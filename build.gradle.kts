import org.gradle.api.tasks.wrapper.Wrapper.DistributionType

plugins {
    kotlin("jvm") version "1.3.72"
    id("com.diffplug.gradle.spotless") version "4.3.0"
    application
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
        licenseHeader("/* Licensed under Apache-2.0 */") // License header
        // licenseHeaderFile("path-to-license-file")    // License header file
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
    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
    implementation("io.github.bonigarcia:webdrivermanager:4.0.0")
}

application {
    mainClassName = "selenium.by.benchmark.AppKt"
}
