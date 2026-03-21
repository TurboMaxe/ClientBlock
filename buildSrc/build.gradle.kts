plugins {
   `kotlin-dsl`
   id("xyz.jpenilla.run-paper") version "2.3.1"
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.jpenilla.xyz/releases/")
}

dependencies {
    implementation("com.gradleup.shadow:shadow-gradle-plugin:8.3.0")
    implementation("io.papermc.paperweight:paperweight-userdev:2.0.0-beta.19")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.3.20")
}