package com.engineerclark.advent2022

import com.engineerclark.advent2022.utils.readInput
import com.engineerclark.advent2022.utils.readTestInput

fun main() {
    // challenge 1
    val rounds = readRounds(readInput(2))
    println("Day 2, Challenge 1 -- My total points: ${rounds.totalScores().myPoints}")

    // challenge 2
    val decodedRounds = readEncodedRounds(readInput(2))
    println("Day 2, Challenge 2 -- My total points: ${decodedRounds.totalScores().myPoints}")
}

fun readMove(moveChar: String): Move = when(moveChar) {
    "A", "X" -> Move.rock
    "B", "Y" -> Move.paper
    "C", "Z" -> Move.scissors
    else -> throw RuntimeException("Unrecognized move")
}

fun readEncodedMove(opponentMove: Move, moveChar: String): Move = when(moveChar) {
    "X" -> Move.values().first { move -> move.against(opponentMove) == Outcome.lost }
    "Y" -> opponentMove
    "Z" -> Move.values().first { move -> move.against(opponentMove) == Outcome.won }
    else -> throw RuntimeException("Unrecognized move")
}

fun readRound(line: String): Round {
    val moveChars = line.split(" ")
    return Round(line, readMove(moveChars[0]), readMove(moveChars[1]))
}

fun readEncodedRound(line: String): Round {
    val moveChars = line.split(" ")
    val opponentMove = readMove(moveChars[0])
    return Round(line, opponentMove, readEncodedMove(opponentMove, moveChars[1]))
}

fun readRounds(input: String): List<Round> = input.trim().split("\n").map { readRound (it.trim()) }

fun readEncodedRounds(input: String): List<Round> = input.trim().split("\n").map { readEncodedRound(it.trim()) }

enum class Outcome(val value: Int) { lost(0), draw(3), won(6) }
enum class Move(val value: Int) { rock(1), paper(2), scissors(3) }
fun Move.against(opponentMove: Move): Outcome = when ((this.value - opponentMove.value - 1) % 3) {
    -1 -> Outcome.draw
    0 -> Outcome.won
    else -> Outcome.lost
}

fun playerScore(move: Move, outcome: Outcome): Int = move.value + outcome.value
data class Round(val source: String, val opponentMove: Move, val myMove: Move) {
    val scores: Scores = Scores (
            playerScore(myMove, myMove.against(opponentMove)),
            playerScore(opponentMove, opponentMove.against(myMove))
    )
}
data class Scores (val myPoints: Int, val opponentPoints: Int)

fun List<Scores>.totals() = this.reduce { total, score ->
    Scores(total.myPoints + score.myPoints, total.opponentPoints + score.opponentPoints) }

fun List<Round>.totalScores() = this.map { it.scores }.totals()

