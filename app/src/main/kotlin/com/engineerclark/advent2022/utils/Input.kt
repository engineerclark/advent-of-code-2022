package com.engineerclark.advent2022.utils

fun readInput(day: Int): String = object {}.javaClass.classLoader.getResource(fileName(day))?.readText()!!

private fun fileName(day: Int) = "input_day${day.toString().padStart(2, '0')}.txt"