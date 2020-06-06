package model.people

import model.animals.Cat
import java.util.*

open class Person(
    val id: String = UUID.randomUUID().toString(), // generates a random string for the ID!
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val cats: MutableSet<Cat> = mutableSetOf() // every person can adopt cats, and as many as they want!
)