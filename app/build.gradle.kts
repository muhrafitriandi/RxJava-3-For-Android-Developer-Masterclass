import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yandey.rxjava3_android"
    compileSdk = 34

    val localPropertiesFile = rootProject.file("local.properties")
    val localProperties = Properties()
    localProperties.load(localPropertiesFile.inputStream())

    defaultConfig {
        applicationId = "com.yandey.rxjava3_android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "BASE_URL", "\"${localProperties.getProperty("BASE_URL")}\"")

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // https://github.com/ReactiveX/RxJava
    implementation("io.reactivex.rxjava3:rxjava:3.1.8")

    // https://github.com/ReactiveX/RxAndroid
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")

    // https://square.github.io/retrofit/
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // https://github.com/square/retrofit/tree/trunk/retrofit-adapters/rxjava3
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.11.0")

    // https://github.com/square/okhttp
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // https://developer.android.com/jetpack/androidx/releases/activity
    implementation("androidx.activity:activity-ktx:1.9.0")
}