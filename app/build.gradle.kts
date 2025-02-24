plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
//    alias(libs.plugins.googleServices)
}

android {
    namespace = "com.example.todoapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.todoapp"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.cardview)
    implementation("androidx.compose.material:material-icons-extended:1.0.5")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation(libs.androidx.runtime.livedata)
    implementation("androidx.core:core-ktx:1.15.0")
//    implementation(libs.firebase.database)
//    implementation(platform(libs.firebase.bom))
//    implementation(libs.firebase.crashlytics)

    implementation(platform("com.google.firebase:firebase-bom:31.0.2"))

    // Firebase Realtime Database
    implementation("com.google.firebase:firebase-database-ktx")

    // Firebase Firestore
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Firebase Authentication (si lo usas)
    implementation("com.google.firebase:firebase-auth-ktx")

    // Firebase Analytics (opcional)
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.runner)
    implementation(libs.androidx.navigation.testing)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    val media3_version = "1.4.1"
    // For media playback using ExoPlayer
    implementation("androidx.media3:media3-exoplayer:$media3_version")
    implementation("androidx.media3:media3-ui:$media3_version")
    implementation("com.airbnb.android:lottie-compose:6.6.2")
}