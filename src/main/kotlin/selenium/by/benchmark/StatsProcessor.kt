/* Licensed under Apache-2.0 */
package selenium.by.benchmark

import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalTime
class StatsProcessor(locatorsList: Map<String, List<Locator>>) {
    init {
        locatorsList.forEach { website ->
            website.value.forEach { locator ->
                calculateStats(locator)
                printStats(locator)
            }
        }
    }

    private fun calculateStats(locator: Locator) {
        locator.average = locator.tries.average().toLong()
        locator.median = locator.tries.sorted().let { sortedList -> (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2 }
        locator.maximum = locator.tries.max()!!
        locator.minimum = locator.tries.min()!!
        locator.mean = (locator.minimum + locator.maximum) / 2
        locator.total = locator.tries.sum()
    }

    private fun printStats(locator: Locator) {
        println("""
            ${locator.locatorName}
            - ${locator.locatorBy}
            - Tries: ${locator.tries.size}
            - Average: ${locator.average.toDuration(DurationUnit.MILLISECONDS)}
            - Median: ${locator.median.toDuration(DurationUnit.MILLISECONDS)}
            - Mean: ${locator.mean.toDuration(DurationUnit.MILLISECONDS)}
            - Maximum: ${locator.maximum.toDuration(DurationUnit.MILLISECONDS)}
            - Minimum: ${locator.minimum.toDuration(DurationUnit.MILLISECONDS)}
            - Total: ${locator.total.toDuration(DurationUnit.MILLISECONDS)}

        """.trimIndent())
    }
}
