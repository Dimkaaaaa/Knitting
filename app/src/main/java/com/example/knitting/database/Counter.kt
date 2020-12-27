package com.example.knitting.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counter_table")
data class Counter(
        @PrimaryKey(autoGenerate = true)
        var counterID: Int = 0,

        @ColumnInfo(name = "name")
        var counterName: String = " ",

        @ColumnInfo(name = "count_number")
        var countNumber: Long = 0L,

        @ColumnInfo(name = "time")
        var time: Long = 0L,

        @ColumnInfo(name = "step")
        var step: Long = 1,

        @ColumnInfo(name = "note")
        var note: String = " ",

        @ColumnInfo(name = "state")
        var state: String = ""
)