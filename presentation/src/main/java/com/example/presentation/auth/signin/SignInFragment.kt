package com.example.presentation.auth.signin

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
import com.example.presentation.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding
        get() = _binding ?: throw RuntimeException("FragmentSignInBinding is null")

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
        collectEvents()
    }

    private fun listeners() = with(binding) {
        btnSingUp.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
        }
        btnSingIn.setOnClickListener {
            viewModel.signIn(etUsername.text.toString().trim(), etPassword.text.toString().trim())
        }
    }

    private fun collectEvents() {
        viewModel.eventsFlow
            .onEach { event ->
                when (event) {
                    is SignInViewModel.Event.NavigateToMain -> {
                        findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
                    }
                    SignInViewModel.Event.ShowError -> {
                        Toast.makeText(requireContext(), R.string.invalid_cred, Toast.LENGTH_SHORT).show()
                    }
                    SignInViewModel.Event.ShowLoading -> {
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