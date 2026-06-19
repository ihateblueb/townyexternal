plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.serialization") version "2.2.21"
    id("com.gradleup.shadow") version "8.3.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "site.remlit"
version = "2026.6.0"

repositories {
    mavenCentral()
    maven("https://repo.remlit.site/mirror")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:2.3.21")

    compileOnly("com.palmergames.bukkit.towny:towny:0.101.1.0")
    compileOnly("co.aikar:acf-bukkit:0.5.1-SNAPSHOT")
    implementation("org.bstats:bstats-bukkit:3.2.1")

    implementation("io.ktor:ktor-server-core-jvm:3.5.0")
    implementation("io.ktor:ktor-server-host-common-jvm:3.5.0")
    implementation("io.ktor:ktor-server-netty-jvm:3.5.0")

    implementation("io.ktor:ktor-server-content-negotiation-jvm:3.5.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:3.5.0")
    implementation("io.ktor:ktor-server-status-pages-jvm:3.5.0")
    implementation("io.ktor:ktor-server-cors-jvm:3.5.0")

    compileOnly("redis.clients:jedis:7.5.0")
    compileOnly("com.zaxxer:HikariCP:7.0.2")
    compileOnly("com.h2database:h2:2.4.240")
    compileOnly("org.postgresql:postgresql:42.7.11")
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
