package com.tpham8.expensemanager.ui

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tpham8.expensemanager.database.Entry
import com.tpham8.expensemanager.databinding.AddFragmentBinding
import com.tpham8.expensemanager.ui.main.MainViewModel

class AddEntryDialog : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: AddFragmentBinding? = null
    private var newEntry = Entry()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val addFragmentBinding = AddFragmentBinding.inflate(inflater, container, false)
        binding = addFragmentBinding

        binding?.apply {
            categorySpinner.onItemSelectedListener = this@AddEntryDialog
            methodSpinner.onItemSelectedListener = this@AddEntryDialog

            amountEditText.imeOptions = EditorInfo.IME_ACTION_DONE
            amountEditText.inputType = InputType.TYPE_CLASS_TEXT

            addBtn.setOnClickListener {
                with(newEntry) {
                    store = storeEditText.text.toString()
                    item = itemEditText.text.toString()
                    amount = amountEditText.text.toString().toInt()
                }
                sharedViewModel.addEntry(newEntry)
                dismiss()
            }

            cancelButton.setOnClickListener {
                dismiss()
            }
        }
        return addFragmentBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        binding?.apply {
            when (parent) {
                categorySpinner -> newEntry.category = categorySpinner.getItemAtPosition(position).toString()
                methodSpinner -> newEntry.method = methodSpinner.getItemAtPosition(position).toString()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}