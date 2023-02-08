package com.example.quizapp.data.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "Email")
    val email: String,
    @ColumnInfo(name = "UserName")
    val userName: String,
    @ColumnInfo(name = "Password")
    val password: String
)
