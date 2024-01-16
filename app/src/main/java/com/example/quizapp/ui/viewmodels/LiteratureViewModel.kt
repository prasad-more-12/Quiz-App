package com.example.quizapp.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class LiteratureViewModel:ViewModel() {


}

class LiteratureViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MathsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MathsViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}