/* Licensed under Apache-2.0 */
package selenium.by.benchmark

import org.openqa.selenium.By

object LocatorsListFactory {
    fun createOriginalList(): Map<String, List<Locator>> {
        return mapOf<String, List<Locator>>(
                Pair("https://www.w3.org/", listOf(
                        Locator("Bad - CSS", By.ByCssSelector("#w3c_main > div:nth-child(2) > ul:nth-child(3) > li:nth-child(7) > a")),
                        Locator("Good - CSS", By.ByCssSelector(".w3c_leftCol a[href='WoT/']")),
                        Locator("Bad - XPath", By.ByXPath("//*[@id='w3c_main']/div[2]/ul[1]/li[7]/a")),
                        Locator("Full path - XPath", By.ByXPath("/html/body/div[1]/div[2]/div[2]/ul[1]/li[7]/a")),
                        Locator("Good - XPath", By.ByXPath("//div[@class='w3c_leftCol']//a[@href='WoT/']")))))
    }

    fun createRougouskiList(): Map<String, List<Locator>> {
        return mapOf<String, List<Locator>>(
                Pair("https://www.w3.org/", listOf(
                        Locator("Bad - CSS", By.ByCssSelector("#w3c_main > div:nth-child(2) > ul:nth-child(3) > li:nth-child(7) > a")),
                        Locator("Good - CSS by Adrian Rougouski", By.ByCssSelector("[class='w3c_leftCol'] a[href='WoT/']")),
                        Locator("""Good - CSS matching "Good - XPath" by Adrian Rougouski""", By.ByCssSelector("div[class='w3c_leftCol'] a[href='WoT/']")),
                        Locator("Bad - XPath", By.ByXPath("//*[@id='w3c_main']/div[2]/ul[1]/li[7]/a")),
                        Locator("Full path - XPath", By.ByXPath("/html/body/div[1]/div[2]/div[2]/ul[1]/li[7]/a")),
                        Locator("Good - XPath", By.ByXPath("//div[@class='w3c_leftCol']//a[@href='WoT/']")))))
    }
}
