package com.example.knitting.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counter_table")
data class Counter(
        @PrimaryKey(autoGenerate = true)
        val counterID: Int,

        @ColumnInfo(name = "name")
        val counterName: String = " ",

        @ColumnInfo(name = "count_number")
        val countNumber: Long = 0L,

        @ColumnInfo(name = "time")
        val time: Long = 0L,

        @ColumnInfo(name = "step")
        val step: Int = 1,

        @ColumnInfo(name = "note")
        val note: String = " "
)