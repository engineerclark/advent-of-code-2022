package com.engineerclark.advent2022

typealias CargoInput = List<String>

fun main() {
    val
}

val CargoInput.stackCount
    get() = this.first { line -> line.trim().startsWith('1') }
        .split(" ")
        .map { stack -> stack.trim().toInt() }
        .last()