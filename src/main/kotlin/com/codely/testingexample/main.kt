package com.codely.testingexample

import java.time.LocalDate
import java.time.Period
import kotlin.system.exitProcess


open class Reader(){
    fun read()= readLine()
}

open class Writer() {
    fun write(msg: String) = println(msg)
}

open class App(private val writer: Writer, private val reader: Reader) {
    fun execute() {
        writer.write("Please enter a date with the format <yyyy-MM-dd>")
        val line = reader.read()
        line.takeUnless {
            !it.isNullOrEmpty() && !it.isNullOrBlank()
        }?.let {
            writer.write("The introduced date <$it> is not valid")
            return
        }
        line.takeUnless {
            it.isNullOrEmpty()
        }?.let {
            LocalDate.parse(it)
        }.apply {
            if (this == null) {
                writer.write("The introduced date <$this> is not valid")
                exitProcess(1)
            }
        }.also {
            writer.write("You wrote the date $it")
        }?.run {
            calculateDifference()
        }

        writer.write("Bye!")
    }

    private fun LocalDate.calculateDifference() = with(Period.between(this, currentDate())) {
        when { // Es como un Switch
            years > 0 -> writer.write("The age is ${this.years} years")
            months > 0 -> writer.write("The age is ${this.years} months")
            days > 0 -> writer.write("The age is ${this.days} days")
        }
    }

    protected open fun currentDate(): LocalDate = LocalDate.now()
}