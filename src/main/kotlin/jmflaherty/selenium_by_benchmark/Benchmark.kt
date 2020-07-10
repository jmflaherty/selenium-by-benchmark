/* This file is part of Selenium-by-benchmark (https://github.com/jmflaherty/selenium-by-benchmark).

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

package jmflaherty.selenium_by_benchmark

import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver

@ExperimentalTime
object Benchmark {
    @JvmOverloads
    fun run(
        driversList: List<() -> WebDriver>,
        locatorsList: Map<String, List<Locator>>,
        iterations: Int = 1000,
        printStats: Boolean = true
    ): Map<String, Map<String, List<Locator>>> {
        try {
            val locatorsListByDriver = mutableMapOf<String, Map<String, List<Locator>>>()
            driversList.forEach { driverConstructor ->
                val driver = driverConstructor.invoke()
                locatorsListByDriver[getDriverNameAndVersion(driver)] = benchmark(driver, locatorsList, iterations, printStats)
            }
            return locatorsListByDriver
        } catch (exception: Exception) {
            throw exception
        }
    }

    @JvmOverloads
    fun run(
        driver: WebDriver,
        locatorsList: Map<String, List<Locator>>,
        iterations: Int = 1000,
        printStats: Boolean = true
    ): Map<String, List<Locator>> {
        return benchmark(driver, locatorsList, iterations, printStats)
    }

    private fun benchmark(driver: WebDriver, locatorsList: Map<String, List<Locator>>, iterations: Int, printStats: Boolean): Map<String, List<Locator>> {
        try {
            benchmarkDriver(driver, locatorsList, iterations)
            return StatsProcessor.process(locatorsList, printStats)
        } catch (exception: Exception) {
            throw exception
        } finally {
            driver.quit()
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

    private fun getDriverNameAndVersion(driver: WebDriver): String {
        return "${(driver as RemoteWebDriver).capabilities.browserName.toUpperCase()} " +
                "${driver.capabilities.version} (${driver.capabilities.platform.name} " +
                "${driver.capabilities.platform.majorVersion}" +
                ".${driver.capabilities.platform.minorVersion})"
    }

    private fun printDriver(driver: WebDriver) {
        println("""

            ${getDriverNameAndVersion(driver)})
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
