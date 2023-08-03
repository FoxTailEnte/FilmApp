package com.example.cinematicapp.presentation.ui.labrary

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentLibraryBinding
import com.example.cinematicapp.presentation.adapters.FilmsLoaderStateAdapter
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse
import com.example.cinematicapp.presentation.adapters.libraryFilm.LibraryFilmAdapter
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


class LibraryFragment : BaseFragment<FragmentLibraryBinding, LibraryView, LibraryPresenter>(),
    LibraryView {

    private val footerAdapter = FilmsLoaderStateAdapter()
    private val headerAdapter = FilmsLoaderStateAdapter()
    private val adapter by lazy {
        LibraryFilmAdapter {
            navigateTo(LibraryFragmentDirections.actionLibraryFragmentToFilmInfoFragment(it.id))
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
                } else -> {
                presenter.saveMainPosition(it)
            }
            }
        }
    }

    @InjectPresenter
    lateinit var presenter: LibraryPresenter

    override fun initializeBinding() = FragmentLibraryBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        presenter.initAdapters()
        presenter.getLibraryList()
    }

    override fun setupListener() = with(binding) {
        ivFilters.setOnClickListener {
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
            presenter.getLibraryFilms()
            swipeLayout.isRefreshing = false
        }
    }

    override fun setupUi() {
        getMainActivityView()?.hideBottomMenu(true)
    }

    override fun initRc() = with(binding) {
        val filmLayoutManager = GridLayoutManager(requireContext(), 3)
        rcLib.layoutManager = filmLayoutManager
        rcLib.adapter = adapter.withLoadStateHeaderAndFooter(
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
            if (loadState.refresh is LoadState.Loading) setLoadingState(true) else {
                Handler(Looper.getMainLooper()).postDelayed({
                    setLoadingState(false)
                }, 500)
            }
        }
    }

    override fun initRcMain(state: Boolean, newPosition: Int, oldPosition: Int) {
        binding.recyclerViewMain.adapter = adapterMain
        adapterMain.setState(state, newPosition, oldPosition)
    }

    override fun submitList(items: PagingData<BaseFilmInfoResponse>) {
        adapter.submitData(lifecycle, items)
    }

    override fun setPlaceHolder() {
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { state ->
                when(state.refresh) {
                    is LoadState.NotLoading -> {
                        if(adapter.itemCount == 0) {
                            binding.rcLib.isVisible = false
                            binding.tvEmpty.isVisible = true
                        }
                    }
                    is LoadState.Loading -> {
                        binding.rcLib.isVisible = true
                        binding.tvEmpty.isVisible = false
                    }
                    else -> Unit
                }
            }
        }
    }

    override fun scrollToPosition() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.rcLib.scrollToPosition(0)
        }, 500)
    }

    override fun setFullFilterColor(state: Boolean) {
        if (!state) binding.ivFilters.setColorFilter(Color.argb(255, 255, 255, 255))
        else binding.ivFilters.setColorFilter(Color.argb(255, 107, 102, 102))
    }

    override fun setLoadingState(isLoading: Boolean) = with(binding) {
        rcLib.isVisible = !isLoading
        lPBar.isVisible = isLoading
    }

    @ProvidePresenter
    fun provideLibraryPresenter() = CinematicApplication.appComponent.provideLibraryPresenter()

    private fun showSearchFilterDialog() {
        BottomFragment(presenter.getFilterItemsForDialogFragment()) {
            presenter.clearOldFilters()
            presenter.saveFullFilters(it)
            presenter.getLibraryFilms()
            presenter.saveMainRcState(it.isEmpty())
            updateMainAdapter()
        }.show(childFragmentManager, "tag")
    }

    private fun updateMainAdapter() {
        presenter.initAdapters()
    }
}