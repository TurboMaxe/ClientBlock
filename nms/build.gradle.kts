import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    clientblock.common
    id("xyz.jpenilla.run-paper") version "2.3.1"
    id("com.gradleup.shadow")
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
        logger.info("Building ClientBlock (GrimAC) v${version}")
    }

    tasks.processResources {
        val props = mapOf("version" to version)
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("plugin.yml") {
            expand(props)
    }
}

