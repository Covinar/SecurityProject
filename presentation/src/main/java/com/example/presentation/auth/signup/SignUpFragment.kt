package com.example.presentation.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding
        get() = _binding ?: throw RuntimeException("FragmentSignUpBinding is null")

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
        collectEvents()
    }

    private fun listeners() = with(binding) {
        btnSingUp.setOnClickListener {
            viewModel.signUp(etUsername.text.toString().trim(), etPassword.text.toString().trim())
        }
    }

    private fun collectEvents() {
        viewModel.eventsFlow
            .onEach { event ->
                when (event) {
                    is SignUpViewModel.Event.NavigateToMain -> {
                        findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToMainFragment())
                    }
                    SignUpViewModel.Event.ShowError -> {
                        Toast.makeText(requireContext(), R.string.user_exist, Toast.LENGTH_SHORT).show()
                    }
                    SignUpViewModel.Event.ShowLoading -> {
                        Toast.makeText(requireContext(), R.string.loading, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}