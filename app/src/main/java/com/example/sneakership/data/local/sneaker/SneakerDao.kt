package com.example.sneakership.data.local.sneaker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sneakership.feature.home.enums.SortOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface SneakerDao {

    fun search(searchQuery: String = "", sortOrder: SortOrder): Flow<List<SneakerUiItem>> = when(sortOrder) {
        SortOrder.BY_NAME -> searchDatabaseSortByName(searchQuery)
        SortOrder.BY_RETAIL_PRICE -> searchDatabaseSortByPrice(searchQuery)
    }
    @Query("SELECT * FROM sneaker_table")
    fun getAll(): Flow<List<SneakerUiItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sneakers: List<SneakerUiItem>)

    @Query("SELECT * FROM sneaker_table WHERE name LIKE '%' || :searchQuery || '%' OR brand LIKE '%' || :searchQuery || '%' ORDER BY name")
    fun searchDatabaseSortByName(searchQuery: String): Flow<List<SneakerUiItem>>

    @Query("SELECT * FROM sneaker_table WHERE name LIKE '%' || :searchQuery || '%' OR brand LIKE '%' || :searchQuery || '%' ORDER BY retailPrice")
    fun searchDatabaseSortByPrice(searchQuery: String): Flow<List<SneakerUiItem>>

}