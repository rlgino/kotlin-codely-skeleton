package com.codely.testingexample

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDate

class AppTestWithMockk {
    @Test
    fun `should calculate the difference and return 31 years`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)// Omit lines to mock
        val clock = mockk<Clock>()
        val app = App(writer,reader, clock)
        every { reader.read() } returns "1990-08-31"
        every { clock.now() } returns LocalDate.parse("2021-08-31")

        app.execute()

        verify { writer.write("The age is 31 years") }
    }
}