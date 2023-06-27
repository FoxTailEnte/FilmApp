package com.example.cinematicapp.presentation.ui.bottomDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentBottomFilterSheetBinding
import com.example.cinematicapp.presentation.adapters.allFilterItems.CheckedItemModel
import com.example.cinematicapp.presentation.adapters.filterRc.FilterAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomFragment(
    private var beCheckedListItem: List<CheckedItemModel>,
    private var saveListener: (List<CheckedItemModel>) -> Unit
) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentBottomFilterSheetBinding
    private lateinit var adapter: FilterAdapter
    private var presenter = BottomSheetDialogPresenter()

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBottomFilterSheetBinding.bind(inflater.inflate(R.layout.fragment_bottom_filter_sheet, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.parseCheckedList(beCheckedListItem)
        initAdapter()
    }

    private fun initAdapter() {
        adapter = FilterAdapter(
            presenter.checkItemLive, presenter.visibilityItemLive,
            viewLifecycleOwner, presenter.getFilterItems()
        ) {
            when (it) {
                is FilterAdapter.CallBack.CheckedItem -> {
                    presenter.saveFilters(it.checkedItem)
                }

                is FilterAdapter.CallBack.VisibilityState -> {
                    presenter.saveVisibilityState(it.state)
                }

                is FilterAdapter.CallBack.SaveListener -> {
                    returnFilterList()
                    dialog?.dismiss()
                }

                is FilterAdapter.CallBack.ClearListener -> {
                    presenter.clearList()
                    returnFilterList()
                    dialog?.dismiss()
                }
            }
        }
        binding.rcFilter.adapter = adapter
    }

    private fun returnFilterList() {
        saveListener.invoke(presenter.getFilterItems())
        dialog?.dismiss()
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val bottomSheet = it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }
}