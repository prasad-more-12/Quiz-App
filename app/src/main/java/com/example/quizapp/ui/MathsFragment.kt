package com.example.quizapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
import com.example.quizapp.data.Maths
import com.example.quizapp.data.QuizQuestions
import com.example.quizapp.databinding.FragmentMathsBinding
import com.example.quizapp.ui.viewmodels.MathsViewModel
import com.example.quizapp.ui.viewmodels.MathsViewModelFactory

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
        viewModel.questionIndex.observe(viewLifecycleOwner) { newQuestionIndex ->
            binding.tvQuestionNumber.text =
                getString(R.string.current_Question, newQuestionIndex + 1)

            Log.i("Index", "Current Question: " + (newQuestionIndex + 1).toString());
        }
        setUpQuestions(viewModel.getQuizQuestions())

        binding.btnNextQuestionLiterature.setOnClickListener {
            if (viewModel.questionIndex.value!! < 9) {
                isAnswerCorrect(viewModel.getQuizQuestions())
                binding.radioGroup.check(binding.radioButton1.id)
                displayNextQuestion()
            }
            if (viewModel.questionIndex.value == 9) {
                binding.btnNextQuestionLiterature.text = getText(R.string.finish)
            }
        }
    }

    private fun isAnswerCorrect(quizQuestions: QuizQuestions) {
        val radioButtonId = binding.radioGroup.checkedRadioButtonId
        val radioButton: RadioButton = binding.radioGroup.findViewById(radioButtonId)
        Log.i("IsCorrect", "${radioButton.text} ${quizQuestions.correctAnswer}")
        if (radioButton.text.toString() == quizQuestions.correctAnswer) viewModel.correctAnswerPoint()
        Log.i("correctAnsCount", viewModel.correctAnswer.value.toString())

    }

    private fun setUpQuestions(quizQuestions: QuizQuestions) {
        binding.tvQuestion.text = quizQuestions.question
        binding.radioButton1.text = quizQuestions.options[0]
        binding.radioButton2.text = quizQuestions.options[1]
        binding.radioButton3.text = quizQuestions.options[2]
        binding.radioButton4.text = quizQuestions.options[3]

    }

    private fun displayNextQuestion() {
        viewModel.getNextQuestion()
        setUpQuestions(viewModel.getQuizQuestions())
    }

}