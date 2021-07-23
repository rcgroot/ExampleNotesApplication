package com.example.backend

data class Request(
    val operation: Operation,
    val path: String,
    val body: String? = null,
)

enum class Operation {
    POST, GET
}
