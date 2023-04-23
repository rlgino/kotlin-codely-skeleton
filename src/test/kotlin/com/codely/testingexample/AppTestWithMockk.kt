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
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val app = App(writer,reader, clock)
        every { reader.read() } returns "1990-08-31"
        every { clock.now() } returns LocalDate.parse("2021-08-31")

        app.execute()

        verify { writer.write("The difference between the date you wrote an today is 31 years") }
        /*
        1) Writer(#2).write(Please enter a date with the format <yyyy-MM-dd>)
        2) Writer(#2).write(Bye!)
        3) Writer(#2).write(You wrote the date 1990-08-31)
        4) Writer(#2).write(The age is 31 years)
         */
    }
}