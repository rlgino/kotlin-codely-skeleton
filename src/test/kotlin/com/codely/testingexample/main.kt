package com.codely.testingexample

import java.time.LocalDate

class AppTest(writer: Reader, reader: Writer) : App(reader, writer) {
    override fun currentDate(): LocalDate = LocalDate.parse("2022-04-30")
}