/* Licensed under Apache-2.0 */
package xpath.vs.css.benchmark

import io.github.bonigarcia.wdm.WebDriverManager
import kotlin.system.exitProcess
import kotlin.time.ExperimentalTime
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.RemoteWebDriver

@ExperimentalTime
fun main() {
    WebDriverManager.chromedriver().setup()
    WebDriverManager.firefoxdriver().setup()
    val drivers: MutableList<WebDriver> = mutableListOf<WebDriver>()
    drivers.add(ChromeDriver())
    drivers.add(FirefoxDriver())

    val locators: MutableList<Locator>? = mutableListOf()
    // locators?.add(Locator("Bad - CSS", By.ByCssSelector("#w3c_main > div:nth-child(2) > ul:nth-child(3) > li:nth-child(7) > a")))
    locators?.add(Locator("Good - CSS", By.ByCssSelector(".w3c_leftCol a[href='WoT/']")))
    locators?.add(Locator("Good - CSS by Adrian Rougouski", By.ByCssSelector("[class='w3c_leftCol'] a[href='WoT/']")))
    locators?.add(Locator("""Good - CSS matching "Good - XPath" by Adrian Rougouski""", By.ByCssSelector("div[class='w3c_leftCol'] a[href='WoT/']")))
    // locators?.add(Locator("Bad - XPath", By.ByXPath("//*[@id='w3c_main']/div[2]/ul[1]/li[7]/a")))
    // locators?.add(Locator("Full path - XPath", By.ByXPath("/html/body/div[1]/div[2]/div[2]/ul[1]/li[7]/a")))
    locators?.add(Locator("Good - XPath", By.ByXPath("//div[@class='w3c_leftCol']//a[@href='WoT/']")))

    drivers.forEach { driver ->
        println((driver as RemoteWebDriver).capabilities.browserName.toUpperCase() + "\n")
        driver.get("https://www.w3.org/")
        locators?.forEach { locator ->
            locator.tries = benchmarkFindElement(driver, locator = locator.locatorBy)
        }
        locators?.forEach { it.calculateStats() }
        locators?.forEach { it.printStats() }
    }

    drivers.forEach { driver -> driver.quit() }

    exitProcess(0)
}

fun benchmarkFindElement(driver: WebDriver, iterations: Int = 1000, locator: By): List<Long> {
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
