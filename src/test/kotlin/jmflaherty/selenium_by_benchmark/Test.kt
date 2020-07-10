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

import java.io.File
import kotlin.test.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class Test {
    @Test
    fun driversList() {
        // val driversList: List<() -> WebDriver> = listOf(Browsers.chrome, Browsers.firefox)
        // ToDo: 1. Define locators. Maybe use files. JSON, HOCON, CVS ??  WEBSITE ,  NAME - LOCATOR STRATEGY - LOCATOR STRING
        // val locatorsList: Map<String, List<Locator>> = LocatorsListFactory.createOriginalList()
        // ToDo: 2. Implement another function using inversion of control over the stats to generate all the metrics
        //  and comparisons to declare the winners on different categories
        Benchmark.run(
                driversList = listOf(Browsers.chrome, Browsers.firefox),
                locatorsList = TestLocators.createOriginalList(),
                iterations = 10,
                printStats = true
        )
    }

    @Test
    fun driverObject() {
        Benchmark.run(
                driver = Browsers.chrome.invoke(),
                locatorsList = TestLocators.createOriginalList(),
                iterations = 10,
                printStats = true
        )
    }

    @Test
    fun listFromCSV() {
        Benchmark.run(
                driver = Browsers.chrome.invoke(),
                locatorsList = LocatorsListFactory.createListFromCSV(File(this.javaClass.getResource("/locatorsList.csv").toURI())),
                iterations = 10,
                printStats = true
        )
    }
}
