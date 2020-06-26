/* Licensed under Apache-2.0 */
package selenium.by.benchmark

import kotlin.properties.Delegates
import org.openqa.selenium.By

data class Locator(
    val locatorName: String,
    val locatorBy: By
) {
    var average: Long by Delegates.notNull()
    var median: Long by Delegates.notNull()
    var mean: Long by Delegates.notNull()
    var maximum: Long by Delegates.notNull()
    var minimum: Long by Delegates.notNull()
    var total: Long by Delegates.notNull()
    lateinit var tries: List<Long>
}
