//package com.example.kuy_njajan.database
//
//import androidx.room.*
//import androidx.room.OnConflictStrategy.REPLACE
//import com.example.kuy_njajan.model.Dagangan
//
//@Dao
//interface DAOTroli {
//
//        @Insert(onConflict = REPLACE)
//        fun insert(data: Dagangan)
//
//        @Delete
//        fun delete(data: Dagangan)
//
//        @Update
//        fun update(data: Dagangan): Int
//
//        @Query("SELECT * from troli ORDER BY id_barang ASC")
//        fun getAll(): List<Dagangan>
//
//        @Query("SELECT * FROM troli WHERE id_barang = :id LIMIT 1")
//        fun getNote(id: Int): Dagangan
//
//        @Query("DELETE FROM troli")
//        fun deleteAll(): Int
//
//}