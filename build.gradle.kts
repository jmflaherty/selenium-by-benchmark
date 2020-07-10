import org.gradle.api.tasks.wrapper.Wrapper.DistributionType

description = "A lightweight library to benchmark Selenium By locators"
version = "0.1.0"
project.ext.set("author", "Juan Manuel Flaherty")
project.ext.set("linkedIn", "https://www.linkedin.com/in/juanmflaherty/")
project.ext.set("repository", "https://github.com/jmflaherty/selenium-by-benchmark")
project.ext.set("license", "GNU LGPL v3")
project.ext.set("licenseCopy", "https://www.gnu.org/licenses/lgpl-3.0.html")

plugins {
    kotlin("jvm") version "1.3.72"
    id("com.diffplug.gradle.spotless") version "4.3.0"
    `java-library`
}

tasks.wrapper {
    version = "6.5"
    distributionType = DistributionType.ALL
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        licenseHeaderFile("LICENSE.NOTICE") // License header file
    }
    kotlinGradle {
        target("*.gradle.kts")
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        ktlint()
    }
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.seleniumhq.jmflaherty.selenium:jmflaherty.selenium-java:3.141.59")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-testng"))
    testImplementation("io.github.bonigarcia:webdrivermanager:4.0.0")
}

tasks.test {
    outputs.upToDateWhen { false }
    useTestNG {
        reports.html.isEnabled = true
    }
}
