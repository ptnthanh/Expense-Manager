package com.tpham8.expensemanager.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.tpham8.expensemanager.database.Entry
import com.tpham8.expensemanager.database.ExpenseRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    init {
        ExpenseRepository.initialize(application)
    }

    private val expenseRepository = ExpenseRepository.get()
    val expenseLiveData = expenseRepository.getAllEntries()

    fun getEntry(index: Long) = expenseRepository.getEntry(index)

    fun addEntry(newEntry: Entry) {
        expenseRepository.addEntry(newEntry)
    }

    fun deleteEntry(entry: Entry) {
        expenseRepository.deleteEntry(entry)
    }
}