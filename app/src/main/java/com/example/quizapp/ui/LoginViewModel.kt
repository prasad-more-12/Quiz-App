package com.example.quizapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.quizapp.data.Repository
import com.example.quizapp.data.local.AppDatabase
import com.example.quizapp.data.local.User
import kotlinx.coroutines.flow.Flow

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    init {
        val userDao = AppDatabase.getDatabase(application).getUserDao()
        repository = Repository(userDao)
    }

    fun checkIfUserIsRegistered(userName: String, password: String):Flow<List<User>> {
        return repository.checkIfUserIsRegistered(userName, password)
    }


}

