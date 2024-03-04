package com.example.quizapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
import com.example.quizapp.data.QuizQuestions
import com.example.quizapp.databinding.FragmentLiteratureBinding
import com.example.quizapp.ui.viewmodels.LiteratureViewModel
import com.example.quizapp.ui.viewmodels.LiteratureViewModelFactory


class LiteratureFragment : Fragment() {
    private var _binding: FragmentLiteratureBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LiteratureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLiteratureBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, LiteratureViewModelFactory(requireContext())).get(
            LiteratureViewModel::class.java
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.questionIndex.observe(viewLifecycleOwner) { newQuestionIndex ->
            binding.tvQuestionNumber.text = "Current Question: " + (newQuestionIndex + 1).toString()
            Log.i("Index","Current Question: " + (newQuestionIndex + 1).toString());
        }
        setUpQuestions(viewModel.getQuizQuestions())

        binding.btnNextQuestionLiterature.setOnClickListener{
            displayNextQuestion()
        }
    }

    private fun setUpQuestions(quizQuestions: QuizQuestions) {
        binding.tvQuestion.text=quizQuestions.question
        binding.radioButton1.text=quizQuestions.options[0]
        binding.radioButton2.text=quizQuestions.options[1]
        binding.radioButton3.text=quizQuestions.options[2]
        binding.radioButton4.text=quizQuestions.options[3]

    }
    private fun displayNextQuestion(){
        viewModel._questionIndex.value=(viewModel._questionIndex.value?:0)+1;
        setUpQuestions(viewModel.getQuizQuestions())
    }

}