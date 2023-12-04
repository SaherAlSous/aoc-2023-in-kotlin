
fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { s ->
            s.filter { it.isDigit() }
                .let { "${it.first()}${it.last()}".toInt() }
        }
//        return input.map {
//            val firstDigit = it.first(Char::isDigit)
//            val lastDigit = it.reversed().first(Char::isDigit)
//            "$firstDigit$lastDigit".toInt()
//        }.fold(0, Int::plus)
    }

    fun part2(input: List<String>): Int {
        val words = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        ).mapValues { it.value.toString() }
            .let { alphaMap -> alphaMap + alphaMap.values.associateBy { it } }
        fun String.firstDigit() = words[findAnyOf(words.keys)!!.second]
        fun String.lastDigit() = words[findLastAnyOf(words.keys)!!.second]
        return input.sumOf { s ->
            "${s.firstDigit()}${s.lastDigit()}".toInt()
        }
    }
//        val numberNames = (1..9).associateWith { number ->
//            when (number) {
//                1 -> "one"
//                2 -> "two"
//                3 -> "three"
//                4 -> "four"
//                5 -> "five"
//                6 -> "six"
//                7 -> "seven"
//                8 -> "eight"
//                9 -> "nine"
//                else -> error("Invalid number")
//            }
//        }.mapValues { listOf(it.value, it.key.toString()) }
//
//        fun findNumbers(string: String): Map<Int, Int> {
//            val foundNumbers = mutableMapOf<Int, Int>()
//            numberNames.forEach { (number, numberValues) ->
//                numberValues.forEach { numberValue ->
//                    val index = string.indexOf(numberValue)
//                    if (index != -1) foundNumbers[index] = number
//                    val lastIndex = string.lastIndexOf(numberValue)
//                    if (lastIndex != -1) foundNumbers[lastIndex] = number
//                }
//            }
//            return foundNumbers
//        }
//
//        return input.map { line ->
//                val foundNumbers = findNumbers(line)
//                val firstDigit = foundNumbers.minBy { it.key }.value
//                val lastDigit = foundNumbers.maxBy { it.key }.value
//                "$firstDigit$lastDigit".toInt()
//            }
//            .fold(0, Int::plus)

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
