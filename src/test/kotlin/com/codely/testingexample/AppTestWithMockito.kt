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
        /*
        when() requires an argument which has to be 'a method call on a mock'.
        For example:
            when(mock.getArticles()).thenReturn(articles);
         */
        doNothing().`when`(writer).write(any())

        app.execute()

        verify(writer).write("The age is 27 years")
    }
}