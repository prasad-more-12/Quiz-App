package com.example.quizapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.quizapp.R
import com.example.quizapp.data.Maths
import com.example.quizapp.databinding.FragmentMathsBinding
import com.example.quizapp.databinding.FragmentQuizOptionBinding
import kotlin.text.Typography.quote

class MathsFragment : Fragment() {
    private var _binding: FragmentMathsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MathsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMathsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(
            this,
            MathsViewModelFactory(requireContext())
        ).get(MathsViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.questionIndex.observe(viewLifecycleOwner, Observer { newQuestionIndex ->
            binding.tvQuestionNumber.text = "Current Question: " + (newQuestionIndex + 1).toString()

        })
//        viewModel.correctAnswer.observe(viewLifecycleOwner, Observer { ans ->
//            Log.i("Ans" ,ans.toString())
//        })

        setMathsQuestion(viewModel.getMathQuestion())

        binding.btnNextQuestionMath.setOnClickListener {
            if (binding.tietEnterAnswerMath.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Answer should not be empty", Toast.LENGTH_SHORT).show()
            }
            val questions = viewModel.getMathQuestion()
            if (questions.answer == binding.tietEnterAnswerMath.text.toString().trim()) {
                viewModel.onCorrect()
            }

            if (binding.btnNextQuestionMath.text.toString() == "Next") {
                displayNextQuestion()
            }
        }
          viewModel.index = 1

    }

    private fun displayNextQuestion() {
        if (viewModel.index < 5) {
            setMathsQuestion(viewModel.getNextMathQuestion())
        } else {
            binding.btnNextQuestionMath.text = "Finish"
        }
        binding.tietEnterAnswerMath.text?.clear()
    }

    private fun setMathsQuestion(maths: Maths) {
//        if (binding.tietEnterAnswerMath.text.toString().trim().isNotEmpty()) {
//            viewModel.checkAnswer(
//                binding.tietEnterAnswerMath.text.toString().trim(),
//                maths.answer.trim()
//            )}
        Log.i(
            "Ans",
            maths.answer.trim() + " " + binding.tietEnterAnswerMath.text.toString()
                .trim()
        )

        binding.tvQuestion.text = maths.question
    }
}