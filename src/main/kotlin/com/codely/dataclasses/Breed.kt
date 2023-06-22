package com.codely.dataclasses

data class Breed(val value: String) {
    companion object {
        fun from(value: String?) = if (value.isNullOrEmpty() || value.isBlank()) {
            throw InvalidBreed("Empty or blank value")
        } else {
            Breed(value)
        }
    }
}
