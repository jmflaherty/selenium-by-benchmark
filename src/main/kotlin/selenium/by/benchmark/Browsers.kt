/* Licensed under Apache-2.0 */
package selenium.by.benchmark

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.opera.OperaDriver
import org.openqa.selenium.safari.SafariDriver

object Browsers {
    val chrome: () -> ChromeDriver = {
        WebDriverManager.chromedriver().setup()
        ChromeDriver()
    }
    val firefox: () -> FirefoxDriver = {
        WebDriverManager.firefoxdriver().setup()
        FirefoxDriver()
    }
    val opera: () -> OperaDriver = {
        WebDriverManager.operadriver().setup()
        OperaDriver()
    }
    val edge: () -> EdgeDriver = {
        WebDriverManager.edgedriver().setup()
        EdgeDriver()
    }
    val internetExplorer: () -> InternetExplorerDriver = {
        WebDriverManager.iedriver().setup()
        InternetExplorerDriver()
    }
    val safari: () -> SafariDriver = {
        SafariDriver()
    }
}
