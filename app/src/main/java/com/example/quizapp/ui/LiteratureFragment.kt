package com.example.quizapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
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
    }
}