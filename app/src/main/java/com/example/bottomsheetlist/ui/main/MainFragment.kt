package com.example.bottomsheetlist.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bottomsheetlist.R
import com.example.bottomsheetlist.databinding.MainFragmentBinding
import com.example.bottomsheetlist.ui.main.adapter.BottomSheetDialogAdapter
import com.example.bottomsheetlist.ui.main.adapter.TitleEnum

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding

    private lateinit var adapter: BottomSheetDialogAdapter

    private var dialog: BottomSheetDialogList? = null

    private val listener: (TitleEnum) -> Unit = { title ->
        val toast = Toast.makeText(this.requireContext(), title.string, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = MainFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomSheet()
        setupListener()
    }

    private fun setupListener() {
        adapter.submitList(TitleEnum.values().toList())
        binding.showDialogButton.setOnClickListener {
            dialog?.showAllowingStateLost(this.childFragmentManager,BottomSheetDialogList.TAG)
        }
    }

    private fun setupBottomSheet() {
        adapter = BottomSheetDialogAdapter (listener)
        dialog = BottomSheetDialogList.newInstance(BottomSheetDialogList.DEFAULT_DIALOG_THEME)
    }


}