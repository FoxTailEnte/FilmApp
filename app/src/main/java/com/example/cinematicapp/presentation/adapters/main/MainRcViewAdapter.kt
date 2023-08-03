package com.example.cinematicapp.presentation.adapters.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinematicapp.databinding.ItemRcViewMainBinding
import com.example.cinematicapp.repository.data.mainRcViewListSubmit

class MainRcViewAdapter(
    private val callBack: (position: CallBack) -> Unit
) : RecyclerView.Adapter<MainRcViewHolder>() {

    private val list by lazy { mainRcViewListSubmit() }
    private var colorState: Boolean = true
    private var newPosition: Int = 0
    private var oldPosition: Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainRcViewHolder(
        ItemRcViewMainBinding.inflate(LayoutInflater.from(parent.context), parent, false),{
            callBack.invoke(CallBack.ModelCallBack(it))
        }) {
        newPosition = it
        callBack.invoke(CallBack.NewPosition(newPosition))
        colorState = true
        updateSelectItem(it)
    }

    override fun onBindViewHolder(holder: MainRcViewHolder, position: Int) {
        holder.bind(list[position], newPosition, colorState)
    }

    private fun updateSelectItem(pos: Int) {
       notifyItemChanged(pos)
       notifyItemChanged(oldPosition)
        oldPosition = pos
        callBack.invoke(CallBack.OldPosition(oldPosition))
    }

    override fun getItemCount() = list.size

    fun setState(state: Boolean, newPosition: Int, oldPosition: Int) {
        this.newPosition = newPosition
        this.oldPosition = oldPosition
        colorState = state
    }

    sealed class CallBack {
        class ModelCallBack(val item: MainRcViewModel): CallBack()
        class NewPosition(val position: Int): CallBack()
        class OldPosition(val position: Int): CallBack()
    }
}