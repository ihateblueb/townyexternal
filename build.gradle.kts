plugins {
    kotlin("jvm") version "2.2.0"
    id("com.gradleup.shadow") version "8.3.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "site.remlit.blueb"
version = "2025.7.1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven("https://repo.glaremasters.me/repository/towny/") {
        name = "glaremasters-repo"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    compileOnly("com.palmergames.bukkit.towny:towny:0.101.1.0")
    implementation("org.bstats:bstats-bukkit:3.0.2")

    implementation("io.ktor:ktor-server-core:3.2.0")
    implementation("io.ktor:ktor-server-core-jvm:3.2.0")
    implementation("io.ktor:ktor-server-host-common:3.2.0")
    implementation("io.ktor:ktor-server-netty:3.2.0")

    implementation("io.ktor:ktor-server-content-negotiation:3.2.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:3.2.0")
    implementation("io.ktor:ktor-server-status-pages:3.2.0")
    implementation("io.ktor:ktor-server-status-pages-jvm:3.2.0")
}

tasks {
    shadowJar {
        relocate("org.bstats", "site.remlit.blueb.townyexternal.bstats-bukkit")
    }
    runServer {
        minecraftVersion("1.19.4")
    }
}

val targetJavaVersion = 17
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
