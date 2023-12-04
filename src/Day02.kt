import kotlin.math.max

// only 12 red cubes, 13 green cubes, and 14 blue cubes
fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            line.substringAfter(": ").split("; ").map { items ->
                items.split(", ").map { cubes ->
                    val n = cubes.substringBefore(' ').toInt()
                    val color = cubes.substringAfter(' ')
                    if (color == "red" && n > 12 ||color == "green" && n > 13 || color == "blue" && n > 14) return@sumOf 0
                }
            }
            line.substringAfter(' ').substringBefore(':').toInt()
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            val values = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
            line.substringAfter(": ").split("; ").forEach { draw ->
                draw.split(", ").forEach { cubes ->
                    val color = cubes.substringAfter(' ')
                    values[color] = max(values[color]!!, cubes.substringBefore(' ').trim().toInt())
                }
            }
            values["red"]!! * values["blue"]!! * values["green"]!!
        }
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

/*
fun part1(input: List<String>): Int {
        var counter = 0
        input.forEach { line ->
            val gameNUmber = line.substringBefore(':').removeRange(0, 4).trim().toInt()
            var possible = 0
            line.substringAfter(':').split(';').forEach { draw ->
                draw.split(',').forEach { cube ->
                    if (cube.contains("red")) possible -= if (cube.substringBefore("red").trim().toInt() > 12) 1 else 0
                    if (cube.contains("green")) possible -= if (cube.substringBefore("green").trim().toInt() > 13) 1 else 0
                    if (cube.contains("blue")) possible -= if (cube.substringBefore("blue").trim().toInt() > 14) 1 else 0
                }
            }
            if (possible == 0) counter += gameNUmber
        }
        return counter
    }
 */

/*
 val red = mutableListOf<Int>()
            val green = mutableListOf<Int>()
            val blue = mutableListOf<Int>()
            line.substringAfter(": ").split("; ").forEach { draw ->
                draw.split(',').forEach { cubes ->
                    if (cubes.contains("red")) red.add(cubes.substringBefore(" red").trim().toInt())
                    if (cubes.contains("green")) green.add(cubes.substringBefore(" green").trim().toInt())
                    if (cubes.contains("blue")) blue.add(cubes.substringBefore(" blue").trim().toInt())
                }
            }
            red.max() * blue.max() * green.max()
 */