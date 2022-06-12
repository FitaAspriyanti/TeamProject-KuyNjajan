package com.example.kuy_njajan.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//import com.example.kuy_njajan.room.DaoFavoriteJajan

/*@Database(
    entities = [FavoriteJajan::class],
    version = 1
)
abstract class FavDatabase: RoomDatabase() {
    companion object {
        var INSTANCE : FavDatabase? = null

        fun getDatabase(context: Context): FavDatabase? {
            if (INSTANCE == null) {
                synchronized(FavDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavDatabase::class.java,
                        "fav_database").build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun daoFavJajan(): DaoFavoriteJajan
}*/