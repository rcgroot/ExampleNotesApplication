package com.example.backend_mock

import android.content.Context
import android.os.SystemClock
import com.example.backend.BackendCommunication
import com.example.backend.Request
import com.example.backend.Response
import org.intellij.lang.annotations.Language

private const val MOCK_NETWORK_DELAY = 750L

class MockBackendCommunication(private val context: Context) : BackendCommunication {

    override fun execute(request: Request): Response =
        responses.getOrElse(request.path) { notFound }.also {
            SystemClock.sleep(MOCK_NETWORK_DELAY)
        }
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
