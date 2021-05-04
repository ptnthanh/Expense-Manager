package com.tpham8.expensemanager.ui

import android.app.AlertDialog
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tpham8.expensemanager.MainActivity
import com.tpham8.expensemanager.R
import com.tpham8.expensemanager.database.Entry
import com.tpham8.expensemanager.databinding.FragmentDetailBinding
import com.tpham8.expensemanager.ui.main.MainViewModel

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private val viewModel: MainViewModel by activityViewModels()
    private val expenseAdapter = ExpenseAdapter()

    private val prefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
                findNavController().navigate(R.id.action_detailFragment_to_addEntryDialog)
            }
            when (prefs.getString(MainActivity.BACKGROUND_COLOR, "0")?.toInt()) {
                0 -> root.setBackgroundColor(Color.parseColor("#ffffff"))
                else -> root.setBackgroundColor(Color.parseColor("#d3e0ea"))
            }
        }

        return detailFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.expenseLiveData.observe(viewLifecycleOwner, {
            expenseAdapter.updateEntries(it)
        })

        val itemTouchHelperCallback =
                object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                    override fun onMove(
                            recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder
                    ) = false

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val entry = expenseAdapter.getEntryAtPosition(viewHolder.adapterPosition)
                        deleteEntryAlert(entry)
                    }
                }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding?.expenseRecycler)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun deleteEntryAlert(entry: Entry) {
        val msg = resources.getString(R.string.delete_entry_alert, entry.item, "$" + entry.amount)
        val builder = AlertDialog.Builder(context)
        with (builder) {
            setTitle(R.string.alert)
            setMessage(msg)
            setPositiveButton(R.string.yes) { _, _ ->
                viewModel.deleteEntry(entry)
            }
            setNegativeButton(R.string.no) { _, _ ->
                expenseAdapter.notifyDataSetChanged()
            }
            show()
        }
    }
}