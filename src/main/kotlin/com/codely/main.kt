package com.codely

import com.codely.dataclasses.*

fun main() {
    try {
        CatCreator(Writer(), Reader(), Clock(), InMemoryCatList()).create()
    } catch (exc: CatCreationException) {
        when(exc) {
            is InvalidBirthday -> println(exc.message)
            is InvalidName -> println(exc.message)
        }
    }
}