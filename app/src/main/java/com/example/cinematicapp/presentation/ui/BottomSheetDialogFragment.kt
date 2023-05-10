package com.example.cinematicapp.presentation.ui

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
    private var item: List<String>,
    private var checkedItem: (CheckedItemModel) -> Unit,
) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentBottomFilterSheetBinding
    private lateinit var adapter: FilterAdapter
    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBottomFilterSheetBinding.bind(inflater.inflate(R.layout.fragment_bottom_filter_sheet, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FilterAdapter(item){
            checkedItem.invoke(it)
        }
        binding.rcFilter.adapter = adapter
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