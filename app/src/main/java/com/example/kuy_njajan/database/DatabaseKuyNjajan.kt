//package com.example.kuy_njajan.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [Dagangan::class] /* List model Ex:NoteModel */, version = 1)
//abstract class DatabaseKuyNjajan : RoomDatabase() {
//
//    companion object {
//        private var INSTANCE: DatabaseKuyNjajan? = null
//
//        fun getInstance(context: Context): DatabaseKuyNjajan? {
//            if (INSTANCE == null) {
//                synchronized(DatabaseKuyNjajan::class) {
//                    INSTANCE = Room.databaseBuilder(
//                        context.applicationContext,
//                        DatabaseKuyNjajan::class.java, "db_kuynjajan" // Database Name
//                    ).allowMainThreadQueries().build()
//                }
//            }
//            return INSTANCE
//        }
//
//        fun destroyInstance() {
//            INSTANCE = null
//        }
//    }
//}