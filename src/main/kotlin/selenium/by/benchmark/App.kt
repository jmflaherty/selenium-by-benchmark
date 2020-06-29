/* Licensed under Apache-2.0 */
package selenium.by.benchmark

import kotlin.system.exitProcess
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    // val driversList: List<() -> WebDriver> = listOf(Browsers.chrome, Browsers.firefox)
    // ToDo: 1. Define locators. Maybe use files. JSON, HOCON, CVS ??  WEBSITE ,  NAME - LOCATOR STRATEGY - LOCATOR STRING
    // val locatorsList: Map<String, List<Locator>> = LocatorsListFactory.createOriginalList()
    // ToDo: 2. Implement another function using inversion of control over the stats to generate all the metrics
    //  and comparisons to declare the winners on different categories
    Benchmark(
            driversList = listOf(Browsers.chrome, Browsers.firefox),
            // locatorsList = LocatorsListFactory.createOriginalList(),
            locatorsList = LocatorsListFactory.createListFromCSV(),
            iterations = 10
    )
    exitProcess(0)
}
