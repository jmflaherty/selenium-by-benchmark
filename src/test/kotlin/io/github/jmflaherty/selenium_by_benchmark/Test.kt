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

import java.io.File
import kotlin.test.Test
import kotlin.time.ExperimentalTime
import org.openqa.selenium.By

@ExperimentalTime
class Test {
    @Test
    fun driversList() {
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

    @Test
    fun singleBy() {
        val driver = Browsers.chrome.invoke()
        driver.get("https://www.w3.org/")
        Benchmark.run(
                driver = driver,
                by = By.ByXPath("//div[@class='w3c_leftCol']//a[@href='WoT/']"),
                iterations = 10,
                printStats = true
        )
    }

    @Test
    fun singleByWithUrl() {
        Benchmark.run(
                driver = Browsers.chrome.invoke(),
                url = "https://www.w3.org/",
                by = By.ByXPath("//div[@class='w3c_leftCol']//a[@href='WoT/']"),
                iterations = 10,
                printStats = true
        )
    }

    @Test
    fun singleByWithUrlAndDriversList() {
        Benchmark.run(
                driversList = listOf(Browsers.chrome, Browsers.firefox),
                url = "https://www.w3.org/",
                by = By.ByXPath("//div[@class='w3c_leftCol']//a[@href='WoT/']"),
                iterations = 10,
                printStats = true
        )
    }
}
