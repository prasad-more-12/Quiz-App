package com.example.quizapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.data.DataStore
import com.example.quizapp.databinding.FragmentHomeBinding
import com.example.quizapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var userDataStore: DataStore

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        userDataStore = DataStore(requireContext())
        userDataStore.userNameFlow.asLiveData().observe(viewLifecycleOwner) {
            if (it != "") {
                binding.tvUser.text = it
            }
            else {
                val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            cvQuiz.setOnClickListener() {
                val action = HomeFragmentDirections.actionHomeFragmentToQuizOptionFragment()
                findNavController().navigate(action)
            }

            cvRules.setOnClickListener() {
                val action = HomeFragmentDirections.actionHomeFragmentToRulesFragment()
                findNavController().navigate(action)
            }

            cvHistory.setOnClickListener() {
                val action = HomeFragmentDirections.actionHomeFragmentToHistoryFragment()
                findNavController().navigate(action)
            }

            cvEditPassword.setOnClickListener() {
                val action = HomeFragmentDirections.actionHomeFragmentToEditPasswordFragment()
                findNavController().navigate(action)
            }

            cvSignOut.setOnClickListener() {
//                userDataStore = DataStore(requireContext())
//                lifecycleScope.launch(Dispatchers.Main) {
//                    userDataStore.clearUserName(requireContext())
//               }
                val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }

    }
}