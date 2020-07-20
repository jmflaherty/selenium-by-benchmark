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
    `java-library`
    id("com.diffplug.spotless") version "5.1.0"
    jacoco
    id("org.jetbrains.dokka") version "0.10.1"
    `maven-publish`
}

tasks.wrapper {
    version = "6.5.1"
    distributionType = DistributionType.ALL
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        licenseHeaderFile("LICENSE.NOTICE")
    }
    java {
        target("**/*.java")
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        licenseHeaderFile("LICENSE.NOTICE")
    }
    kotlinGradle {
        target("*.gradle.kts")
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        ktlint()
    }
}

jacoco {
    toolVersion = "0.8.5"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-testng"))
    testImplementation("io.github.bonigarcia:webdrivermanager:4.1.0")
}

tasks.test {
    outputs.upToDateWhen { false }
    useTestNG()
    finalizedBy(
            tasks.jacocoTestReport,
            tasks.jacocoTestCoverageVerification
    )
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
    executionData(File("build/jacoco/test.exec"))
}
