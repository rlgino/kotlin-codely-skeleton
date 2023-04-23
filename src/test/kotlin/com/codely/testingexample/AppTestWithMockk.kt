package com.codely.testingexample

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class AppTestWithMockk {
    @Test
    fun `should be success with mockk`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val app = AppTest(reader, writer)
        every { reader.read() } returns "2022-04-30"

        app.execute()

        verify {
            writer.write("Please enter a date with the format <yyyy-MM-dd>")
            writer.write("You wrote the date 2022-04-30")
            writer.write("You wrote the date 2022-04-30")
            writer.write("Bye!")
        }
    }
}