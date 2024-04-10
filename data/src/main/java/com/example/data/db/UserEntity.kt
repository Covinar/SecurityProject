package com.example.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "users")
data class UserEntity(
    @PrimaryKey
    val username: String,
    @ColumnInfo(name = "Password")
    val password: String
)
