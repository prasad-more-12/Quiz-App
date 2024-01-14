package com.example.quizapp.data

import com.google.gson.annotations.SerializedName

data class QuizData(
    @SerializedName("subject") val quizSubject: String,
    @SerializedName("questions") val quizQuestions: List<Questions>
)

data class Questions(
    @SerializedName("question") val question: String,
    @SerializedName("options") val options: String,
    @SerializedName("correctAnswer") val correctAnswer: String,
)
