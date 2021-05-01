package com.tpham8.expensemanager.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tpham8.expensemanager.R
import com.tpham8.expensemanager.databinding.FragmentDetailBinding
import com.tpham8.expensemanager.ui.main.MainViewModel

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private val viewModel: MainViewModel by activityViewModels()
    private val expenseAdapter = ExpenseAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var detailFragmentBinding = FragmentDetailBinding.inflate(inflater, container, false)
        binding = detailFragmentBinding
        binding?.apply {
            expenseRecycler.run {
                layoutManager = LinearLayoutManager(context)
                adapter = expenseAdapter
            }
            addButton.setOnClickListener{
                findNavController().navigate(R.id.action_detailFragment_to_addExpenseDialogFragment)
            }
        }

        return detailFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.expenseLiveData.observe(viewLifecycleOwner, {
            expenseAdapter.updateEntries(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}