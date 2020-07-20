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

import java.io.BufferedReader
import java.io.File
import org.openqa.selenium.By

object LocatorsListFactory {
    @JvmStatic
    fun createListFromCSV(csvFile: File): Map<String, List<Locator>> {
        val locatorsList: MutableMap<String, MutableList<Locator>> = mutableMapOf()
        val reader: BufferedReader = csvFile.bufferedReader()
        reader.readLine()
        reader.forEachLine { line ->
            val locatorProperties: List<String> = line.split(",")
            if (locatorsList.containsKey(locatorProperties[0])) {
                locatorsList.getValue(locatorProperties[0]).add(
                        Locator(locatorName = locatorProperties[1],
                                locatorBy = createBy(locatorProperties[2], locatorProperties[3])))
            } else {
                val list: MutableList<Locator> = mutableListOf()
                list.add(
                        Locator(locatorName = locatorProperties[1],
                                locatorBy = createBy(locatorProperties[2], locatorProperties[3])))
                locatorsList[locatorProperties[0]] = list
            }
        }
        return locatorsList
    }

    @Throws(Exception::class)
    private fun createBy(byType: String, byString: String): By {
        when (byType) {
            "ByXPath" -> return By.ByXPath(byString)
            "ById" -> return By.ById(byString)
            "ByClassName" -> return By.ByClassName(byString)
            "ByLinkText" -> return By.ByLinkText(byString)
            "ByPartialLinkText" -> return By.ByPartialLinkText(byString)
            "ByTagName" -> return By.ByTagName(byString)
            "ByCssSelector" -> return By.ByCssSelector(byString)
        }
        throw Exception("The By type $byType is invalid. Use the exact name of any By found in org.openqa.io.github.jmflaherty.selenium.By")
    }
}
