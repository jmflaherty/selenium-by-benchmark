/* Licensed under Apache-2.0 */
package selenium.by.benchmark

import kotlin.properties.Delegates
import org.openqa.selenium.By

data class Locator(
    val locatorName: String,
    val locatorBy: By
) {
    var average: Long by Delegates.notNull<Long>()
    var median: Long by Delegates.notNull<Long>()
    var mean: Long by Delegates.notNull<Long>()
    var maximum: Long by Delegates.notNull<Long>()
    var minimum: Long by Delegates.notNull<Long>()
    var total: Long by Delegates.notNull<Long>()
    lateinit var tries: List<Long>
}
