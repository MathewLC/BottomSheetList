package com.example.bottomsheetlist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StyleRes
import com.example.bottomsheetlist.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialogList: BottomSheetDialogFragment() {

    @StyleRes
    private var dialogTheme: Int = DEFAULT_DIALOG_THEME
    private var dialogTitle: String? = null

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog_layout, container, false)
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