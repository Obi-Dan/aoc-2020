/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package aoc

import java.net.URL

class App {
    data class Day1Ex1Result(val sum: Int?, val product: Int?, val resultNums: List<Int>)

    fun getDay1Ex1Input(): List<String>? {
        val url: URL? = App::class.java.getResource("input-day-1.txt")
        return url?.readText()?.split("\n")
    }

    fun findDay1Ex1Result(day1Input: List<String>?, sumTotal: Int): Day1Ex1Result? {
        val nums: List<Int> = day1Input?.map { string ->
            try {
                Pair<Int?, Exception?>(string.toInt(), null)
            } catch (exception: Exception) {
                Pair<Int?, Exception?>(null, exception)
            }
        }?.filter { it.first != null }?.map { it.first!! } ?: emptyList()

        val results: List<Day1Ex1Result> = nums.filter { firstNum ->
            nums.any { secondNum ->
                secondNum != firstNum && firstNum + secondNum == sumTotal
            }
        }.map { firstNum ->
            val secondNum: Int? = nums.find { secondNum ->
                secondNum != firstNum && firstNum + secondNum == sumTotal
            }

            Day1Ex1Result(
                sum = secondNum?.plus(firstNum),
                product = secondNum?.times(firstNum),
                resultNums = if (secondNum != null) {
                    listOf(firstNum, secondNum)
                } else {
                    emptyList()
                }
            )
        }

        val result = results.firstOrNull<Day1Ex1Result>();

        return result
    }

    fun findDay1Ex2Result(day1Input: List<String>?, sumTotal: Int): Day1Ex1Result? {
        val nums: List<Int> = day1Input?.map { string ->
            try {
                Pair<Int?, Exception?>(string.toInt(), null)
            } catch (exception: Exception) {
                Pair<Int?, Exception?>(null, exception)
            }
        }?.filter { it.first != null }?.map { it.first!! } ?: emptyList()

        val results: List<Day1Ex1Result> = nums.filter { firstNum ->
            nums.any { secondNum ->
                nums.any { thirdNum ->
                    firstNum != secondNum
                            && firstNum != thirdNum
                            && secondNum != thirdNum
                            && firstNum + secondNum + thirdNum == sumTotal
                }
            }
        }.map { firstNum ->
            val resultNums: List<Int> = nums.flatMap { secondNum ->
                nums.flatMap { thirdNum ->
                    if (firstNum != secondNum
                            && firstNum != thirdNum
                            && secondNum != thirdNum
                            && firstNum + secondNum + thirdNum == sumTotal) {
                        listOf(firstNum, secondNum, thirdNum)
                    } else {
                        emptyList()
                    }
                }
            }.distinct()

            Day1Ex1Result(
                sum = resultNums.sum(),
                product = resultNums.reduce { acc: Int, num: Int -> acc * num },
                resultNums = resultNums
            )
        }

        val result = results.firstOrNull<Day1Ex1Result>();

        return result
    }

    fun runDay1Ex1() {
        val day1Input: List<String>? = getDay1Ex1Input()
        val result: App.Day1Ex1Result? = findDay1Ex1Result(day1Input, 2020)
        println("Day 1 - Exercise 1 Result")
        println("Product: ${result?.product}")
        result?.resultNums?.forEachIndexed { index, num ->
            println("Number ${index + 1}: $num")
        }
    }

    fun runDay1Ex2() {
        val day1Input: List<String>? = getDay1Ex1Input()
        val result: App.Day1Ex1Result? = findDay1Ex2Result(day1Input, 2020)
        println("Day 1 - Exercise 2 Result")
        println("Product: ${result?.product}")
        result?.resultNums?.forEachIndexed { index, num ->
            println("Number ${index + 1}: $num")
        }
    }
}

fun main() {
    val app = App()
    app.runDay1Ex1()
    app.runDay1Ex2()
}
