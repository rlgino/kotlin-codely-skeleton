package com.codely.dataclasses

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*
import kotlin.test.assertEquals

class CreateCatTest {
    @Test
    fun `should calculate the difference and return 31 years`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)// Omit lines to mock
        val clock = mockk<Clock>()
        val today = LocalDate.parse("2021-08-31")
        var repo = InMemoryCatList()
        val app = CatCreator(writer, reader, clock, repo)
        every { reader.read() } returns "69919846-7d7e-4f43-89a6-97c3627e0f2c" andThen "pepe" andThen "true"
        every { clock.now() } returns today

        app.create()

        val expected =
            Cat(UUID.fromString("69919846-7d7e-4f43-89a6-97c3627e0f2c"), "pepe", true, Cat.Color.BLACK, today)
        val result = repo.listAll()[0]
        assertEquals(expected, result)
    }
}