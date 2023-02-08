package com.example.quizapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.quizapp.QuizApplication
import com.example.quizapp.R
import com.example.quizapp.data.DataStore
import com.example.quizapp.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegisterFragment : Fragment() {
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var userDataStore: DataStore
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btRegister.setOnClickListener {
            addNewUser()
        }
    }

    private fun isValid(): Boolean {
        return viewModel.isUserValid(
            binding.tielEmail.text.toString().trim(),
            binding.tietUsername.text.toString().trim(),
            binding.tielPassword.text.toString().trim()
        )
    }

    private fun addNewUser() {
        lifecycle.coroutineScope.launch {
            viewModel.checkIfUserAlreadyExist(binding.tietUsername.text.toString().trim())
                .collect() {
                    if (it.isEmpty()) {
                        if (isValid()) {
                            if (isEmailValid(binding.tielEmail.text.toString().trim())) {
                                viewModel.addUser(
                                    binding.tielEmail.text.toString().trim(),
                                    binding.tietUsername.text.toString().trim(),
                                    binding.tielPassword.text.toString().trim()
                                )
                                userDataStore = DataStore(requireContext())
                                lifecycleScope.launch {
                                    userDataStore.saveUserName(
                                        binding.tietUsername.text.toString().trim(),
                                        requireContext()
                                    )
                                }

                                val action =
                                    RegisterFragmentDirections.actionRegisterFragmentToHomeFragment()
                                findNavController().navigate(action)
                                Toast.makeText(context, "User Registered", Toast.LENGTH_LONG)
                                    .show()


                            } else {
                                Toast.makeText(context, "Enter a valid email", Toast.LENGTH_LONG)
                                    .show()
                            }
                        } else {
                            Toast.makeText(context, "User details are empty", Toast.LENGTH_LONG)
                                .show()
                        }
                    } else {
                        Toast.makeText(context, "User already exists", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }
}