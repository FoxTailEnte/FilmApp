package com.example.cinematicapp.presentation.ui.labrary

import android.os.Handler
import android.os.Looper
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
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class LibraryFragment : BaseFragment<FragmentLibraryBinding, LibraryView, LibraryPresenter>(), LibraryView {

    @InjectPresenter
    lateinit var presenter: LibraryPresenter
    private lateinit var adapter: LibraryFilmAdapter
    private lateinit var adapterMain: MainRcViewAdapter
    private val footerAdapter = FilmsLoaderStateAdapter()
    private val headerAdapter = FilmsLoaderStateAdapter()

    @ProvidePresenter
    fun provideLibraryPresenter() = CinematicApplication.appComponent.provideLibraryPresenter()

    override fun initializeBinding() = FragmentLibraryBinding.inflate(layoutInflater)

    override fun setupUi() {
        initRc()
        initRcMain()
        getFilmsList()
    }

    override fun setupListener() = with(binding) {
       /* edSearchText.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.getRandomFilms(arrayOf(edSearchText.text.toString()), Constants.SEARCH)
                requireActivity().setKeyboardVisibility(false)
            }
            true
        }*/
        swipeLayout.setOnRefreshListener {
            presenter.getRefreshFilms()
            swipeLayout.isRefreshing = false
        }
    }

    private fun initRc() = with(binding) {
        val filmLayoutManager = GridLayoutManager(requireContext(),3)
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
            if (loadState.refresh is LoadState.Loading) setLoadingState(true) else setLoadingState(false)
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
        adapterMain = MainRcViewAdapter {
            if(getString(it.name) != getString(R.string.all)) {
               // presenter.getGenresFilms(arrayOf(getString(it.name).lowercase()), Constants.GENRES)
            } else {
               // presenter.getRandomFilms(arrayOf(""), Constants.BASE)
            }
        }
        binding.recyclerViewMain.adapter = adapterMain
    }

    private fun getFilmsList() {
        presenter.getLibraryList()
    }

    override fun submitList(items: PagingData<BaseFilmInfoResponse>) {
        adapter.submitData(lifecycle,items)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.rcLib.scrollToPosition(0)
        }, 200)
    }

    override fun setLoadingState(isLoading: Boolean) = with(binding) {
        rcLib.isVisible = !isLoading
        pBar.isVisible = isLoading
    }
}