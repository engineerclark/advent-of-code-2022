package com.engineerclark.advent2022

import com.engineerclark.advent2022.utils.readInput
import com.engineerclark.advent2022.utils.readTestInput

fun main() {
    val rucks = readRucksacks(readInput(3))
    println("Day 3, challenge 1 -- Sum of priorities of common item types: ${rucks.sumOfCommonItemPriorities()}")
}

fun List<Rucksack>.sumOfCommonItemPriorities(): Int = this.sumOf { ruck -> ruck.commonItems.first().priority }

private fun readRucksacks(input: String): List<Rucksack> = input.splitToSequence("\n").map(::Rucksack).toList()

private val priorityMap = sequenceOf(CharRange('a', 'z').asSequence(), CharRange('A', 'Z').asSequence()).flatten()
    .mapIndexed { i, c -> Pair(c, i + 1) }
    .toMap()

private val Char.priority
    get() = priorityMap.getOrElse(this) { throw RuntimeException("Character priority unknown") }

data class Rucksack(val contents: String) {
    val firstCompartment = contents.substring(0, contents.length / 2)
    val secondCompartment = contents.substring(contents.length / 2)
    val commonItems = firstCompartment.asIterable().intersect(secondCompartment.asIterable().toSet())
}