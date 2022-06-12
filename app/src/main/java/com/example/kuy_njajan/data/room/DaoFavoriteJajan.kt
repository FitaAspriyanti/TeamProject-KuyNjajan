package com.example.kuy_njajan.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
//import com.example.kuy_njajan.local.FavoriteJajan

/*@Dao
interface DaoFavoriteJajan {
    @Insert
    suspend fun addToFav(favoriteJajan: FavoriteJajan)

    @Query("SELECT * FROM favorite_jajan")
    fun getFavJajan(): LiveData<List<FavoriteJajan>>

    @Query("SELECT count(*) FROM favorite_jajan WHERE favorite_jajan.id = :id")
    suspend fun checkFav(id: Int): Int

    @Query("DELETE FROM favorite_jajan WHERE favorite_jajan.id = :id")
    suspend fun deleteFromFav(id: Int): Int
}*/