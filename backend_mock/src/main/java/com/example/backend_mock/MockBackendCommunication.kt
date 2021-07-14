package com.example.backend_mock

import android.content.Context
import com.example.backend.BackendCommunication
import com.example.backend.Request
import com.example.backend.Response
import org.intellij.lang.annotations.Language

class MockBackendCommunication(private val context: Context) : BackendCommunication {

    override fun execute(request: Request): Response =
        responses.getOrElse(request.path) { notFound }
}

@Language("Json")
val notes: String = """{ "notes" : [
    {
      "id" : 1,
      "title" : "First note",
      "body": "This this a first note created every in this sample application"
    }
] }"""

val notFound = Response(404, "Not Found")

private val responses = mapOf(
    "/" to notFound,
    "/notes" to Response(200, notes)
)