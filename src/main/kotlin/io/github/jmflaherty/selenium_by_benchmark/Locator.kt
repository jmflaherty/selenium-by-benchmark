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

import kotlin.properties.Delegates
import org.openqa.selenium.By

/**
 * This class stores a [By] to be benchmarked, along with a friendly name to easily identify it,
 * and it also saves the times of all tries to find an element with the By in a list for later processing.
 */
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
