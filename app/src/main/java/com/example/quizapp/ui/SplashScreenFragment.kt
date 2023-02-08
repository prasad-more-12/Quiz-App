package com.example.quizapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.data.DataStore
import com.example.quizapp.databinding.FragmentLoginBinding
import com.example.quizapp.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {
    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var handler: Handler
    private lateinit var userDataStore:DataStore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userDataStore = DataStore(requireContext())

        handler= Handler(Looper.myLooper()!!)
        handler.postDelayed({
            userDataStore.userNameFlow.asLiveData().observe(viewLifecycleOwner) {
                if (it != "") {
                    val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToHomeFragment()
                    findNavController().navigate(action)
                }
                else{
                    // Delay and Start Activity
                    findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
                }
            }

        } , 1500)
    }
}