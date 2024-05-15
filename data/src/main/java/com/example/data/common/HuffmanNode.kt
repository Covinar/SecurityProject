package com.example.data.common

data class HuffmanNode(
    val char: Char?,
    val frequency: Int,
    val left: HuffmanNode? = null,
    val right: HuffmanNode? = null
)
