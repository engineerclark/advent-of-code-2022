package com.engineerclark.advent2022

import com.engineerclark.advent2022.models.Elf
import com.engineerclark.advent2022.models.FoodItem
import com.engineerclark.advent2022.utils.readInput


fun main() {
    val elves = readElves(readInput(1))
    println("Calories held by the Elf with the most: ${elves.elfWithMost()?.totalCalories}")
}

fun readElves(input: String): List<Elf> = input.trim().split("\n\n").map(::readElf)

fun readElf(input: String): Elf = Elf(input.trim().split("\n").map { FoodItem(it.trim().toInt()) })

fun List<Elf>.sortedByMostCalories(): List<Elf> = this.sortedByDescending { it.totalCalories }
fun List<Elf>.elfWithMost(): Elf? = this.sortedByMostCalories().firstOrNull()