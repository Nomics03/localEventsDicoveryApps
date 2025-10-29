plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.adli.eventdiscover"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.adli.eventdiscover"
        // DIKEMAS KINI: Dinaikkan kepada 23 untuk memenuhi keperluan Firebase Auth
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        packaging {
            resources {
                excludes += "res/drawable/name_of_the_duplicate_file.png"
                excludes += "res/layout/some_layout_to_exclude.xml"
                excludes += "res/drawable-v21/ic_some_icon.xml"
            }
        }

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    // BLOK YANG TELAH DIPERBAIKI
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Firebase BoM - Cara terbaik untuk mengurus versi Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.1"))
    //google_themes
    implementation("com.google.android.material:material:1.13.0")

    // Pustaka Firebase
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth:21.0.1")
    implementation("com.google.firebase:firebase-database:20.0.3")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}