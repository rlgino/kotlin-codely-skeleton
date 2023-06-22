package com.codely.dataclasses

import java.lang.IllegalArgumentException

// All direct subclasses of a sealed class are known at compile time.
// No other subclasses may appear outside the module and package within
// which the sealed class is defined. For example,
// third-party clients can't extend your sealed class in their code.
// Thus, each instance of a sealed class has a type from a
// limited set that is known when this class is compiled.
sealed class CatCreationException(override val message: String?): IllegalArgumentException(message)
//In some sense, sealed classes are similar to enum classes:
// the set of values for an enum type is also restricted,
// but each enum constant exists only as a single instance,
// whereas a subclass of a sealed class can have multiple instances, each with its own state.
class InvalidID(message: String): CatCreationException("Invalid id: $message")
class InvalidName(message: String): CatCreationException("Invalid name: $message")
class InvalidVaccinated(message: String): CatCreationException("Invalid vaccinated: $message")
class InvalidBirthday(message: String): CatCreationException("Invalid birthday: $message")
