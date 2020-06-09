package model.people

import java.text.SimpleDateFormat
import java.util.*

private val dateFormatter = SimpleDateFormat.getTimeInstance()

class Employee(
  id: String,
  firstName: String,
  lastName: String,
  email: String,
  phoneNumber: String,
  val salary: Double,
  val socialSecurityNumber: String,
  val hireDate: String
) : Person(id = id, firstName = firstName, lastName = lastName, email = email, phoneNumber = phoneNumber) {

  override fun toString(): String {
    return "$lastName, $firstName, contact at: $email / $phoneNumber"
  }

  /**
   * Prints a time of clocking in, in a nice format.
   *
   * Hint: to get time, you can create a `Date` object. Use SimpleDateFormatter to format the time!
   * */
  fun clockIn() {
    val time = dateFormatter.format(Date())

    println("Clocking in at $time")
  }

  fun clockOut() {
    val time = dateFormatter.format(Date())

    println("Clocking out at $time")
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Employee

    if (salary != other.salary) return false
    if (socialSecurityNumber != other.socialSecurityNumber) return false
    if (hireDate != other.hireDate) return false

    return true
  }

  override fun hashCode(): Int {
    var result = salary.hashCode()
    result = 31 * result + socialSecurityNumber.hashCode()
    result = 31 * result + hireDate.hashCode()
    return result
  }
}