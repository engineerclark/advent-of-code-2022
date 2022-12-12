package com.engineerclark.advent2022.utils

fun readInput(day: Int): String = readResourceFile(fileName(day))
fun readTestInput(day: Int): String = readResourceFile(testFileName(day))

fun readResourceFile(fileName: String): String = object {}.javaClass.classLoader.getResource(fileName)?.readText()!!

private fun fileName(day: Int) = "input_day${day.toString().padStart(2, '0')}.txt"
private fun testFileName(day: Int) = "input_test_day${day.toString().padStart(2, '0')}.txt"