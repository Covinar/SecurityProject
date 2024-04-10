package com.example.data.gateways

import com.example.data.common.MD5Encrypt
import com.example.data.datasources.local.UsersDataSource
import com.example.data.db.UserEntity
import com.example.domain.common.Resource
import com.example.domain.gateways.AuthGateway
import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthGatewayImpl(
    private val usersDataSource: UsersDataSource,
    private val mD5Encrypt: MD5Encrypt
) : AuthGateway {

    override fun signIn(username: String, password: String): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading)
            val user = usersDataSource.getUser(username, mD5Encrypt.encrypt(password))
            if (user == null) {
                emit(Resource.Error(IllegalArgumentException("User not found")))
            } else {
                emit(Resource.Success(User(user.username)))
            }
        }
    }

    override fun signUp(username: String, password: String): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading)
            val user = usersDataSource.getUser(username, mD5Encrypt.encrypt(password))
            if (user == null) {
                usersDataSource.insertUser(UserEntity(username, mD5Encrypt.encrypt(password)))
                emit(Resource.Success(User(username)))
            } else {
                emit(Resource.Error(IllegalArgumentException("User is already exist")))
            }
        }
    }

}