# Selenium By Benchmark

![Lesser General Public License v3](assets/lgplv3-with-text-154x68.png)<a href="https://selenium.dev"><img src="./assets/selenium.png" height="68" alt="Selenium"/></a><a href="https://kotlinlang.org/"><img src="./assets/kotlin.png" height="68" alt="Kotlin"/></a>

This JVM compatible library allows you to benchmark any Selenium element locator on a given driver, or combination of them. Multiple selectors can be benchmarked on the same run, so you can compare how they perform when put side by side just for fun, or to be able to make an informed decision on which to use for your automation.

Selenium By Benchmark was completely developed in Kotlin by [Juan Manuel Flaherty](https://www.linkedin.com/in/juanmflaherty/) on May of 2020 and then released on July of the same year as free software under the [GNU LGPL-3.0](assets/lgpl-3.0.md) license.

## Compatibility
[Documentation available through this link.](https://jmflaherty.github.io/selenium-by-benchmark/index.html)

Selenium By Benchmark has officially only been validated to work on Kotlin 1.3.72+ and Java 1.6+, but it should with any Java compatible language, as Kotlin compiles to Java bytecode.

You are more than welcome to try different languages and report if the library works or not with them.

[Selenium Java 3.141.59](https://search.maven.org/artifact/org.seleniumhq.selenium/selenium-java/3.141.59/jar) was used to develop the library, therefore any Selenium Java 3.x.x should work with it.

Some tests were made with Selenium Java 4 alpha builds and it worked just fine, so once released it should continue to work as is. However, a new version of the library will be released regardless, just to ensure compatibility with updated dependencies. 

## How to use it
The `Benchmark` object in this library has a function called `run` with several overloads. Use your IDE's intellisense to see them all and pick the one works best for your needs. All overloads are documented with KDoc, so you can know how they work and differ from each other.

You can use the library as shown next, in Kotlin and Java.

### Kotlin

```kotlin
val driver = ChromeDriver()
driver.get("https://www.w3.org/")
Benchmark.run(
    driver = driver,
    by = By.ByXPath("//div[@class='w3c_leftCol']//a[@href='WoT/']"),
    iterations = 10,
    printStats = true
)
```

### Java

```java
WebDriver driver = new ChromeDriver();
driver.get("https://www.w3.org/");
Benchmark.run(
    driver,
    new By.ByXPath("//div[@class='w3c_leftCol']//a[@href='WoT/']"),
    10,
    true
);
```

## License

Selenium By Benchmark is licensed under [GNU LGPL-3.0](assets/lgpl-3.0.md).

([HTML version of GNU LGPL-3.0 at www.gnu.org](https://www.gnu.org/licenses/lgpl-3.0.html))

Basically, what this means is that you are free to use the library in any kind of project you want -even commercial and privative ones-, but if you make changes to it, you must publish the code with this same [GNU LGPL-3.0](https://www.gnu.org/licenses/lgpl-3.0.html) license, while making clear how your version differs from this one.