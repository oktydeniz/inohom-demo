plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id ("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.odeniz.inohom"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.odeniz.inohom"
        minSdk = 28
        this.targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debugbuild"
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "appType"
    productFlavors {
        create("staging") {
            dimension = "appType"
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-debug"
            buildConfigField("String", "BASE_URL", "\"85.105.107.53\"")
            buildConfigField("int", "BASE_PORT", "9095")
        }
        create("product") {
            dimension = "appType"
            applicationIdSuffix = ".product"
            versionNameSuffix = "-prod"
            buildConfigField("String", "BASE_URL", "\"85.105.107.53\"")
            buildConfigField("int", "BASE_PORT", "9095")
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
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation (libs.androidx.core.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.fragment.ktx)

    // OkHttp
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)

    // Gson
    implementation (libs.gson)

    // Kotlin Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    // Dependency Injection (Hilt)
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)


    // Navigation UI - VM - LiveData
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    // Recyclerview
    implementation (libs.androidx.recyclerview)
}