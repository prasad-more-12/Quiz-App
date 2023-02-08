package com.example.quizapp

import android.app.Application
import com.example.quizapp.data.local.AppDatabase

class QuizApplication : Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
}