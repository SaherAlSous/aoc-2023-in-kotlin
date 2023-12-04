
data class Card(val winning: Set<Int>, val mine: Set<Int>)
typealias Input = List<Card>
private fun Card.matchCount() = mine.intersect(winning).size


fun parse(inputStr: String) = inputStr.lines().filterNot { it.isBlank() }.map { line ->
    fun String.ints() = split(" ").filterNot { it.isBlank() }.map { it.toInt() }
    val (_, winStr, myStr) = line.split(":", "|")
    Card(winStr.ints().toSet(), myStr.ints().toSet())
}

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val list = line.substringAfter(':').split('|')
            val winning = list[0].split(' ').filter { it.isNotEmpty() && it.isNotBlank() }.map { it.toInt() }
            val numbers = list[1].split(' ').filter { it.isNotEmpty() && it.isNotBlank() }.map { it.toInt() }
            var equals = numbers.sumOf { num ->
                winning.count { it == num }
            }
            var score = if (equals >= 1) 1 else 0
            while (equals > 1) {
                score *= 2
                equals--
            }
            score
        }
    }
//    fun part2(input: List<String>): Int {
//        var counter = 0
//        val cards = MutableList(input.size * 2) { 1 }
//        return input.sumOf { line ->
//            val list = line.substringAfter(':').split('|')
//            val winning = list[0].split(' ').filter { it.isNotEmpty() && it.isNotBlank() }.map { it.toInt() }
//            val numbers = list[1].split(' ').filter { it.isNotEmpty() && it.isNotBlank() }.map { it.toInt() }
//            var wins = numbers.sumOf { num ->
//                winning.count { it == num }
//            }
//            var i = counter
//            while (wins > 0) {
//                cards[i] += 1
//                i++
//                wins--
//            }
//            var j = cards[counter]
//            var k = counter
//            while (j > 0) {
//                cards[k] += 1
//                j--
//                k++
//            }
//            counter++
//            cards.sum()
//        }
//    }
    fun part2(input: Input): Int {
        val counts = IntArray(input.size) { 1 }
        input.forEachIndexed { index, card ->
            repeat(card.matchCount()) { counts[index + 1 + it] += counts[index] }
        }
        return counts.sum()
    }

    val input = readInput("Day04")
    val myCards = mutableListOf<Card>()
    input.forEach {
        myCards.addAll(parse(it))
    }
    println(part1(input))
    println(part2(myCards))
}
