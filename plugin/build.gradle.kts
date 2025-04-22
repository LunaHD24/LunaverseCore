plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.5"
}

group = "dev.lunaa.lunaversecore"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    implementation("org.reflections:reflections:0.10.2")
    implementation(project(":api"))
}

tasks.compileJava {
    options.release = 21
}

tasks.test {
    useJUnitPlatform()
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.jar {
    enabled = false
}

tasks.shadowJar {
    archiveBaseName.set("LunaverseCore")
    archiveVersion.set(version.toString())
    archiveClassifier.set("")
}

tasks.register("cleanBuild") {
    group = "build"
    description = "Cleans and rebuilds the project."
    dependsOn(tasks.clean)
    finalizedBy(tasks.build)
}

tasks {
    javadoc {
        options {
            (this as CoreJavadocOptions).addBooleanOption("Xdoclint:none", true)
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
    withSourcesJar()
    withJavadocJar()
}