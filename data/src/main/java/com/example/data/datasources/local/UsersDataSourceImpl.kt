package com.example.data.datasources.local

import com.example.data.db.Dao
import com.example.data.db.UserEntity
import kotlinx.coroutines.flow.Flow

class UsersDataSourceImpl(
    private val dao: Dao
) : UsersDataSource {

    override fun insertUser(user: UserEntity) {
        dao.insertUser(user)
    }

    override fun getUser(username: String, password: String): Flow<UserEntity> {
        return dao.getUser(username, password)
    }

}