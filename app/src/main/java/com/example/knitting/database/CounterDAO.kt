package com.example.knitting.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CounterDAO {

    @Insert
    suspend fun insert (counter: Counter)

    @Update
    suspend fun update (counter: Counter)

    @Delete
    suspend fun delete (counter: Counter)

    @Query ("SELECT * FROM counter_table WHERE counterID = :counterID")
    suspend fun get (counterID: Int): Counter

    @Query("SELECT * FROM counter_table ORDER BY counterID DESC LIMIT 1")
    suspend fun getCounter(): Counter?

    @Query("SELECT * FROM counter_table ORDER BY counterID DESC")
    fun getAllCounters(): LiveData<List<Counter>>

}