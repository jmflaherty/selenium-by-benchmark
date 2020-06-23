/* Licensed under Apache-2.0 */
package selenium.by.benchmark

import kotlin.system.exitProcess
import kotlin.time.ExperimentalTime
import org.openqa.selenium.WebDriver

@ExperimentalTime
fun main() {
    val driversList: List<() -> WebDriver> = listOf(Browsers.chrome, Browsers.firefox)
    // ToDo: 2. Define locators. Maybe use files. JSON, HOCON, CVS ??  WEBSITE ,  NAME - LOCATOR STRATEGY - LOCATOR STRING
    val locatorsList: Map<String, List<Locator>> = LocatorsListFactory.createOriginalList()
    // ToDo: 3. Invoke a function that receives as params the browser array and the locators, that stores
    //  all the basic stats for the browser-locators combo, so they can be later used when invoking another function using
    //  inversion of control over the stats and so generating all the metrics and comparisons to declare the winners
    //  on different categories

    // ToDo: Break 3. down !!!
    Benchmark(driversList, locatorsList, 10)

    exitProcess(0)
}
