package com.tpham8.expensemanager.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.lang.IllegalStateException
import java.util.concurrent.Executors

class ExpenseRepository private constructor(context: Context){

    private val database: ExpenseDatabase = Room.databaseBuilder(
        context.applicationContext,
        ExpenseDatabase::class.java,
        "expensemanager_database"
    ).build()

    private val expenseDao = database.expenseDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun addEntry(entry: Entry) {
        executor.execute{
            expenseDao.addEntry(entry)
        }
    }

    fun updateEntry(entry: Entry) {
        executor.execute {
            expenseDao.updateEntry(entry)
        }
    }

    fun deleteEntry(entry: Entry) {
        executor.execute {
            expenseDao.deleteEntry(entry)
        }
    }

    fun getEntry(id: Long): LiveData<Entry?> = expenseDao.getItem(id)

    fun getAllItems(): LiveData<List<Entry>> = expenseDao.getAllItems()

    companion object {

        private var INSTANCE: ExpenseRepository? =  null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ExpenseRepository(context)
            }
        }

        fun get(): ExpenseRepository {
            return INSTANCE
                ?: throw IllegalStateException("ExpenseRepository must be initialized.")
        }
    }
}