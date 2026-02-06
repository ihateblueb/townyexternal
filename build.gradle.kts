plugins {
    kotlin("jvm") version "2.2.0"
    kotlin("plugin.serialization") version "2.2.0"
    id("com.gradleup.shadow") version "8.3.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "site.remlit"
version = "2026.2.1"

repositories {
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven("https://repo.glaremasters.me/repository/towny/") {
        name = "glaremasters-repo"
    }
    maven("https://repo.aikar.co/content/groups/aikar/") {
        name = "aikar-repo"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:2.3.0")

    compileOnly("com.palmergames.bukkit.towny:towny:0.101.1.0")
    compileOnly("co.aikar:acf-bukkit:0.5.1-SNAPSHOT")
    implementation("org.bstats:bstats-bukkit:3.0.2")

    implementation("io.ktor:ktor-server-core-jvm:3.4.0")
    implementation("io.ktor:ktor-server-host-common-jvm:3.4.0")
    implementation("io.ktor:ktor-server-netty-jvm:3.4.0")

    implementation("io.ktor:ktor-server-content-negotiation-jvm:3.4.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:3.4.0")
    implementation("io.ktor:ktor-server-status-pages-jvm:3.4.0")
    implementation("io.ktor:ktor-server-cors-jvm:3.4.0")

    compileOnly("redis.clients:jedis:6.0.0")
    compileOnly("com.zaxxer:HikariCP:6.3.0")
    compileOnly("com.h2database:h2:2.3.230")
    compileOnly("org.postgresql:postgresql:42.7.7")
}

tasks {
    shadowJar {
        relocate("org.bstats", "site.remlit.townyexternal.bstats-bukkit")
        relocate("io.ktor", "site.remlit.townyexternal.ktor")
        relocate("io.netty", "site.remlit.townyexternal.netty")
    }
    runServer {
        minecraftVersion("1.21.10")
    }
}

val targetJavaVersion = 21

kotlin {
    jvmToolchain(targetJavaVersion)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
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
