package model.animals

import model.caffe.Sponsorship

data class Cat(
    val id: String,
    val name: String,
    val gender: String,
    val shelterId: String,
    val sponsorships: MutableSet<Sponsorship> = mutableSetOf()
)