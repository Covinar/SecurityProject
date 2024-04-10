package com.example.domain.usecases

import com.example.domain.common.Resource
import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow

interface SignUpUseCase {

    operator fun invoke(username: String, password: String): Flow<Resource<User>>

}