/* Licensed under Apache-2.0 */
package selenium.by.benchmark

import java.io.BufferedReader
import java.io.File
import org.openqa.selenium.By

object LocatorsListFactory {
    fun createOriginalList(): Map<String, List<Locator>> {
        return mapOf(
                Pair("https://www.w3.org/", listOf(
                        Locator("Bad - CSS", By.ByCssSelector("#w3c_main > div:nth-child(2) > ul:nth-child(3) > li:nth-child(7) > a")),
                        Locator("Good - CSS", By.ByCssSelector(".w3c_leftCol a[href='WoT/']")),
                        Locator("Bad - XPath", By.ByXPath("//*[@id='w3c_main']/div[2]/ul[1]/li[7]/a")),
                        Locator("Full path - XPath", By.ByXPath("/html/body/div[1]/div[2]/div[2]/ul[1]/li[7]/a")),
                        Locator("Good - XPath", By.ByXPath("//div[@class='w3c_leftCol']//a[@href='WoT/']")))))
    }

    fun createRougouskiList(): Map<String, List<Locator>> {
        return mapOf(
                Pair("https://www.w3.org/", listOf(
                        Locator("Bad - CSS", By.ByCssSelector("#w3c_main > div:nth-child(2) > ul:nth-child(3) > li:nth-child(7) > a")),
                        Locator("Good - CSS by Adrian Rougouski", By.ByCssSelector("[class='w3c_leftCol'] a[href='WoT/']")),
                        Locator("""Good - CSS matching "Good - XPath" by Adrian Rougouski""", By.ByCssSelector("div[class='w3c_leftCol'] a[href='WoT/']")),
                        Locator("Bad - XPath", By.ByXPath("//*[@id='w3c_main']/div[2]/ul[1]/li[7]/a")),
                        Locator("Full path - XPath", By.ByXPath("/html/body/div[1]/div[2]/div[2]/ul[1]/li[7]/a")),
                        Locator("Good - XPath", By.ByXPath("//div[@class='w3c_leftCol']//a[@href='WoT/']")))))
    }

    fun createListFromCSV(csvFile: String = "locatorsList.csv"): Map<String, List<Locator>> {
        val locatorsList: MutableMap<String, MutableList<Locator>> = mutableMapOf()
        val reader: BufferedReader = File(csvFile).bufferedReader()
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
        throw Exception("The By type $byType is invalid. Use the exact name of any By found in org.openqa.selenium.By")
    }
}
