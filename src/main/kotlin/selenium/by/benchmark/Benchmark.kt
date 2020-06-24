/* Licensed under Apache-2.0 */
package selenium.by.benchmark

import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver

@ExperimentalTime
class Benchmark(
    driversList: List<() -> WebDriver>,
    locatorsList: Map<String, List<Locator>>,
    iterations: Int = 1000
) {
    init {
        driversList.forEach { driverConstructor ->
            val driver: WebDriver = driverConstructor.invoke()
            try {
                printDriver(driver)
                benchmarkDriver(driver, locatorsList, iterations)
                processLocatorsListStats(locatorsList)
            } catch (exception: Exception) {
                throw exception
            } finally {
                driver.quit()
            }
        }
    }

    private fun printDriver(driver: WebDriver) {
        println("""

            ${(driver as RemoteWebDriver).capabilities.browserName.toUpperCase()} ${driver.capabilities.version} (${driver.capabilities.platform.name} ${driver.capabilities.platform.majorVersion}.${driver.capabilities.platform.minorVersion})

        """.trimIndent())
    }

    private fun benchmarkDriver(driver: WebDriver, locatorsList: Map<String, List<Locator>>, iterations: Int) {
        locatorsList.forEach { website ->
            driver.get(website.key)
            website.value.forEach { locator ->
                locator.tries = benchmarkBy(driver, iterations, locator.locatorBy)
            }
        }
    }

    private fun benchmarkBy(driver: WebDriver, iterations: Int, locator: By): List<Long> {
        var startingTime: Long by Delegates.notNull<Long>()
        var endingTime: Long by Delegates.notNull<Long>()
        val triesList: MutableList<Long> = mutableListOf<Long>()

        for (counter in 1..iterations) {
            startingTime = System.currentTimeMillis()
            driver.findElement(locator)
            endingTime = System.currentTimeMillis()
            triesList.add(endingTime - startingTime)
        }
        return triesList
    }

    private fun processLocatorsListStats(locatorsList: Map<String, List<Locator>>) {
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

    @ExperimentalTime
    private fun printStats(locator: Locator) {
        println("""
            ${locator.locatorName}
            - ${locator.locatorBy}
            - Tries: ${locator.tries.size}
            - Average: ${locator.average.toDuration(TimeUnit.MILLISECONDS)}
            - Median: ${locator.median.toDuration(TimeUnit.MILLISECONDS)}
            - Mean: ${locator.mean.toDuration(TimeUnit.MILLISECONDS)}
            - Maximum: ${locator.maximum.toDuration(TimeUnit.MILLISECONDS)}
            - Minimum: ${locator.minimum.toDuration(TimeUnit.MILLISECONDS)}
            - Total: ${locator.total.toDuration(TimeUnit.MILLISECONDS)}

        """.trimIndent())
    }
}
