import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "2.3.20"
    id("com.gradleup.shadow") version "8.3.0"
    id("xyz.jpenilla.run-paper") version "3.0.2"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.19"
}

group = "io.turbo"
version = "1"
val vendorJvm = JvmVendorSpec.JETBRAINS

repositories {
    mavenCentral()
    maven("https://repo.codemc.io/repository/maven-releases/")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    paperweight.paperDevBundle("1.21.11-R0.1-SNAPSHOT")
}

tasks {
    named<ShadowJar>("shadowJar") {

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveClassifier = ""
    }

    named<DefaultTask>("assemble") {
        dependsOn(named("shadowJar"))
    }

    named<Wrapper>("wrapper") {
        distributionType = Wrapper.DistributionType.ALL
    }

    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }

    withType<AbstractArchiveTask>().configureEach {
        isPreserveFileTimestamps = false
        isReproducibleFileOrder = true
    }
}

    val targetJavaVersion = 21
    kotlin {
        jvmToolchain(targetJavaVersion)
    }

    tasks.build {
        dependsOn("shadowJar")
    }

    tasks.processResources {
        val props = mapOf("version" to version)
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("plugin.yml") {
            expand(props)
    }
}

