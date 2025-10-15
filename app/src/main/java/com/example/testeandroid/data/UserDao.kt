package com.example.testeandroid.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT passwordHash FROM users WHERE username = :u LIMIT 1")
    suspend fun getPasswordHash(u: String): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)
}
