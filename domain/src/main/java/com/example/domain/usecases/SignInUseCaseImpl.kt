package com.example.domain.usecases

import com.example.domain.common.Resource
import com.example.domain.gateways.AuthGateway
import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow

class SignInUseCaseImpl(
    private val authGateway: AuthGateway
) : SignInUseCase {

    override fun invoke(username: String, password: String): Flow<Resource<User>> {
        return authGateway.signIn(username, password)
    }

}