import java.net.URL
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType

description = "A lightweight library to benchmark Selenium By locators"
version = "0.1.1"
project.ext.set("author", "Juan Manuel Flaherty")
project.ext.set("linkedIn", "https://www.linkedin.com/in/juanmflaherty/")
project.ext.set("repository", "https://github.com/jmflaherty/selenium-by-benchmark")
project.ext.set("license", "GNU LGPL v3")
project.ext.set("licenseCopy", "https://www.gnu.org/licenses/lgpl-3.0.html")

plugins {
    kotlin("jvm") version "1.5.21"
    `java-library`
    id("com.diffplug.spotless") version "5.14.2"
    jacoco
    id("org.jetbrains.dokka") version "1.5.0"
    `maven-publish`
}

tasks.wrapper {
    version = "7.1.1"
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
    toolVersion = "0.8.7"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-testng"))
    testImplementation("io.github.bonigarcia:webdrivermanager:4.4.3")
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
        xml.required.set(true)
        html.required.set(true)
    }
    executionData(buildDir.resolve("jacoco/test.exec"))
}

tasks.dokkaHtml {
    outputDirectory.set(projectDir.resolve("docs"))
    dokkaSourceSets {
        configureEach {
            displayName.set("JVM")
            sourceLink {
                localDirectory.set(file("src/main/kotlin"))
                remoteUrl.set(URL("https://github.com/jmflaherty/selenium-by-benchmark/tree/${version ?: "master"}/"))
                remoteLineSuffix.set("#L")
            }
        }
    }
}
