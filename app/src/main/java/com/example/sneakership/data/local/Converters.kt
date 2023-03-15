package com.example.sneakership.data.local

import androidx.room.TypeConverter
import com.example.sneakership.network.sneaker.Media
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

class Converters {
    @TypeConverter
    fun stringToStringList(stringListString: String?): List<String>? {
        return stringListString?.split(",")?.map { it }
    }

    @TypeConverter
    fun stringListToString(stringList: List<String>?): String? {
        return stringList?.joinToString(separator = ",")
    }

    @TypeConverter
    fun stringToIntegerList(stringListString: String?): List<Int>? {
        return stringListString?.split(",")?.map { it.toInt() }
    }

    @TypeConverter
    fun integerListToString(stringList: List<Int>?): String? {
        return stringList?.joinToString(separator = ",")
    }

    @TypeConverter
    fun mediaToString(media: Media?): String {
        return Gson().toJson(media)
    }

    @Throws(JsonSyntaxException::class)
    @TypeConverter
    fun stringToMedia(value: String?): Media? {
        value ?: return null
        return try {
            Gson().fromJson(value, Media::class.java)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            null
        }
    }
}