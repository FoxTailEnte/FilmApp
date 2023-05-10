package com.example.cinematicapp.presentation.ui.labrary

import android.os.Handler
import android.os.Looper
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentLibraryBinding
import com.example.cinematicapp.presentation.adapters.FilmsLoaderStateAdapter
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse
import com.example.cinematicapp.presentation.adapters.libraryFilm.LibraryFilmAdapter
import com.example.cinematicapp.presentation.adapters.mainRcView.MainRcViewAdapter
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.presentation.ui.home.HomeFragmentDirections
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.example.cinematicapp.repository.utils.Extensions.setKeyboardVisibility
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class LibraryFragment : BaseFragment<FragmentLibraryBinding, LibraryView, LibraryPresenter>(), LibraryView {

    @InjectPresenter
    lateinit var presenter: LibraryPresenter
    private lateinit var adapter: LibraryFilmAdapter
    private lateinit var adapterMain: MainRcViewAdapter
    private val footerAdapter = FilmsLoaderStateAdapter()
    private val headerAdapter = FilmsLoaderStateAdapter()

    private fun initRc() = with(binding) {
        val filmLayoutManager = GridLayoutManager(requireContext(), 3)
        rcLib.layoutManager = filmLayoutManager
        adapter = LibraryFilmAdapter {
            navigateTo(HomeFragmentDirections.actionHomeFragmentToFilmInfoFragment(it.id))
        }
        rcLib.adapter = adapter.withLoadStateHeaderAndFooter(
            header = FilmsLoaderStateAdapter(),
            footer = FilmsLoaderStateAdapter()
        )
        val concatAdapter = adapter.withLoadStateHeaderAndFooter(
            header = headerAdapter,
            footer = footerAdapter
        )
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh !is LoadState.Loading) {
                Handler(Looper.getMainLooper()).postDelayed({
                    setLoadingState(false)
                }, 200)
            }
        }
        filmLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0 && headerAdapter.itemCount > 0) 3
                else if (position == concatAdapter.itemCount - 1 && footerAdapter.itemCount > 0) 3
                else 1
            }
        }
    }

    private fun initRcMain() {
        adapterMain = MainRcViewAdapter({
            genresListener(getString(it.name))
        }) {
           // presenter.setRcMainPosition(it)
        }
        binding.recyclerViewMain.adapter = adapterMain
    }

    private fun genresListener(genre: String) {
        if (genre != getString(R.string.all)) {
            presenter.getGenresLibraryFilms(arrayOf(genre.lowercase()))
        } else {
            presenter.getAllLibraryList()
        }
    }

    private fun getLibraryList() {
        setLoadingState(true)
        presenter.getCurrentFilmList()
    }

    private fun hideKeyBoard() {
        requireActivity().setKeyboardVisibility(false)
    }

    private fun scrollList() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.rcLib.scrollToPosition(0)
        }, 200)
    }

    @ProvidePresenter
    fun provideLibraryPresenter() = CinematicApplication.appComponent.provideLibraryPresenter()

    override fun initializeBinding() = FragmentLibraryBinding.inflate(layoutInflater)

    override fun setupUi() {
        initRc()
        initRcMain()
        getLibraryList()
        //presenter.getRcMainPosition()
    }

    override fun setupListener() = with(binding) {
        edSearchText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.getSearchList(edSearchText.text.toString().trim())
                hideKeyBoard()
            }
            true
        }
        swipeLayout.setOnRefreshListener {
            presenter.getCurrentFilmList()
            swipeLayout.isRefreshing = false
        }
    }

    override fun getSearchText() {
        presenter.getSearchList(binding.edSearchText.text.toString())
    }

    override fun scrollRcMain(position: Int) {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.recyclerViewMain.scrollToPosition(position)
        }, 200)
    }

    override fun submitList(items: PagingData<BaseFilmInfoResponse>) {
        adapter.submitData(lifecycle, items)
        scrollList()
    }

    override fun setLoadingState(isLoading: Boolean) = with(binding) {
        pBar.isVisible = isLoading
        rcLib.isVisible = !isLoading
    }
}