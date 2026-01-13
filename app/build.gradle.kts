import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id("org.jetbrains.kotlin.plugin.parcelize")
}

android {
    namespace = "chris.utils.AppLauncher"
    compileSdk = 36

    defaultConfig {
        applicationId = "chris.utils.AppLauncher"
        minSdk = 24
        targetSdk = 36
        versionCode = releaseTime().toInt()
        versionName = "1.3." + releaseTime()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
        }
    }

    applicationVariants.all {
        val variant = this
        variant.outputs.all {
            val output = this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
            output.outputFileName = "ChrisAppLauncher_${variant.versionName}${variant.buildType.name}.apk"
                .replace("[", "")
                .replace("]", "")
                .replace("debug", ".D")
                .replace("release", "")
        }
    }

    buildFeatures {
        dataBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    fileTree("libs") {
        include("*")
    }

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation (libs.gson)

}

fun releaseTime(): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyyMMddHH")
    return current.format(formatter)
}