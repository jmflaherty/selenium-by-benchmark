/* Licensed under Apache-2.0 */
package selenium.by.benchmark

import kotlin.io.println as println
import kotlin.time.ExperimentalTime
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver

@ExperimentalTime
class Benchmark(
    driversList: List<()-> WebDriver>,
    locatorsList: Map<String, List<Locator>>,
    iterations: Int = 1000
) {
    init {
        driversList.forEach { driverConstructor ->
            val driver: WebDriver = driverConstructor.invoke()
            printDriver(driver)
            benchmarkDriver(driver, locatorsList, iterations)
            processLocatorsListStats(locatorsList)
            driver.quit()
        }
    }

    private fun printDriver(driver: WebDriver) {
        println((driver as RemoteWebDriver).capabilities.browserName.toUpperCase() +
                " " + driver.capabilities.version +
                " (" + driver.capabilities.platform.name + " " + driver.capabilities.platform.majorVersion + "." + driver.capabilities.platform.minorVersion + ")" +
                "\n")
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
        var startingTime: Long
        var endingTime: Long
        val trieList: MutableList<Long> = mutableListOf<Long>()

        for (counter in 1..iterations) {
            startingTime = System.currentTimeMillis()
            driver.findElement(locator)
            endingTime = System.currentTimeMillis()
            trieList.add(endingTime - startingTime)
        }
        return trieList
    }

    private fun processLocatorsListStats(locatorsList: Map<String, List<Locator>>) {
        locatorsList.forEach { website ->
            website.value.forEach { locator ->
                // ToDo: Use setters so I don't depend on internal access to properties,
                //  also use inversion of control, so the calculateStats func receives the locator and deals with it
                locator.calculateStats()
                locator.printStats() }
        }
    }
}
