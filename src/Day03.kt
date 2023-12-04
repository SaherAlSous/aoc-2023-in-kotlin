
data class Number(val value: Int, val x: Int, val y: Int)
val numbers = mutableListOf<Number>()
fun Int.around() = (this -1) .. (this + 1)
fun main() {
    fun part1(input: List<String>): Int {
        input.forEachIndexed { y, line ->
            var x = 0
            while (x <= line.lastIndex) {
                if (line[x].isDigit()) {
                    val value = line.drop(x).takeWhile { it.isDigit() }.toInt()
                    Number(value = value, x = x, y = y).let {
                        numbers += it
                        x += it.value.toString().length
                    }
                } else x++
            }
        }
        return numbers.filter { (value, x, y) ->
            input.withIndex()
                .filter { it.index in (y - 1) .. (y + 1) }
                .flatMap { it.value.drop(maxOf(x - 1, 0)).take(value.toString().length + 2).toList() }
                .any { it != '.' && !it.isDigit() }
        }.sumOf { it.value }
    }
    fun part2(input: List<String>): Int {
        numbers.clear()
        input.forEachIndexed { y, line ->
            var x = 0
            while (x <= line.lastIndex) {
                if (line[x].isDigit()) {
                    val value = line.drop(x).takeWhile { it.isDigit() }.toInt()
                    Number(value = value, x = x, y = y).let {
                        numbers += it
                        x += it.value.toString().length
                    }
                } else x++
            }
        }
        var total = 0
        input.forEachIndexed { y, s ->
            s.forEachIndexed { x, c ->
                if (c == '*') numbers
                    .filter { it.y in  y.around() && listOf(it.x, it.x + it.value.toString().lastIndex).any {num -> num in x.around()} }
                    .takeIf { it.size == 2 }?.let { total += it[0].value * it[1].value}
            }
        }
        return total
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}