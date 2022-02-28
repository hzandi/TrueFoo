plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    compileSdk = Versions.compileSdkVersion
    defaultConfig {
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(Dependencies.Common.kotlinStdLib)
    implementation(Dependencies.Common.inject)
    implementation(Dependencies.Common.timber)
    implementation(Dependencies.Common.coroutinesCore)
    implementation(Dependencies.Common.coroutinesAndroid)
    implementation(Dependencies.Data.retrofit)
    implementation(Dependencies.Data.retrofitJsonConverter)
    implementation(Dependencies.Data.retrofitScalarsConverter)
    implementation(Dependencies.Data.okhttpLoggingInterceptor)
    implementation(Dependencies.Data.gson)
    implementation(Dependencies.Data.jsoup)

    // Test
    testImplementation(Dependencies.CommonTest.junit)
    testImplementation(Dependencies.CommonTest.truth)
    testImplementation(Dependencies.CommonTest.testCore)
    testImplementation(Dependencies.CommonTest.coreTesting)
    testImplementation(Dependencies.CommonTest.mockito)
    testImplementation(Dependencies.CommonTest.mockitoInline)
    testImplementation(Dependencies.CommonTest.coroutinesTest)

    // Espresso
    androidTestImplementation(Dependencies.AppTest.espresso)
    androidTestImplementation(Dependencies.AppTest.testRunner)
    androidTestImplementation(Dependencies.AppTest.testRules)

    debugImplementation(Dependencies.AppTest.fragmentTesting)

    // Dagger Hilt
    implementation(Dependencies.App.hilt)
    kapt(Dependencies.App.hiltAndroidCompiler)

    // Dagger Hilt ViewModel extension
    implementation(Dependencies.App.hiltViewModel)
    kapt(Dependencies.App.hiltCompiler)

    // Dagger Hilt instrumentation tests
    androidTestImplementation(Dependencies.AppTest.hiltAndroidTesting)
    kaptAndroidTest(Dependencies.AppTest.hiltAndroidCompiler)
}
