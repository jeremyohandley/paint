package com.ohandley.jeremy.paintapplication



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment : BottomSheetDialogFragment() {


    var viewModel: MainViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.bottom_sheet, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val colorRv = view.findViewById<RecyclerView>(R.id.color_rv)
        val layoutManager = GridLayoutManager(view.context,2, GridLayoutManager.HORIZONTAL, false)
        colorRv.adapter = ColorPickerRecyclerViewAdapter(context!!, viewModel!!, this)
        colorRv.layoutManager = layoutManager

    }




}
