package com.example.kuy_njajan.data.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.kuy_njajan.model.Dagangan

@Dao
interface DaoBelisekarang {

    @Insert(onConflict = REPLACE)
    fun insert(data: Dagangan)

    @Delete
    fun delete(data: Dagangan)

    @Update
    fun update(data:Dagangan): Int

    @Query("SELECT * from belisekarang ORDER BY id ASC")
    fun getAll(): List<Dagangan>

    @Query("SELECT * FROM belisekarang WHERE id = :id LIMIT 1")
    fun getDagangan(id: String): Dagangan

    @Query("DELETE FROM belisekarang")
    fun deleteAll(): Int
}