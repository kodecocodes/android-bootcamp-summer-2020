package model.caffe

import helper.testEmployees
import helper.testPatrons
import helper.testReceiptsOne
import helper.testReceiptsTwo
import model.people.Employee
import model.people.Person

class Cafe {


  // cafe related stuff

  /**
   * To simplify it, let's imitate a week-long cafe turnaround.
   *
   * Make sure to add your receipts to each set, with your data.
   * */
  private val receiptsByDay = mutableMapOf(
    "Monday" to testReceiptsOne,
    "Tuesday" to testReceiptsTwo,
    "Wednesday" to mutableSetOf(),
    "Thursday" to mutableSetOf(),
    "Friday" to mutableSetOf(),
    "Saturday" to testReceiptsOne + testReceiptsTwo,
    "Sunday" to testReceiptsOne + testReceiptsTwo
  )

  private var numberOfReceipts: Int = 31

  // maybe as employees check in, you can add them to the list of working employees!
  private val employees = testEmployees.toMutableSet()
  private val customers = (testPatrons + testEmployees.first()).toMutableSet()

  // make sure to add sponsorships and tie them to people!
  private val sponsorships = mutableSetOf<Sponsorship>()

  fun checkInEmployee(employee: Employee) {
    employee.clockIn()

    employees.add(employee)
  }

  fun checkOutEmployee(employee: Employee) {
    employee.clockOut()

    employees.remove(employee)
  }

  fun showNumberOfReceiptsForDay(day: String) {
    val receiptForDay = receiptsByDay[day] ?: return // wrong day inserted!

    println("On $day you made ${receiptsByDay.size} transactions!")
  }

  fun showNumberOfCustomersForDay(day: String) {
    val receiptForDay = receiptsByDay[day] ?: return // wrong day inserted!
    val customersForDay = receiptForDay.map { it.customerId }.toSet()

    println("On $day you had ${customersForDay.size} unique customers!")
  }

  fun showNumberOfNonEmployeeCustomersForDay(day: String) {
    val receiptForDay = receiptsByDay[day] ?: return // wrong day inserted!
    val customersForDay = receiptForDay.mapNotNull { receipt ->
      customers.firstOrNull { it.id == receipt.customerId }
    }.filter { it !is Employee }
      .toSet()

    println("On $day you had ${customersForDay.size} unique customers, that aren't employed at the cafe!")
  }

  fun getReceipt(items: List<Product>, customerId: String): Receipt {
    val hasDiscount = employees.any { it.id == customerId }
    val totalPrice = items.map { it.price }.sum() * (if (hasDiscount) 0.75 else 1.0)

    numberOfReceipts++
    return Receipt(numberOfReceipts.toString(), items, totalPrice, customerId)
  }

  fun addSponsorship(catId: String, personId: String) {
    sponsorships.add(Sponsorship(personId, catId))
  }

  fun getWorkingEmployees(): Set<Employee> = employees

  fun getTopSellingItem(): Product? {
    val allReceipts = receiptsByDay.flatMap { it.value } // get all the receipts
    val productGroups = allReceipts.flatMap { it.products }.groupBy { it }

    val productsByAmountSold = productGroups.mapValues { it.value.size }

    return productsByAmountSold.maxBy { it.value }?.key
  }

  fun getTopTenSellingItems(): List<Product> {
    val allReceipts = receiptsByDay.flatMap { it.value } // get all the receipts
    val productGroups = allReceipts.flatMap { it.products }.groupBy { it }

    val productsByAmountSold = productGroups.mapValues { it.value.size }

    return productsByAmountSold.toList()
      .sortedBy { (_, numberSold) -> numberSold }
      .take(10)
      .map { (product, _) -> product }
  }

  fun getAdopters(): List<Person> {
    return (employees + customers).filter { it.cats.isNotEmpty() }
  }

  fun getCustomers(): Set<Person> = customers

  fun addCustomer(person: Person) {
    customers.add(person)
  }

  fun getSponsorships(): Set<Sponsorship> = sponsorships
}