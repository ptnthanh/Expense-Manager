package com.tpham8.expensemanager.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Entry::class], version = 1, exportSchema = true)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}