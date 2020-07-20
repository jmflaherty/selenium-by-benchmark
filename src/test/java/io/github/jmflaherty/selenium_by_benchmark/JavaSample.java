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

package io.github.jmflaherty.selenium_by_benchmark;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class JavaSample {
  @Test
  public void JavaSingleByWithUrl() {
    Benchmark.run(
        Browsers.INSTANCE.getChrome().invoke(),
        "https://www.w3.org/",
        new By.ByXPath("//div[@class='w3c_leftCol']//a[@href='WoT/']"),
        10,
        true);
  }
}
