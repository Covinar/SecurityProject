package com.example.domain.usecases

import com.example.domain.common.Resource
import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow

interface SignInUseCase {

    operator fun invoke(username: String, password: String): Flow<Resource<User>>

}