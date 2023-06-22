package com.codely.dataclasses

import kotlinx.coroutines.*
import java.time.LocalDate
import java.util.*


open class Reader(){
    fun read()= readlnOrNull()
}

open class Writer() {
    fun write(msg: String) = println(msg)
}

class Clock {
    fun now() = LocalDate.now()
}

/*
Corrutinas

¿Qué son las corrutinas?, se trata de bloques de código que se pueden suspender
pero que no estan “atados” a ningún hilo de ejecución en particular.
En Kotlin las podemos usar gracias a una librería de Jetbrains kotlinx.coroutines
 */
class CatCreator(
    private val writer: Writer,
    private val reader: Reader,
    private val clock: Clock,
    private val repo: CatRepository,
    private val breedSearcher: BreedSearcher
) {
    fun create(): Cat {
        val breedList = loadBreedAsync()
        val catID = ID.from(obtainValue("Please enter a UUID"))
        val name = Name.from(obtainValue("Please enter a name"))
        val isVaccinated = IsVaccinated.from(obtainValue("Is he/she vaccinated? true/false"))

        /*
        Una corrutina puede suspenderse y reiniciarse en cualquier thread
         */
        return runBlocking {
            val breed = obtainBreed(breedList.await())
            Cat.from(
                id =  catID,
                name = name,
                isVaccinated = isVaccinated,
                color = Cat.Color.BLACK,
                breed = breed,
                createAt = clock.now()
            ).apply {
                repo.save(this)
            }.also {
                writer.write("The new cat has been created: $it")
            }
        }
    }
    private fun obtainBreed(breedList: List<String>): Breed {
        return Breed.from(obtainValue("Select from the list: $breedList"))
    }


    /*
    ¿Qué son las corrutinas?, se trata de bloques de código que se pueden suspender
    pero que no estan “atados” a ningún hilo de ejecución en particular.
    En Kotlin las podemos usar gracias a una librería de Jetbrains kotlinx.coroutines

    La función tiene que estar marcada como suspend, además tenemos que llamar
    al lauch desde dentro de un runBlocking
     */
    @OptIn(DelicateCoroutinesApi::class)
    private fun loadBreedAsync(): Deferred<List<String>> {
        writer.write("Reading list of breeds")
        return GlobalScope.async {
            val list = breedSearcher.Search().map { it }
            writer.write("All breeds listed!!")
            list
        }
    }

    fun obtainValue(msg: String) = writer.write(msg).run { reader.read() }
}