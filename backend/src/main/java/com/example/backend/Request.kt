package com.example.backend

class Request(
    val operation: Operation,
    val path: String,
    val body: String? = null,
)

enum class Operation {
    POST, GET
}
