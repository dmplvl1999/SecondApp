plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    //id("com.google.devtools.ksp")
}

android {
    namespace = "com.hackinghell.secondapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hackinghell.secondapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
        aidl = true
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
}


dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.core.ktx)
    implementation(libs.room.common)
    implementation(libs.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //implementation("com.android.support:recyclerview-v7:28.0.0")
    //implementation("com.android.support:design:28.0.0")
   // compile 'com.android.support:recyclerview-v7:23.1.1'
   // compile 'com.android.support:design:23.1.1'
    implementation (libs.kotlin.stdlib)
    //ksp(libs.androidx.room.compiler)
    //ksp("androidx.room:room-compiler:2.6.1")

}