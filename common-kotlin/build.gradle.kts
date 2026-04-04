import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    clientblock.common
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "io.turbo.clientblockkotlin"
version = "1"

repositories {
  mavenCentral()
}

dependencies {
    paperweight.paperDevBundle("1.21.10-R0.1-SNAPSHOT")
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

    register<Wrapper>("wrapper") {
        gradleVersion = "9.3.1"
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
