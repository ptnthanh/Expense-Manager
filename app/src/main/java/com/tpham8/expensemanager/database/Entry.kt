package com.tpham8.expensemanager.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
data class Entry (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var category: String = "",
    var store: String = "",
    var item: String = "",
    var method: String = "",
    var amount: Int = 0
)
