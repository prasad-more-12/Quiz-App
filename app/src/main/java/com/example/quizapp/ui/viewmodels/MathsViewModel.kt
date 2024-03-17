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

class MathsViewModel(private val context: Context) : ViewModel() {
    private var mathsQuestionList: List<QuizQuestions> = emptyList()

    //    var index=0;
    private val _questionIndex = MutableLiveData<Int>()
    val questionIndex: LiveData<Int>
        get() = _questionIndex

    private val _correctAnswer = MutableLiveData<Int>()
    val correctAnswer: LiveData<Int>
        get() = _correctAnswer

    init {
        mathsQuestionList = QuizUtils.getSubjectQuestions(context, "Math")
        Log.i("Math", mathsQuestionList.toString());
        _questionIndex.value = 0
        _correctAnswer.value = 0
    }

    fun getQuizQuestions(): QuizQuestions {
        return mathsQuestionList[_questionIndex.value!!]
    }
    fun correctAnswerPoint(){
        _correctAnswer.value=(_correctAnswer.value)?.plus(1);
    }
    fun getNextQuestion(){
        _questionIndex.value=(_questionIndex.value)?.plus(1);
    }

}

class MathsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MathsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MathsViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}