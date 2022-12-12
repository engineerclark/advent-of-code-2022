package com.engineerclark.advent2022.models

data class Elf(val foodItems: List<FoodItem>) {
    val totalCalories: Int = foodItems.asSequence().map { it.calories }.sum()
}

data class FoodItem(val calories: Int)