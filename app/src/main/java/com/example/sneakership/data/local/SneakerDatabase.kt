package com.example.sneakership.data.local

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CartItem::class], version = 1)
abstract class SneakerDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao

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