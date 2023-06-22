package com.codely.dataclasses

import java.lang.RuntimeException
import java.time.LocalDate
import java.util.*


open class Reader(){
    fun read()= readLine()
}

open class Writer() {
    fun write(msg: String) = println(msg)
}

class Clock {
    fun now() = LocalDate.now()
}

class CatCreator(private val writer: Writer, private val reader: Reader, private val clock: Clock, private val repo: CatRepository) {
    fun create(): Cat {
        val catID = ID.from(obtainValue("Please enter a UUID"))
        val name = Name.from(obtainValue("Please enter a name"))
        val isVaccinated = IsVaccinated.from(obtainValue("Is he/she vaccinated? true/false"))

        return Cat.from(
            id =  catID,
            name = name,
            isVaccinated = isVaccinated,
            color = Cat.Color.BLACK,
            createAt = clock.now()
        ).apply {
            repo.save(this)
        }.also {
            writer.write("The new cat has been created with ID: " + it.id)
        }
    }

    fun obtainValue(msg: String) = writer.write(msg).run { reader.read() }
}