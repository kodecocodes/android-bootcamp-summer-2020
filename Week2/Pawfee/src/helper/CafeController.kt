package helper

import model.animals.Cat
import model.caffe.Cafe
import model.caffe.Product
import model.people.Person
import model.shelter.Shelter

class CafeController {

  // cafe related things
  private val cafe = Cafe()

  // shelter related things
  private val shelters = mutableSetOf(firstShelter, secondShelter)
  private val shelterToCat = mutableMapOf(
    firstShelter to firstShelterCats.toMutableSet(),
    secondShelter to secondShelterCats.toMutableSet()
  )

  fun adoptCat(catId: String, person: Person) {
    // check if cats exist, and retrieve its entry!
    val catInShelter = shelterToCat.entries.firstOrNull { (_, catsInShelter) ->
      catsInShelter.any { it.id == catId }
    }

    // you can adopt that cat!
    if (catInShelter != null) {
      val cat = catInShelter.value.first { cat -> cat.id == catId } // find the cat for that ID again

      // remove the cat from the shelter
      catInShelter.value.remove(cat)

      // add the cat to the person
      person.cats.add(cat)

      cafe.addCustomer(person)
    }
  }

  fun sellItems(items: List<Product>, customerId: String) {

    /**
     * Also make sure to print receipt information out & add the receipt to the list of receipts for the current day.
     * You can random the day from a List, or check from the Date object!
     * */
    val receipt = cafe.getReceipt(items, customerId)
    println("You just sold ${receipt.products.map { it.name }} for a total of ${receipt.totalPrice}, to a customer #${receipt.customerId}")
  }

  /**
   * First gets a list of all adopters, then queries shelters.
   *
   * */
  fun getNumberOfAdoptionsPerShelter(): Map<String, Int> {
    val adoptedCats = getAdoptedCats()
    val shelters = shelterToCat.keys

    return shelters.map { shelter -> shelter.name to adoptedCats.count { cat -> cat.shelterId == shelter.id } }.toMap()
  }

  fun getUnadoptedCats(): Set<Cat> = shelterToCat.flatMap { it.value }.toSet()

  fun getAdoptedCats(): Set<Cat> {
    val customers = cafe.getCustomers()
    val adoptedCats = customers.flatMap { it.cats }

    return adoptedCats.toSet()
  }

  fun getSponsoredCats(): Set<Cat> {
    val sponsoredCatIds = cafe.getSponsorships().map { it.catId }
    val unadoptedCats = getUnadoptedCats()

    return unadoptedCats.filter { it.id in sponsoredCatIds }.toSet()
  }

  fun getMostPopularCats(): Set<Cat> {
    val sponsoredCats = getSponsoredCats()
    val mostPopularCats = sponsoredCats.filter { it.sponsorships.size > 5 }

    return mostPopularCats.toSet()
  }

  fun getTopSellingProducts() = cafe.getTopTenSellingItems()

  fun getWorkingEmployees() = cafe.getWorkingEmployees()

  fun getAdopters() = cafe.getAdopters()

  fun showNumberOfNonEmployeeCustomersForDay(day: String) = cafe.showNumberOfNonEmployeeCustomersForDay(day)

  fun showNumberOfReceiptsForDay(day: String) = cafe.showNumberOfReceiptsForDay(day)
}