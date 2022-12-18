package com.engineerclark.advent2022

import com.engineerclark.advent2022.utils.readDayLines

fun main() {
    val assignments = readDayLines(4).readPairAssignments()
    println("Day 4, challenge 1 -- Count of elf pair assignments where one is a subset of the other: " +
            "${assignments.countHavingSubset}")

    println("Day 4, challenge 2 -- Count of elf pair assignments that overlap: " +
            "${assignments.countOverlappingAssignments}")
}

fun List<String>.readPairAssignments(): List<ElfPair> = this.map { it.trim().readPairAssignment() }

val List<ElfPair>.countHavingSubset
    get() = this.count { it.oneIsSubset }

val List<ElfPair>.countOverlappingAssignments
    get() = this.count { it.assignmentsOverlap }

fun String.readPairAssignment(): ElfPair {
    val assignments = this.split(",")
    val firstAssignment = readAssignmentRange(assignments[0])
    val secondAssignment = readAssignmentRange(assignments[1])
    return ElfPair(this, firstAssignment, secondAssignment)
}

fun readAssignmentRange(assignmentString: String): Set<Int> {
    val limits = assignmentString.split("-")
    val first = limits[0].toInt()
    val last = limits[1].toInt()
    return IntRange(first, last).toSet()
}

data class ElfPair(
    val source: String,
    val firstAssignment: Set<Int>,
    val secondAssignment: Set<Int>
) {
    val oneIsSubset: Boolean
        get() = firstAssignment.containsAll(secondAssignment) || secondAssignment.containsAll(firstAssignment)

    val assignmentsOverlap: Boolean
        get() = firstAssignment.intersect(secondAssignment).isNotEmpty()
}