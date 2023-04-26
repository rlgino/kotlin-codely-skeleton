package com.codely.dataclasses

import java.time.LocalDate
import java.util.UUID

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
data class Cat(val id: UUID, val name: String, val isVaccinated: Boolean, val createAt: LocalDate) {
    companion object {
        /*
Companion objects
Se trata de declarar un objeto dentro de una clase con la palabra reservada "companion"
para que se pueda llamar a sus funciones usando solo el nombre de la clase, pero, como
dice el título, ¿son métodos estáticos?, esto es lo que hablaremos en el video aunque
si quereís un spoiler echad un vistazo aquí:
(https://pro.codely.com/library/introduccion-a-kotlin-tu-primera-app-174088/381069/path/step/151059335/#:~:text=echad%20un%20vistazo-,aqu%C3%AD,-Pod%C3%A9is%20ver%20el)
         */
        fun vaccinate(id: UUID, name: String, createAt: LocalDate): Cat {
            return Cat(
                id = id,
                name = name,
                isVaccinated = true,
                createAt = createAt,
            )
        }
        fun notVaccinate(id: UUID, name: String, createAt: LocalDate): Cat {
            return Cat(
                id = id,
                name = name,
                isVaccinated = false,
                createAt = createAt,
            )
        }
    }
}

interface CatRepository {
    fun save(cat: Cat)
    fun listAll(): List<Cat>
}