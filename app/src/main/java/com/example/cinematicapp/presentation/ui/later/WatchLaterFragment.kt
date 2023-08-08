package com.example.cinematicapp.presentation.ui.later

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
import com.example.cinematicapp.databinding.FragmentWatchLaterBinding
import com.example.cinematicapp.presentation.adapters.FilmsLoaderStateAdapter
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseFilmInfoResponse
import com.example.cinematicapp.presentation.adapters.main.MainRcViewAdapter
import com.example.cinematicapp.presentation.adapters.watchLater.WatchLaterFilmAdapter
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.presentation.ui.bottomDialog.BottomFragment
import com.example.cinematicapp.repository.utils.Extensions.getMainActivityView
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.example.cinematicapp.repository.utils.Extensions.setKeyboardVisibility
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class WatchLaterFragment :
    BaseFragment<FragmentWatchLaterBinding, WatchLaterView, WatchLaterPresenter>(), WatchLaterView {

    private val footerAdapter = FilmsLoaderStateAdapter()
    private val headerAdapter = FilmsLoaderStateAdapter()
    private val adapter by lazy {
        WatchLaterFilmAdapter {
            navigateTo(WatchLaterFragmentDirections.actionWatchLaterFragmentToFilmInfoFragment(it.id))
        }
    }
    private val adapterMain by lazy {
        MainRcViewAdapter {
            setLoadingState(true)
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
    lateinit var presenter: WatchLaterPresenter

    override fun initializeBinding() = FragmentWatchLaterBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLoadingState(true)
        presenter.getLibraryList()
        presenter.initAdapters()
    }

    override fun onResume() {
        super.onResume()
        binding.edSearchText.setText(presenter.searchText)
    }

    override fun setupListener() = with(binding) {
        ivFilters.setOnClickListener {
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
            presenter.getWatchLaterFilms()
            swipeLayout.isRefreshing = false
        }
    }

    override fun setupUi() {
        getMainActivityView()?.hideBottomMenu(true)
    }

    override fun initRc() = with(binding) {
        adapter.addLoadStateListener { loadState ->
            if (loadState.append is LoadState.Loading) setLoadingState(true) else {
                Handler(Looper.getMainLooper()).postDelayed({
                    setLoadingState(false)
                }, 200)
            }
        }
        val filmLayoutManager = GridLayoutManager(requireContext(), 3)
        rcWatchLater.layoutManager = filmLayoutManager
        rcWatchLater.adapter = adapter.withLoadStateHeaderAndFooter(
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
                when (state.append) {
                    is LoadState.NotLoading -> {
                        if (adapter.itemCount == 0) {
                            binding.tvEmpty.text =
                                this@WatchLaterFragment.getText(R.string.emptyList)
                            binding.tvEmpty.visibility = View.VISIBLE
                        } else {
                            binding.tvEmpty.visibility = View.GONE
                            binding.rcWatchLater.isVisible = true
                            setLoadingState(false)
                        }
                    }

                    is LoadState.Loading -> {
                        binding.tvEmpty.visibility = View.GONE
                    }

                    else -> Unit
                }
            }
        }
    }

    override fun setPlaceHolderEmptyList() = with(binding) {
        lPBar.isVisible = false
        rcWatchLater.isVisible = false
        tvEmpty.text = this@WatchLaterFragment.getText(R.string.emptyListWatch)
        tvEmpty.isVisible = true
    }

    override fun scrollToPosition() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.rcWatchLater.scrollToPosition(0)
        }, 500)
    }

    override fun setFullFilterColor(state: Boolean) {
        if (!state) binding.ivFilters.setColorFilter(Color.argb(255, 255, 255, 255))
        else binding.ivFilters.setColorFilter(Color.argb(255, 107, 102, 102))
    }

    override fun setLoadingState(isLoading: Boolean) {
        if (!isLoading) {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.lPBar.isVisible = false
            }, 500)
        } else {
            binding.lPBar.isVisible = true
        }
    }

    @ProvidePresenter
    fun provideWatchLaterPresenter() =
        CinematicApplication.appComponent.provideWatchLaterPresenter()

    private fun showSearchFilterDialog() {
        BottomFragment(presenter.getFilterItemsForDialogFragment()) {
            presenter.clearOldFilters()
            presenter.saveFullFilters(it)
            presenter.getWatchLaterFilms()
            presenter.saveMainRcState(it.isEmpty())
            updateMainAdapter()
        }.show(childFragmentManager, "tag")
    }

    private fun updateMainAdapter() {
        presenter.initAdapters()
    }
}