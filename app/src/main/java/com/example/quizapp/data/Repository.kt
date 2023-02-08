package com.example.quizapp.data

import com.example.quizapp.data.local.User
import com.example.quizapp.data.local.UserDao
import kotlinx.coroutines.flow.Flow

class Repository(private val dao: UserDao) {

    suspend fun insertUser(user: User){
        dao.insertUser(user)
    }

    fun checkIfUserIsRegistered(username: String, password: String): Flow<List<User>>{
        return dao.checkIfUserIsRegistered(username,password)
    }

    fun checkIfUserAlreadyExist(username: String):Flow<List<User>>{
        return dao.checkIfUserAlreadyExist(username)
    }

}