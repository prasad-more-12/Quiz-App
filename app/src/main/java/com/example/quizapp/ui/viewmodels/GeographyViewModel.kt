package com.example.quizapp.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.QuizUtils
import com.example.quizapp.data.QuizQuestions
import java.lang.IllegalArgumentException

class GeographyViewModel(private val context: Context) : ViewModel() {
    private var geographyQuestionList: List<QuizQuestions> = emptyList()

    private val _questionIndex = MutableLiveData<Int>()
    val questionIndex: LiveData<Int>
        get() = _questionIndex

    private val _correctAnswer = MutableLiveData<Int>()
    val correctAnswer: LiveData<Int>
        get() = _correctAnswer

    init {
        geographyQuestionList = QuizUtils.getSubjectQuestions(context, "Geography")
        Log.i("Literature", geographyQuestionList.toString());
        _questionIndex.value = 0
        _correctAnswer.value = 0
    }

    fun getQuizQuestions(): QuizQuestions {
        return geographyQuestionList[_questionIndex.value!!]
    }
    fun correctAnswerPoint(){
        _correctAnswer.value=(_correctAnswer.value)?.plus(1);
    }
    fun getNextQuestion(){
        _questionIndex.value=(_questionIndex.value)?.plus(1);
    }
}

class GeographyViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GeographyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GeographyViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}