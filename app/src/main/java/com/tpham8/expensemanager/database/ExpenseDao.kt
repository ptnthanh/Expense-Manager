package com.tpham8.expensemanager.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExpenseDao {

    @Insert
    fun addEntry(entry: Entry)

    @Update
    fun updateEntry(entry: Entry)

    @Delete
    fun deleteEntry(entry: Entry)

    @Query("SELECT * FROM expense_table WHERE id=(:id)")
    fun getItem(id: Long): LiveData<Entry?>

    @Query("SELECT * FROM expense_table ORDER BY id DESC")
    fun getAllItems(): LiveData<List<Entry>>

    @Query("SELECT * FROM expense_table ORDER BY id DESC LIMIT 1")
    fun getLastItem(): LiveData<Entry?>
}