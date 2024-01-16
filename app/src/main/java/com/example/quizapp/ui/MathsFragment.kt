package com.example.quizapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.data.Maths
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

        viewModel.questionIndex.observe(viewLifecycleOwner, Observer { newQuestionIndex ->
            binding.tvQuestionNumber.text = "Current Question: " + (newQuestionIndex + 1).toString()


        })
//        viewModel.correctAnswer.observe(viewLifecycleOwner, Observer { ans ->
//            Log.i("Ans" ,ans.toString())
//        })

        setMathsQuestion(viewModel.getMathQuestion())
        binding.btnNextQuestionMath.setOnClickListener {
            Log.i("Index", viewModel.index.toString())
            if (binding.tietEnterAnswerMath.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Answer should not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val questions = viewModel.getMathQuestion()
//            if (true) {
//                viewModel.onCorrect()
            Log.i("ANS", questions.answer + " " + binding.tietEnterAnswerMath.text.toString())
//            }
            if (viewModel.index < 4) {
                displayNextQuestion()
            }
            if (viewModel.index ==3){
                binding.btnNextQuestionMath.text = "Finish"
            }
        }

    }

    private fun displayNextQuestion() {

        viewModel.index++
        setMathsQuestion(viewModel.getNextMathQuestion())
        binding.tietEnterAnswerMath.text?.clear()
    }

    private fun setMathsQuestion(maths: Maths) {
        binding.tvQuestion.text = maths.question
//        if (binding.tietEnterAnswerMath.text.toString().trim().isNotEmpty()) {
//            viewModel.checkAnswer(
//                binding.tietEnterAnswerMath.text.toString().trim(),
//                maths.answer.trim()
//            )}
//        Log.i(
//            "Ans",
//            maths.answer.trim() + " " + binding.tietEnterAnswerMath.text.toString()
//                .trim()
//        )


    }
}