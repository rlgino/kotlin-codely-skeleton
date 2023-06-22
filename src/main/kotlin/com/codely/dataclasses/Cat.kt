package com.codely.dataclasses

import java.time.LocalDate
import java.util.*

/*
DataClasses:
* no pueden ser open
* no pueden ser abstract
* no pueder ser inner
* no puede ser sealed
* tiene que tener al menos 1 atributo en el constructor

Además, para este tipo de clases, el compilador ya nos
proporciona las funciones: copy, toString, equals/hashCode
y componentN(funciones para acceder al valor de cada
atributo de la clase)
 */
data class Cat(
    val id: ID,
    val name: Name,
    val isVaccinated: IsVaccinated,
    val color: Color,
    val breed: Breed,
    val createAt: LocalDate
) {

    enum class Color(code: String){
        BLACK("#000000"), WHITE("#FFFFFF")
    }
    companion object {
        /*
Companion objects
Se trata de declarar un objeto dentro de una clase con la palabra reservada "companion"
para que se pueda llamar a sus funciones usando solo el nombre de la clase, pero, como
dice el título, ¿son métodos estáticos?, esto es lo que hablaremos en el video aunque
si quereís un spoiler echad un vistazo aquí:
(https://pro.codely.com/library/introduccion-a-kotlin-tu-primera-app-174088/381069/path/step/151059335/#:~:text=echad%20un%20vistazo-,aqu%C3%AD,-Pod%C3%A9is%20ver%20el)
         */
        fun from(id: ID, name: Name, isVaccinated: IsVaccinated, color: Color, createAt: LocalDate, breed: Breed): Cat {
            return Cat(id, name, isVaccinated, color, breed, createAt)
        }
        fun vaccinate(id: ID, name: Name, color: String, breed: Breed, createAt: LocalDate): Cat {
            return Cat(
                id = id,
                name = name,
                isVaccinated = IsVaccinated(true),
                color = Color.valueOf(color),
                breed = breed,
                createAt = createAt,
            )
        }
        fun notVaccinate(id: ID, name: Name, color: String, breed: Breed, createAt: LocalDate): Cat {
            return Cat(
                id = id,
                name = name,
                isVaccinated = IsVaccinated(false),
                color = Color.valueOf(color),
                breed = breed,
                createAt = createAt,
            )
        }
    }
}

data class Name(val value: String) {
    companion object{
        fun from(value: String?): Name = if (value.isNullOrEmpty() || value.isBlank()) {
            throw InvalidName("Empty name")
        } else {
            Name(value)
        }
    }
}

data class ID(val value: UUID) {
    companion object{
        fun from(value: String?): ID = if(value.isNullOrEmpty() || value.isBlank()) {
            throw InvalidID("Empty ID")
        } else {
            ID(UUID.fromString(value))
        }
    }
}

data class IsVaccinated(val isVaccinated: Boolean) {
    companion object {
        fun from(value: String?) = if(value.isNullOrEmpty() || value.isBlank()) {
            throw InvalidVaccinated("Empty value")
        } else if(value != "true" && value != "false") {
            throw InvalidVaccinated("value")
        }else{
            IsVaccinated(value == "true")
        }
    }
}

interface CatRepository {
    fun save(cat: Cat)
    fun listAll(): List<Cat>
}