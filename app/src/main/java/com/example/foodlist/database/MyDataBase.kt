package com.example.foodlist.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodlist.data.IsFavoriteData
import com.example.foodlist.data.ItemData
import com.example.foodlist.intarface.BasketDao
import com.example.foodlist.intarface.FavoriteDao

@Database(entities = [ItemData::class, IsFavoriteData::class], version = 2)
abstract class MyDataBase : RoomDatabase() {

    abstract fun getDao(): BasketDao

    abstract fun getFavoriteDao(): FavoriteDao

    companion object {
        fun getDataBase(context: Context): MyDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                MyDataBase::class.java,
                "my_data_base"
            ).build()
        }
    }
}