package com.example.kuy_njajan.room

import androidx.room.*
import com.example.kuy_njajan.model.Dagangan

@Dao
interface DaoKeranjang {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: Dagangan)

    @Delete
    fun delete(data: Dagangan)

    @Update
    fun update(data: Dagangan): Int

    @Query("SELECT * from keranjang ORDER BY id ASC")
    fun getAll(): List<Dagangan>

    @Query("SELECT * FROM keranjang WHERE id = :id LIMIT 1")
    fun getKeranjang(id: String): Dagangan

    @Query("DELETE FROM keranjang")
    fun deleteAll(): Int

}