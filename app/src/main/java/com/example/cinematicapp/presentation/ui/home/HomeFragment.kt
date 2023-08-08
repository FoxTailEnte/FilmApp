package com.example.cinematicapp.presentation.ui.home


import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentHomeBinding
import com.example.cinematicapp.presentation.adapters.FilmsLoaderStateAdapter
import com.example.cinematicapp.presentation.adapters.homeFilm.HomeFilmAdapter
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse
import com.example.cinematicapp.presentation.adapters.main.MainRcViewAdapter
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.presentation.ui.bottomDialog.BottomFragment
import com.example.cinematicapp.repository.utils.Extensions.getMainActivityView
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.example.cinematicapp.repository.utils.Extensions.setKeyboardVisibility
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeView, HomePresenter>(), HomeView {

    private val footerAdapter = FilmsLoaderStateAdapter()
    private val headerAdapter = FilmsLoaderStateAdapter()
    private val adapter by lazy {
        HomeFilmAdapter {
            navigateTo(HomeFragmentDirections.actionHomeFragmentToFilmInfoFragment(it.id))
        }
    }
    private val adapterMain by lazy {
        MainRcViewAdapter {
            when (it) {
                is MainRcViewAdapter.CallBack.ModelCallBack -> {
                    if (getString(it.item.name) != getString(R.string.all)) {
                        presenter.clearOldFilters()
                        presenter.getFilmsWithGenres(genres = listOf(getString(it.item.name).lowercase()))
                    } else {
                        presenter.clearOldFilters()
                        presenter.getFilmsWithGenres()
                    }
                }

                else -> {
                    presenter.saveMainPosition(it)
                }
            }
        }
    }


    @InjectPresenter
    lateinit var presenter: HomePresenter

    override fun initializeBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun checkUserAuth() {
        if (!presenter.checkUserAuthStatus()) navigateTo(HomeFragmentDirections.actionHomeFragmentToGraphAuthorization())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.getFilmWithFilters()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initAdapters()
    }

    override fun onResume() {
        super.onResume()
        binding.edSearchText.setText(presenter.searchText)
    }

    override fun setupListener() = with(binding) {
        ivSearchFilters.setOnClickListener {
            showSearchFilterDialog()
        }
        edSearchText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.getFilmsWithText(text = listOf(edSearchText.text.toString()))
                presenter.searchText = edSearchText.text.toString()
                requireActivity().setKeyboardVisibility(false)
            }
            true
        }
        swipeLayout.setOnRefreshListener {
            presenter.getFilmWithFilters()
            swipeLayout.isRefreshing = false
        }
    }

    override fun setupUi() {
        getMainActivityView()?.hideBottomMenu(true)
    }

    override fun initRcMain(state: Boolean, newPosition: Int, oldPosition: Int) {
        binding.recyclerViewMain.adapter = adapterMain
        adapterMain.setState(state, newPosition, oldPosition)
    }

    override fun initRc() = with(binding) {
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) setLoadingState(true) else {
                Handler(Looper.getMainLooper()).postDelayed({
                    setLoadingState(false)
                }, 200)
            }
        }
        val filmLayoutManager = GridLayoutManager(requireContext(), 3)
        rcHome.layoutManager = filmLayoutManager
        rcHome.adapter = adapter.withLoadStateHeaderAndFooter(
            header = FilmsLoaderStateAdapter(),
            footer = FilmsLoaderStateAdapter()
        )
        val concatAdapter = adapter.withLoadStateHeaderAndFooter(
            header = headerAdapter,
            footer = footerAdapter
        )
        filmLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0 && headerAdapter.itemCount > 0) 3
                else if (position == concatAdapter.itemCount - 1 && footerAdapter.itemCount > 0) 3
                else 1
            }
        }
    }

    override fun submitList(items: PagingData<BaseFilmInfoResponse>) {
        adapter.submitData(lifecycle, items)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.rcHome.scrollToPosition(0)
        }, 500)
    }

    override fun setPlaceHolder() {
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { state ->
                when (state.refresh) {
                    is LoadState.NotLoading -> {
                        if (adapter.itemCount == 0) {
                            binding.rcHome.isVisible = false
                            binding.tvEmpty.isVisible = true
                        }
                    }

                    is LoadState.Loading -> {
                        binding.rcHome.isVisible = true
                        binding.tvEmpty.isVisible = false
                    }

                    else -> Unit
                }
            }
        }
    }

    override fun setFullFilterColor(state: Boolean) {
        if (!state) binding.ivSearchFilters.setColorFilter(Color.argb(255, 255, 255, 255))
        else binding.ivSearchFilters.setColorFilter(Color.argb(255, 107, 102, 102))
    }

    override fun setLoadingState(isLoading: Boolean) = with(binding) {
        rcHome.isVisible = !isLoading
        lPBar.isVisible = isLoading
    }

    @ProvidePresenter
    fun provideHomePresenter() = CinematicApplication.appComponent.provideHomePresenter()

    private fun showSearchFilterDialog() {
        BottomFragment(presenter.getFilterItemsForDialogFragment()) {
            presenter.clearOldFilters()
            presenter.saveFullFilters(it)
            presenter.getFilmWithFilters()
            presenter.saveMainRcState(it.isEmpty())
            updateMainAdapter()
        }.show(childFragmentManager, "tag")
    }

    private fun updateMainAdapter() {
        presenter.initAdapters()
    }
}