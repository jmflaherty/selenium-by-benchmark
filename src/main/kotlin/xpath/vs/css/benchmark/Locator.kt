/* Licensed under Apache-2.0 */
package xpath.vs.css.benchmark

import java.util.concurrent.TimeUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration
import org.openqa.selenium.By

data class Locator(
    val locatorName: String,
    val locatorBy: By
) {
    var average: Long = 0
    var median: Long = 0
    var mean: Long = 0
    var maximum: Long = 0
    var minimum: Long = 9999999
    var total: Long = 0
    var tries: List<Long>? = listOf<Long>()

    fun calculateStats() {
        average = tries?.average()?.toLong()!!
        median = tries?.sorted().let { (it!![it.size / 2] + it[(it.size - 1) / 2]) / 2 }
        maximum = tries?.max()!!
        minimum = tries?.min()!!
        mean = (minimum + maximum) / 2
        total = tries?.sum()!!
    }

    @ExperimentalTime
    fun printStats() {
        println("""
            $locatorName
            - $locatorBy
            - Tries: ${tries?.size}
            - Average: ${average.toDuration(TimeUnit.MILLISECONDS)}
            - Median: ${median.toDuration(TimeUnit.MILLISECONDS)}
            - Mean: ${mean.toDuration(TimeUnit.MILLISECONDS)}
            - Maximum: ${maximum.toDuration(TimeUnit.MILLISECONDS)}
            - Minimum: ${minimum.toDuration(TimeUnit.MILLISECONDS)}
            - Total: ${total.toDuration(TimeUnit.MILLISECONDS)}

        """.trimIndent())
    }
}
