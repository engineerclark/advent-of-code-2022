package com.engineerclark.advent2022

import com.engineerclark.advent2022.utils.readInput

fun main() {
    val rucks = readRucksacks(readInput(3))
    println("Day 3, challenge 1 -- Sum of priorities of common item types: ${rucks.sumOfCommonItemPriorities()}")

    val elfGroups = rucks.asElfGroups()
    println("Day 3, challenge 2 -- Sum of priorities of group badges: ${elfGroups.sumOfBadgePriorities()}")
}

fun List<Rucksack>.sumOfCommonItemPriorities(): Int = this.sumOf { ruck -> ruck.commonItems.first().priority }

fun List<ElfGroup>.sumOfBadgePriorities(): Int = this.sumOf { group -> group.badge.priority }

private fun List<Rucksack>.asElfGroups(): List<ElfGroup> = this.chunked(3)
    .map { rucks -> ElfGroup(rucks[0], rucks[1], rucks[2]) }

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

data class ElfGroup(val first: Rucksack, val second: Rucksack, val third: Rucksack) {
    val badge: Char = first.contents.asIterable().intersect(second.contents.asIterable().toSet())
        .intersect(third.contents.asIterable().toSet())
        .first()
}