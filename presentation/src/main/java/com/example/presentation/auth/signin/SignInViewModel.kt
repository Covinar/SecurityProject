package com.example.presentation.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Resource
import com.example.domain.models.User
import com.example.domain.usecases.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    fun signIn(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) return
        signInUseCase(username, password)
            .flowOn(Dispatchers.IO)
            .onEach {
                when (it) {
                    is Resource.Error -> eventChannel.send(Event.ShowError)
                    Resource.Loading -> eventChannel.send(Event.ShowLoading)
                    is Resource.Success -> eventChannel.send(Event.NavigateToMain(it.model))
                }
            }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }

    sealed class Event {
        data class NavigateToMain(val user: User): Event()
        object ShowError: Event()
        object ShowLoading: Event()
    }

}