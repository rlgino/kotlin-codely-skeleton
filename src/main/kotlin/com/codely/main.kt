package com.codely

import com.codely.dataclasses.*

fun main() {
    try {
        CatCreator(Writer(), Reader(), Clock(), InMemoryCatList(), BreedSearcher(InMemoryBreedRepository())).create()
    } catch (exc: CatCreationException) {
        when(exc) {
            is InvalidBirthday -> println(exc.message)
            is InvalidName -> println(exc.message)
            is InvalidID -> println(exc.message)
            is InvalidVaccinated -> println(exc.message)
            is InvalidBreed -> println(exc.message)
        }
    }
}