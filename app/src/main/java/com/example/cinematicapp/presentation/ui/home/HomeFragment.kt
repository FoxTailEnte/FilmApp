package com.example.cinematicapp.presentation.ui.home

import android.os.Handler
import android.os.Looper
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
import com.example.cinematicapp.repository.utils.Constants
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
    private val layoutManager by lazy {GridLayoutManager(requireContext(),3)}
    private val footerAdapter = FilmsLoaderStateAdapter()
    private val headerAdapter = FilmsLoaderStateAdapter()

    @ProvidePresenter
    fun provideHomePresenter() = CinematicApplication.appComponent.provideHomePresenter()

    override fun initializeBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun checkUserAuth() {
        if (!presenter.checkUserAuthStatus()) navigateTo(HomeFragmentDirections.actionHomeFragmentToGraphAuthorization())
    }

    override fun setupListener() = with(binding) {
        edSearchText.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.getRandomFilms(arrayOf(edSearchText.text.toString()),Constants.SEARCH)
                requireActivity().setKeyboardVisibility(false)
            }
            true
        }
        swipeLayout.setOnRefreshListener {
            presenter.getRefreshFilms()
            swipeLayout.isRefreshing = false
        }
    }

    override fun setupUi() {
        initRc()
        initRcMain()
        getRandomFilms()
        getMainActivityView()?.hideBottomMenu(true)
    }

    private fun initRc() {
        binding.rcHome.layoutManager = layoutManager
        adapter = HomeFilmAdapter {
            navigateTo(HomeFragmentDirections.actionHomeFragmentToFilmInfoFragment(it.id))
        }
        checkLoadingAdapter()
    }

    private fun checkLoadingAdapter() = with(binding) {
        rcHome.adapter = adapter.withLoadStateHeaderAndFooter(
            header = FilmsLoaderStateAdapter(),
            footer = FilmsLoaderStateAdapter()
        )
        val concatAdapter = adapter.withLoadStateHeaderAndFooter(
            header = headerAdapter,
            footer = footerAdapter
        )
        adapter.addLoadStateListener { loadState ->
            rcHome.isVisible = loadState.source.refresh is LoadState.NotLoading
            pBar.isVisible = loadState.refresh is LoadState.Loading
        }
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
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
                presenter.getGenresFilms(arrayOf(getString(it.name).lowercase()), Constants.GENRES)
            } else {
                presenter.getRandomFilms(arrayOf(""), Constants.BASE)
            }
        }
        binding.recyclerViewMain.adapter = adapterMain
    }

    private fun getRandomFilms() {
        presenter.getRandomFilms(arrayOf(""), Constants.BASE)
    }

    override fun submitList(items: PagingData<BaseFilmInfoResponse>) {
        adapter.submitData(lifecycle,items)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.rcHome.scrollToPosition(0)
        }, 200)
    }

    override fun setLoadingState(isLoading: Boolean) {

    }

}