package com.example.sneakership.data.local.sneaker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SneakerDao {

    @Query("SELECT * FROM sneaker_table")
    fun getAll(): Flow<List<SneakerUiItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sneakers: List<SneakerUiItem>)

    @Query("SELECT * FROM sneaker_table WHERE name LIKE :searchQuery OR brand LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<SneakerUiItem>>

}