package com.example.knitting.database

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
    suspend fun get (counterID: Int): Counter?

}