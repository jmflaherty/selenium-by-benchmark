/* This file is part of Selenium-by-benchmark - 2020.
<https://github.com/io.github.jmflaherty/selenium-by-benchmark>

Selenium-by-benchmark is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Selenium-by-benchmark is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with Selenium-by-benchmark.  If not, see <https://www.gnu.org/licenses/lgpl-3.0.html>. */

package io.github.jmflaherty.selenium_by_benchmark

import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

object StatsProcessor {
    /**
     * Processes lists Map<String, List<[Locator]>, and prints the resulting stats.
    */
    @ExperimentalTime
    fun process(locatorsList: Map<String, List<Locator>>, printStats: Boolean): Map<String, List<Locator>> {
        locatorsList.forEach { website ->
            website.value.forEach { locator ->
                calculateLocatorStats(locator)
                if (printStats) printLocatorStats(locator)
            }
        }
        return locatorsList
    }

    private fun calculateLocatorStats(locator: Locator) {
        locator.average = locator.tries.average().toLong()
        locator.median = locator.tries.sorted().let { sortedList -> (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2 }
        locator.maximum = locator.tries.maxOrNull()!!
        locator.minimum = locator.tries.minOrNull()!!
        locator.mean = (locator.minimum + locator.maximum) / 2
        locator.total = locator.tries.sum()
    }

    @ExperimentalTime
    private fun printLocatorStats(locator: Locator) {
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
