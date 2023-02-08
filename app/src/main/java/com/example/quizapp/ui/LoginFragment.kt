package com.example.quizapp.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.quizapp.data.DataStore
import com.example.quizapp.data.local.User
import com.example.quizapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
    
    private lateinit var users: List<User>
    private lateinit var userDataStore: DataStore
    private val viewModel: LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
      userDataStore = DataStore(requireContext())
        userDataStore.userNameFlow.asLiveData(Dispatchers.Main).observe(viewLifecycleOwner) {
            if (it != "") {
                Log.v("Login",it)
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                findNavController().navigate(action)
            }else
            {
                binding.constraint.visibility=View.VISIBLE
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvSignUp.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                findNavController().navigate(action)
            }
            btLogin.setOnClickListener {
                checkUserExist()

            }
        }
    }

    private fun isValid(): Boolean {
        if (binding.tietUsername.text.toString().trim().isEmpty()) {
            Toast.makeText(context, "User Name is empty", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.tietPassword.text.toString().trim().isEmpty()) {
            Toast.makeText(context, "Password is empty", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun checkUserExist() {
        if (isValid()) {
            lifecycle.coroutineScope.launch {
                viewModel.checkIfUserIsRegistered(
                    binding.tietUsername.text.toString().trim(),
                    binding.tietPassword.text.toString().trim()
                )
                    .collect() {
                        users = it
                        verifyLogin(users)
                    }
            }
        }
    }

    private fun verifyLogin(users: List<User>) {
        val userName = binding.tietUsername.text.toString().trim()
        val password = binding.tietPassword.text.toString().trim()

        for (currentUser in users) {
            if (currentUser.userName == userName && currentUser.password == password) {
                Toast.makeText(context, "Logged In", Toast.LENGTH_SHORT).show()
                userDataStore = DataStore(requireContext())
                lifecycleScope.launch() {
                    userDataStore.saveUserName(currentUser.userName, requireContext())
                }
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                findNavController().navigate(action)
                return
            }

        }
        Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()

    }

}