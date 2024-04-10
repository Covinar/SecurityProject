package com.example.data.datasources.local

import com.example.data.db.UserEntity
import kotlinx.coroutines.flow.Flow

interface UsersDataSource {

    fun insertUser(user: UserEntity)

    fun getUser(username: String, password: String): UserEntity?

}