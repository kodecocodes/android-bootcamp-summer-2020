package model.caffe

import model.animals.Cat
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
        "Monday" to mutableSetOf<Receipt>(),
        "Tuesday" to mutableSetOf<Receipt>(),
        "Wednesday" to mutableSetOf<Receipt>(),
        "Thursday" to mutableSetOf<Receipt>(),
        "Friday" to mutableSetOf<Receipt>(),
        "Saturday" to mutableSetOf<Receipt>(),
        "Sunday" to mutableSetOf<Receipt>()
    )

    // maybe as employees check in, you can add them to the list of working employees!
    private val employees = mutableSetOf<Employee>()
    private val customers = mutableSetOf<Person>()

    // make sure to add sponsorships and tie them to people!
    private val sponsorships = mutableSetOf<Sponsorship>()

    // TODO Add logic for checking in and checking out!
    fun checkInEmployee(employee: Employee) {

    }

    fun checkOutEmployee(employee: Employee) {

    }

    fun showNumberOfReceiptsForDay(day: String) {
        val receiptForDay = receiptsByDay[day] ?: return // wrong day inserted!

        println("On $day you made ${receiptsByDay.size} transactions!")
    }

    fun getReceipt(items: List<Product>, customerId: String): Receipt {
        // TODO return a receipt! Also make sure to check if customer is also an employee

        return Receipt()
    }

    fun addSponsorship(catId: String, personId: String) {
        // TODO add the sponsorship
    }

    fun getWorkingEmployees(): Set<Employee> = employees

    fun getAdoptedCats(): Set<Cat> {

    }

    fun getSponsoredCats(): Set<Cat> {

    }

    fun getMostPopularCats(): Set<Cat> {

    }

    fun getTopSellingItems(): Set<Product> {

    }

    fun getAdopters(): List<Person> {
        return (employees + customers).filter { it.cats.isNotEmpty() }
    }
}