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

import org.openqa.selenium.By

object TestLocators {
    fun createOriginalList(): Map<String, List<Locator>> {
        return mapOf(
                Pair("https://www.w3.org/", listOf(
                        Locator("Bad - CSS", By.ByCssSelector("#w3c_main > div:nth-child(2) > ul:nth-child(5) > li:nth-child(7) > a")),
                        Locator("Good - CSS", By.ByCssSelector(".w3c_leftCol a[href='WoT/']")),
                        Locator("Bad - XPath", By.ByXPath("//*[@id='w3c_main']/div[2]/ul[2]/li[7]/a")),
                        Locator("Full path - XPath", By.ByXPath("/html/body/div[1]/div[2]/div[2]/ul[2]/li[7]/a")),
                        Locator("Good - XPath", By.ByXPath("//div[@class='w3c_leftCol']//a[@href='WoT/']")))))
    }

    fun createRougouskiList(): Map<String, List<Locator>> {
        return mapOf(
                Pair("https://www.w3.org/", listOf(
                        Locator("Bad - CSS", By.ByCssSelector("#w3c_main > div:nth-child(2) > ul:nth-child(5) > li:nth-child(7) > a")),
                        Locator("Good - CSS by Adrian Rougouski", By.ByCssSelector("[class='w3c_leftCol'] a[href='WoT/']")),
                        Locator("""Good - CSS matching "Good - XPath" by Adrian Rougouski""", By.ByCssSelector("div[class='w3c_leftCol'] a[href='WoT/']")),
                        Locator("Bad - XPath", By.ByXPath("//*[@id='w3c_main']/div[2]/ul[2]/li[7]/a")),
                        Locator("Full path - XPath", By.ByXPath("/html/body/div[1]/div[2]/div[2]/ul[2]/li[7]/a")),
                        Locator("Good - XPath", By.ByXPath("//div[@class='w3c_leftCol']//a[@href='WoT/']")))))
    }
}
