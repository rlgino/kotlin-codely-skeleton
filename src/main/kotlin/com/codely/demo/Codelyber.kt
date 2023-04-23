package com.codely.demo

import java.time.LocalDate
import java.time.Period
import kotlin.system.exitProcess

fun Codelyber() {
    // https://kotlinlang.org/docs/scope-functions.html
    println("Set the date in format <yyyy-MM-dd>")
    readlnOrNull().takeIf {// return string? it means could be null
        !it.isNullOrBlank() && it.isNotEmpty()
    }?.let { line ->
        /*¿Qué devuelve? El resultado de ejecutar el código definido dentro
        ¿Cómo se usa el input? Mediante it
        ¿Cuándo es útil? En muchas ocasiones pero especialmente cuando tenemos
            un código que solo debemos ejecutar si un valor nullable no es null
         */
        println("You wrote $line")
        LocalDate.parse(line)
    }.apply {// Son pasamanos
        // Las funciones also y apply son muy interesantes, ambas devuelven el
        // context object del bloque de código.
        // En la primera usaremos it para hacer referencia al context object,
        // mientras que, para la segunda usaremos this.
        if (this == null) {
            println("Invalid date")
            exitProcess(1)
        }
    }?.also {// Son pasamanos
        println("You wrote $it")
    }?.run {
        // La función run devuelve el resultado de la ejecuón del bloque de código
        // que definimos dentro, pero a diferencia del resto, no tiene context object
        // por lo que no se usa ni it ni this.
        calculateDifference()
    }

}

private fun LocalDate.calculateDifference() = with(Period.between(this, LocalDate.now())) {
    // With
    /*
    ¿Qué devuelve? El resultado de ejecutar el código definido dentro
    ¿Cómo se usa el input? Mediante this, es importante mencionar que esta función recibe el objeto como un argumento
    ¿Cúando es útil? Cuando queremos llevar a cabo varias acciones utilizando un mismo objeto
     */
    when { // Es como un Switch
        years > 0 -> println("The age is ${this.years} years")
        months > 0 -> println("The age is ${this.years} months")
        days > 0 -> println("The age is ${this.days} days")
    }
}
