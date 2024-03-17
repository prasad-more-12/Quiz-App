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

class LiteratureViewModel(private val context: Context) : ViewModel() {
    private var literatureQuestionList: List<QuizQuestions> = emptyList()

    //    var index=0;
    private val _questionIndex = MutableLiveData<Int>()
    val questionIndex: LiveData<Int>
        get() = _questionIndex

    private val _correctAnswer = MutableLiveData<Int>()
    val correctAnswer: LiveData<Int>
        get() = _correctAnswer

    init {
        literatureQuestionList = QuizUtils.getSubjectQuestions(context, "Literature")
        Log.i("Literature", literatureQuestionList.toString());
        _questionIndex.value = 0
        _correctAnswer.value = 0
    }

    fun getQuizQuestions(): QuizQuestions {
        return literatureQuestionList[_questionIndex.value!!]
    }
    fun correctAnswerPoint(){
       _correctAnswer.value=(_correctAnswer.value)?.plus(1);
    }
    fun getNextQuestion(){
        _questionIndex.value=(_questionIndex.value)?.plus(1);
    }
}

class LiteratureViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LiteratureViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LiteratureViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}