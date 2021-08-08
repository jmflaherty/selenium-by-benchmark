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

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.opera.OperaDriver
import org.openqa.selenium.opera.OperaOptions
import org.openqa.selenium.safari.SafariDriver

object Browsers {
    val chrome: () -> ChromeDriver = {
        WebDriverManager.chromedriver().setup()
        ChromeDriver(ChromeOptions().addArguments("--headless"))
    }
    val firefox: () -> FirefoxDriver = {
        WebDriverManager.firefoxdriver().setup()
        FirefoxDriver(FirefoxOptions().addArguments("--headless"))
    }
    val opera: () -> OperaDriver = {
        WebDriverManager.operadriver().setup()
        OperaDriver(OperaOptions().addArguments("--headless"))
    }
    val edge: () -> EdgeDriver = {
        WebDriverManager.edgedriver().setup()
        EdgeDriver()
    }
    val internetExplorer: () -> InternetExplorerDriver = {
        WebDriverManager.iedriver().setup()
        InternetExplorerDriver()
    }
    val safari: () -> SafariDriver = {
        SafariDriver()
    }
}
