package com.example.sneakership.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sneakership.data.local.cart.CartDao
import com.example.sneakership.data.local.cart.CartItem
import com.example.sneakership.data.local.sneaker.SneakerDao
import com.example.sneakership.data.local.sneaker.SneakerUiItem

@Database(entities = [CartItem::class, SneakerUiItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class SneakerDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun sneakerDao(): SneakerDao

    companion object {
        @Volatile
        private var INSTANCE: SneakerDatabase? = null

        fun getDatabase(app: Application): SneakerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    app,
                    SneakerDatabase::class.java,
                    "sneaker_shop_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}