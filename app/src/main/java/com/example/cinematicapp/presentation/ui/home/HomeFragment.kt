package com.example.cinematicapp.presentation.ui.home


import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentHomeBinding
import com.example.cinematicapp.presentation.adapters.FilmsLoaderStateAdapter
import com.example.cinematicapp.presentation.adapters.homeFilm.HomeFilmAdapter
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse
import com.example.cinematicapp.presentation.adapters.mainRcView.MainRcViewAdapter
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.presentation.ui.bottomDialog.BottomFragment
import com.example.cinematicapp.repository.utils.Extensions.getMainActivityView
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.example.cinematicapp.repository.utils.Extensions.setKeyboardVisibility
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeView, HomePresenter>(), HomeView {

    @InjectPresenter
    lateinit var presenter: HomePresenter
    private lateinit var adapter: HomeFilmAdapter
    private lateinit var adapterMain: MainRcViewAdapter
    private val footerAdapter = FilmsLoaderStateAdapter()
    private val headerAdapter = FilmsLoaderStateAdapter()

    @ProvidePresenter
    fun provideHomePresenter() = CinematicApplication.appComponent.provideHomePresenter()

    override fun initializeBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun checkUserAuth() {
        if (!presenter.checkUserAuthStatus()) navigateTo(HomeFragmentDirections.actionHomeFragmentToGraphAuthorization())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRc()
        initRcMain(true)
        presenter.getFilmWithFilters()
    }

    override fun setupListener() = with(binding) {
        ivSearchFilters.setOnClickListener {
            showSearchFilterDialog()
        }
        edSearchText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.getFilmsWithText(text = listOf(edSearchText.text.toString()))
                requireActivity().setKeyboardVisibility(false)
            }
            true
        }
        swipeLayout.setOnRefreshListener {
            presenter.getFilmWithFilters()
            swipeLayout.isRefreshing = false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcOptions()
        rcMainOption()
    }

    override fun setupUi() {
        getMainActivityView()?.hideBottomMenu(true)
    }

    override fun initRcMain(state: Boolean) {
        adapterMain = MainRcViewAdapter(state) {
            if (getString(it.name) != getString(R.string.all)) {
                presenter.getFilmsWithGenres(genres = listOf(getString(it.name).lowercase()))
            } else {
                presenter.getFilmsWithGenres()
            }
        }
        rcMainOption()
        //adapterMain.submitList()
    }

    private fun rcMainOption() {
        binding.recyclerViewMain.adapter = adapterMain
        adapterMain.submitList()
    }

    private fun initRc() {
        adapter = HomeFilmAdapter {
            navigateTo(HomeFragmentDirections.actionHomeFragmentToFilmInfoFragment(it.id))
        }
    }

    private fun rcOptions() = with(binding) {
        val filmLayoutManager =  GridLayoutManager(requireContext(), 3)
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
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) setLoadingState(true) else setLoadingState(false)
        }
    }

    override fun submitList(items: PagingData<BaseFilmInfoResponse>) {
        adapter.submitData(lifecycle, items)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.rcHome.scrollToPosition(0)
        }, 500)
    }

    private fun showSearchFilterDialog() {
        BottomFragment(presenter.getFilterItems()) {
            presenter.saveFullFilters(it)
            presenter.getFilmWithFilters()
        }.show(childFragmentManager, "tag")
    }

    override fun setFullFilterColor(state: Boolean) {
        if (state) binding.ivSearchFilters.setColorFilter(Color.argb(255, 255, 255, 255))
        else binding.ivSearchFilters.setColorFilter(Color.argb(255, 107, 102, 102))
    }

    override fun setLoadingState(isLoading: Boolean) = with(binding) {
        rcHome.isVisible = !isLoading
        pBar.isVisible = isLoading
    }
}