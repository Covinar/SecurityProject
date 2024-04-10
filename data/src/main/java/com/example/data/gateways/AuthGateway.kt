package com.example.data.gateways

import com.example.data.datasources.local.UsersDataSource
import com.example.data.db.Dao
import com.example.data.db.UserEntity
import com.example.domain.common.Resource
import com.example.domain.gateways.AuthGateway
import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

class AuthGateway(
    private val usersDataSource: UsersDataSource
) : AuthGateway {

    override fun signIn(username: String, password: String): Flow<Resource<User>> {
        TODO("Not yet implemented")
    }

    override fun signUp(username: String, password: String): Flow<Resource<User>> {
        TODO("Not yet implemented")
    }

}