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

class CreateCat(private val writer: Writer, private val reader: Reader, private val clock: Clock, private val repo: CatRepository) {
    fun create() {
        writer.write("Please enter a UUID")
        val catID = reader.read()
        catID.takeUnless {
            !it.isNullOrEmpty() && !it.isNullOrBlank()
        }?.let {
            throw RuntimeException("You must introduce a ID")
        }
        writer.write("Please enter a name")
        val name = reader.read()
        name.takeUnless {
            !it.isNullOrEmpty() && !it.isNullOrBlank()
        }?.let {
            throw RuntimeException("You must introduce a name")
        }
        writer.write("Is he/she vaccinated? true/false")
        val isVaccinated = reader.read()
        isVaccinated.takeUnless {
            !it.isNullOrEmpty() && !it.isNullOrBlank()
        }?.let {
            throw RuntimeException("You must introduce a default value")
        }


        val cat = Cat(
            id =  UUID.fromString(catID),
            name = name.toString(),
            isVaccinated = isVaccinated.toBoolean(),
            createAt = clock.now()
        )
        repo.save(cat)
    }
}