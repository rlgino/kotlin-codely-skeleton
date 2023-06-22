package com.codely.dataclasses

class InMemoryBreedRepository:BreedRepository {
    override fun findAll(): List<String> {
        Thread.sleep(4_000)
        return listOf("Siames","Nordico", "Tipo3", "Tipo 4")
    }
}