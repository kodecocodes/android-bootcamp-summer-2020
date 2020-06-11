package helper

import model.animals.Cat
import model.caffe.Product
import model.caffe.Receipt
import model.caffe.Sponsorship
import model.people.Employee
import model.people.Patron
import model.shelter.Shelter

val firstShelter = Shelter("101", "Andrea's Fluffy Rascals")
val secondShelter = Shelter("202", "Sam's Playful Kittens")

val firstShelterCats = setOf(
  Cat(
    "1",
    "Spots",
    "F",
    "101",
    mutableSetOf(Sponsorship("91", "1"), Sponsorship("91", "1"))
  ),

  Cat(
    "2",
    "Freckles",
    "M",
    "101",
    mutableSetOf(Sponsorship("92", "1"))
  )
)

val secondShelterCats = setOf(
  Cat(
    "3",
    "Spots",
    "F",
    "202",
    mutableSetOf(Sponsorship("91", "1"), Sponsorship("91", "1"))
  ),

  Cat(
    "4",
    "Freckles",
    "M",
    "202",
    mutableSetOf(Sponsorship("92", "1"))
  )
)

val testEmployees = setOf(
  Employee("91",
    "Mike",
    "Week",
    "mikew@mail.com",
    "+99312349019",
    13000000.0,
    "21391238192381298",
    "17/05/2005"),
  Employee("92",
    "John",
    "Smith",
    "jsmith@mail.com",
    "+81938391303",
    500.0,
    "319238123891",
    "08/06/2020"),
  Employee("93",
    "Jane",
    "Doe",
    "jdoe@mail.com",
    "+389128321",
    50505.0,
    "89138138129312",
    "01/03/2019")
)

val testPatrons = setOf(
  Patron("51",
    "Luka",
    "Kordic",
    "mrluka9@gmail.com",
    "+99312349019"),
  Patron("52",
    "Jennifer",
    "Bailey",
    "reddunrogue@gmail.com",
    "+81938391303"),
  Patron("53",
    "Fuad",
    "Kamal",
    "fuad@anaara.com",
    "+389128321"
  ),
  Patron("54",
    "Filip",
    "Babic",
    "filip@razeware.com",
    "+9319391283")
)

val cola = Product("1", 10.0, "Coca-Cola")
val coffee = Product("2", 5.0, "Coffee")
val espresso = Product("3", 7.0, "Espresso")
val cheeseCake = Product("4", 15.0, "Cheesecake")

val testProducts = setOf(
  cola,
  coffee,
  espresso,
  cheeseCake
)

val testReceiptsOne = mutableSetOf(
  Receipt(
    "1",
    listOf(coffee, coffee, cheeseCake, cheeseCake),
    40.0,
    "54"
  ),

  Receipt(
    "2",
    listOf(coffee, coffee, cheeseCake, cheeseCake),
    40.0,
    "52"
  ),
  Receipt(
    "3",
    listOf(coffee, coffee, cheeseCake, cheeseCake),
    40.0,
    "51"
  ),
  Receipt(
    "4",
    listOf(coffee, coffee, cheeseCake, cheeseCake),
    40.0,
    "53"
  )
)

val testReceiptsTwo = mutableSetOf(
  Receipt(
    "5",
    listOf(cheeseCake, cheeseCake),
    40.0,
    "54"
  ),

  Receipt(
    "6",
    listOf(coffee, coffee, cheeseCake, cheeseCake),
    40.0,
    "92"
  ),
  Receipt(
    "7",
    listOf(coffee, coffee),
    10.0,
    "91"
  )
)