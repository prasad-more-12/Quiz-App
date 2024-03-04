package com.example.quizapp.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.QuizUtils
import com.example.quizapp.data.Maths
import java.lang.IllegalArgumentException

class MathsViewModel(private val context: Context) : ViewModel() {
    private var mathsQuestionsList: Array<Maths> = emptyArray()
    var index=0;
    private val _questionIndex = MutableLiveData<Int>()
    val questionIndex: LiveData<Int>
        get() = _questionIndex

    private val _correctAnswer = MutableLiveData<Int>()
    val correctAnswer: LiveData<Int>
        get() = _correctAnswer

    init {
        mathsQuestionsList = QuizUtils.fetchQuestions(context, "math")
        _questionIndex.value = 0
        _correctAnswer.value = 0
        index=0;
    }

    //  var newList = mathsQuestionsList.toMutableList()
    fun getMathQuestion() = mathsQuestionsList[index]

    fun getNextMathQuestion(): Maths {
        //    newList.shuffle()
        _questionIndex.value = (questionIndex.value)?.plus(1)
        return mathsQuestionsList[index]
        // return newList.removeAt(index++)
    }

    fun onCorrect() {
        _correctAnswer.value = (correctAnswer.value)?.plus(1)
    }
//    fun checkAnswer(input: String, ans: String) {
//        if (input == ans) {
//            _correctAnswer.value = (correctAnswer.value)?.plus(5)
//        } else {
//            _correctAnswer.value = (correctAnswer.value)?.minus(2)
//        }
//    }
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