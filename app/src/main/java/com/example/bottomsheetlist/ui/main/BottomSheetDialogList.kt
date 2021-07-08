package com.example.bottomsheetlist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.fragment.app.FragmentManager
import com.example.bottomsheetlist.R
import com.example.bottomsheetlist.databinding.BottomSheetDialogLayoutBinding
import com.example.bottomsheetlist.ui.main.adapter.BottomSheetDialogAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialogList: BottomSheetDialogFragment() {

    @StyleRes
    private var dialogTheme: Int = DEFAULT_DIALOG_THEME
    private var dialogTitle: String? = null

    var adapter: BottomSheetDialogAdapter? = null
        set(value) {
            field = value
            if (isAdded) {
                initRecyclerView()
            }
        }

    private lateinit var bottomSheetDialogLayoutBinding: BottomSheetDialogLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        setStyle(STYLE_NORMAL, dialogTheme)
    }

    private fun initData() {
        arguments?.let { arguments ->
            dialogTheme = arguments.getInt(EXTRA_DIALOG_THEME)
            dialogTitle = arguments.getString(EXTRA_DIALOG_TITLE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottomSheetDialogLayoutBinding = BottomSheetDialogLayoutBinding.inflate(
            inflater, container,false
        )

        return bottomSheetDialogLayoutBinding.root
    }

    private fun initUI() {
        initTitle()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        bottomSheetDialogLayoutBinding.menuRecyclerView?.adapter = adapter
    }

    private fun initTitle() {
        bottomSheetDialogLayoutBinding.titleTextView.text = dialogTitle
        bottomSheetDialogLayoutBinding.titleTextView.visibility = if (dialogTitle.isNullOrBlank()) GONE else VISIBLE
    }

    fun showAllowingStateLost(manager: FragmentManager, tag: String? = null) {
        if (!this.isAdded) {
            val transaction = manager.beginTransaction()
            transaction.add(this, tag)
            transaction.commitAllowingStateLoss()
        }
    }

    companion object {
        const val TAG = "TicketsStatusListBottomSheetDialog"

        val DEFAULT_DIALOG_THEME = R.style.BottomSheetSpinner_DialogTheme

        private const val EXTRA_DIALOG_THEME = "dialog_theme"
        private const val EXTRA_DIALOG_TITLE = "dialog_title"

        fun newInstance(
            @StyleRes themeRes: Int = DEFAULT_DIALOG_THEME,
            dialogTitle: String? = null
        ): BottomSheetDialogList {
            val fragment = BottomSheetDialogList()
            fragment.arguments = Bundle().apply {
                putInt(EXTRA_DIALOG_THEME, themeRes)
                putString(EXTRA_DIALOG_TITLE, dialogTitle)
            }
            return fragment
        }
    }
}