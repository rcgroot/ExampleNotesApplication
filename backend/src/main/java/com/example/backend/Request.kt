package com.example.backend

class Request(
    val operation: Operation,
    val path: String,
    val body: String?,
)

enum class Operation {
    POST, GET
}
