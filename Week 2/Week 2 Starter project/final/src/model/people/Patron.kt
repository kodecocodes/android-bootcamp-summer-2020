package model.people

class Patron(
  id: String,
  firstName: String,
  lastName: String,
  email: String,
  phoneNumber: String
) : Person(id = id, firstName = firstName, lastName = lastName, email = email, phoneNumber = phoneNumber)