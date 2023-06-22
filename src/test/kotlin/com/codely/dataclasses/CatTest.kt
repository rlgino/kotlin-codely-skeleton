package com.codely.dataclasses

import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class CatTest {
    @Test
    fun `must vaccinated cat`(){
        val id = ID.from("69919846-7d7e-4f43-89a6-97c3627e0f2c")
        val name = Name.from("pepe")
        val today = LocalDate.now()
        val breed = Breed.from("Test")
        val result = Cat.vaccinate(id, name, "BLACK", breed,today)

        val expected = Cat(id, name, IsVaccinated(true), Cat.Color.BLACK, breed, today)
        assertEquals(expected, result)
    }
}