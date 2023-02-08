package com.example.quizapp.data.local

import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM USERS WHERE UserName=:username AND Password=:password")
    fun checkIfUserIsRegistered(username: String, password: String): Flow<List<User>>

    @Query("SELECT * FROM USERS WHERE UserName=:username")
    fun checkIfUserAlreadyExist(username: String): Flow<List<User>>


}