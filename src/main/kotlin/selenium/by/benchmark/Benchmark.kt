/* Licensed under Apache-2.0 */
package selenium.by.benchmark

import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
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
                benchmarkDriver(driver, locatorsList, iterations)
                StatsProcessor(locatorsList)
            } catch (exception: Exception) {
                throw exception
            } finally {
                driver.quit()
            }
        }
    }

    private fun benchmarkDriver(driver: WebDriver, locatorsList: Map<String, List<Locator>>, iterations: Int) {
        printDriver(driver)
        locatorsList.forEach { website ->
            website.key.let { url ->
                println("URL: $url\n")
                driver.get(url)
            }
            website.value.forEach { locator ->
                locator.tries = benchmarkBy(driver, locator.locatorBy, iterations)
            }
        }
    }

    private fun printDriver(driver: WebDriver) {
        println("""

            ${(driver as RemoteWebDriver).capabilities.browserName.toUpperCase()} ${driver.capabilities.version} (${driver.capabilities.platform.name} ${driver.capabilities.platform.majorVersion}.${driver.capabilities.platform.minorVersion})
        """.trimIndent())
    }

    private fun benchmarkBy(driver: WebDriver, locator: By, iterations: Int): List<Long> {
        val triesList: MutableList<Long> = mutableListOf()
        for (counter in 1..iterations) {
            triesList.add(measureTime { driver.findElement(locator) }.toLongMilliseconds())
        }
        return triesList
    }
}
