package com.codely.dataclasses

import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*
import kotlin.math.exp
import kotlin.test.assertEquals

class CatTest {
    @Test
    fun `must vaccinated cat`(){
        val id = UUID.fromString("69919846-7d7e-4f43-89a6-97c3627e0f2c")
        val name = "pepe"
        val today = LocalDate.now()
        val result = Cat.vaccinate(id, name, "BLACK",today)

        val expected = Cat(id, name, true, Cat.Color.BLACK,today)
        assertEquals(expected, result)
    }
}