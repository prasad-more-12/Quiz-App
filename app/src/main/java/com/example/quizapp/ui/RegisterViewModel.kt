package com.example.quizapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quizapp.data.Repository
import com.example.quizapp.data.local.AppDatabase
import com.example.quizapp.data.local.User
import com.example.quizapp.data.local.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository

    init {
        val userDao = AppDatabase.getDatabase(application).getUserDao()
        repository = Repository(userDao)
    }

    private fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    fun checkIfUserAlreadyExist(userName: String): Flow<List<User>> {
        return repository.checkIfUserAlreadyExist(userName)
    }


    fun isUserValid(email: String, userName: String, password: String): Boolean {
        if (email.isEmpty() || userName.isEmpty() || password.isEmpty()) {
            return false
        }
        return true
    }

    //method to register user in database
    fun addUser(email: String, userName: String, password: String) {
        val newUser = User(email, userName, password)
        insertUser(newUser)
    }



}

