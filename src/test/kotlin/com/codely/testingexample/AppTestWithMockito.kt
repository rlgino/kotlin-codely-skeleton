package com.codely.testingexample

import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class AppTestWithMockito {
    @Test
    fun `Should be success`(){
        val reader= mock<Reader>()
        val writer= mock<Writer>()
        val app=AppTest(reader, writer)
        whenever(reader.read()).thenReturn("2022-04-30")
        doNothing().`when`(writer).write(any())

        app.execute()

        verify(writer).write("The age is 27 years")
    }
}