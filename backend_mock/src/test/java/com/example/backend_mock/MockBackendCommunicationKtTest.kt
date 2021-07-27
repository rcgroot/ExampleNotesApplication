package com.example.backend_mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MockBackendCommunicationKtTest {

    @Test
    fun testGetRoot() {
        val result = get("/")

        assertThat(result.status).isEqualTo(200)
    }

    @Test
    fun testGetNoteList() {
        val result = get("/notes")

        assertThat(result.status).isEqualTo(200)
    }

    @Test
    fun testGetNoteDetails() {
        val result = get("/notes/1")

        assertThat(result.status).isEqualTo(200)
        assertThat(result.body).isNotEmpty
    }

    @Test
    fun testGetFailingNoteDetails() {
        val result = get("/notes/111")

        assertThat(result.status).isEqualTo(404)
    }

    @Test
    fun testGetGarbage() {
        val result = get("adsdas/111")

        assertThat(result.status).isEqualTo(404)
    }
}