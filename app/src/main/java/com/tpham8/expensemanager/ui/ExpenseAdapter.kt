package com.tpham8.expensemanager.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tpham8.expensemanager.database.Entry
import com.tpham8.expensemanager.databinding.ExpenseEntryBinding

class ExpenseAdapter : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    private var entries: List<Entry> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ExpenseViewHolder (
        ExpenseEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(entries[position])
    }

    override fun getItemCount() = entries.size

    fun getEntryAtPosition(position: Int) = entries[position]

    fun updateEntries(newEntries: List<Entry>) {
        this.entries = newEntries
        notifyDataSetChanged()
    }

    class ExpenseViewHolder(private val binding: ExpenseEntryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: Entry) {
            binding.apply {
                itemNameTextView.text = entry.item + ":"
                storeTextView.text = entry.store
                amountTextView.text = "$" + entry.amount
                categoryTextView.text = entry.category
                methodTextView.text = entry.method
            }
        }
    }
}