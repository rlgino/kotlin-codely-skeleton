package com.codely.dataclasses

class BreedSearcher(val repository: BreedRepository) {
    fun Search(): List<String> {
        return repository.findAll()
    }
}