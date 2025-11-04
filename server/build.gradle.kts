plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.serialization)
    application
}

group = "org.example.expensewithserver"
version = "1.0.0"
application {
    mainClass.set("org.example.expensewithserver.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
     implementation(libs.ktor.serverCore)
     implementation(libs.ktor.server.content.negotiation)
     implementation(libs.ktor.server.status.pages)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.ktor.client.core)
     implementation(libs.ktor.client.cio)
     implementation(libs.ktor.client.logging)
     implementation(libs.ktor.client.content.negotiation)
     implementation(libs.ktor.client.auth)
     implementation(libs.ktor.client.json)
     implementation(libs.ktor.client.serialization)
}