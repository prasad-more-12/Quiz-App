package com.example.quizapp

import android.content.Context
import com.example.quizapp.data.Maths
import com.google.gson.Gson

class QuizUtils {
    companion object{
        fun fetchQuestions(context: Context, subject: String): Array<Maths> {
            val inputStream = context.assets.open(subject)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, Charsets.UTF_8)
            val gson = Gson()
            return gson.fromJson(json, Array<Maths>::class.java)
        }
    }
}