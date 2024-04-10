package com.example.domain.gateways

import com.example.domain.common.Resource
import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow

interface AuthGateway {

    fun signIn(username: String, password: String): Flow<Resource<User>>

    fun signUp(username: String, password: String): Flow<Resource<User>>

}