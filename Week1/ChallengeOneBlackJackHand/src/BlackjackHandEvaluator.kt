/**
 *
 * This is challenge one for Raywenderlich.com's Android Bootcamp
 * Summer of 2020
 * Written by Jennifer Bailey
 *
 */

data class Card(val suit: Char, val pip: String)

fun main() {
    var deck = createDeck()
    var hand = dealHand(2, deck)
    var total = evaluateHand(hand)
    printResults(hand, total)
}

fun createDeck(): MutableSet<Card> {
    var suits = listOf('\u2663','\u2660','\u2666','\u2665')
    println(suits)
    var pips = listOf("1","2","3","4","5","6","7","8","9","10","J","Q","K")
    var deck: MutableSet<Card> = mutableSetOf()
    for(suit in suits) {
        for(pip in pips) {
            deck.add(Card(suit,pip))
        }
    }
    return deck
}

fun dealHand(number: Int, deck: MutableSet<Card>) : MutableSet<Card> {
    var hand: MutableSet<Card> = mutableSetOf()
    for(i in 0 until number) {
        val card = deck.random()
        hand.add(card)
        deck.remove(card)
    }
    return hand
}

fun evaluateHand(hand: MutableSet<Card>) : Int {
    var total = 0
    for(card in hand) {
        when(card.pip) {
            "K","Q","J","10" -> total += 10
            "A" -> total += 11
            else -> total+=card.pip.toInt()
        }
    }
    return total
}

fun printResults(hand : Set<Card>, total: Int) {
    println("Your hand was:")
    for(card in hand) {
        println("${card.pip}${card.suit}")
    }
    println("For a total of: $total")
}

