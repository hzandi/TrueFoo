plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = Versions.compileSdkVersion
    defaultConfig {
        applicationId = "com.truecaller.truefoo"
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion
        versionCode = Versions.versionCode
        versionName = Versions.versionName
        testInstrumentationRunner = "com.truecaller.android.CustomTestRunner"

        // vector drawable backward compatibility
        vectorDrawables.useSupportLibrary = true
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
    buildFeatures {
        viewBinding = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(Dependencies.Common.kotlinStdLib)
    implementation(Dependencies.Common.inject)
    implementation(Dependencies.Common.timber)
    implementation(Dependencies.Common.appCompat)
    implementation(Dependencies.App.coreKtx)

    // Navigation
    implementation(Dependencies.App.navigationFragment)
    implementation(Dependencies.App.navigationUi)

    implementation(Dependencies.App.constraintLayout)
    implementation(Dependencies.App.preference)
    implementation(Dependencies.App.security)
    implementation(Dependencies.App.material)

    // Calligraphy
    implementation(Dependencies.App.calligraphy)
    implementation(Dependencies.App.viewPump)

    // Dagger Hilt
    implementation(Dependencies.App.hilt)
    kapt(Dependencies.App.hiltAndroidCompiler)

    // Dagger Hilt ViewModel extension
    implementation(Dependencies.App.hiltViewModel)
    kapt(Dependencies.App.hiltCompiler)

    implementation(Dependencies.App.lifecycle)
    kapt(Dependencies.App.lifecycleCompiler)
    implementation(Dependencies.App.lifecycleViewModel)
    implementation(Dependencies.App.materialProgressBar)
    implementation(Dependencies.App.transitionsEverywhere)
    implementation(Dependencies.App.blurry)
    implementation(Dependencies.App.coil)

    // Test
    testImplementation(Dependencies.CommonTest.junit)
    testImplementation(Dependencies.CommonTest.truth)
    testImplementation(Dependencies.CommonTest.testCore)
    testImplementation(Dependencies.CommonTest.coreTesting)
    testImplementation(Dependencies.CommonTest.mockito)
    testImplementation(Dependencies.CommonTest.mockitoInline)
    testImplementation(Dependencies.CommonTest.coroutinesTest)
    testImplementation(Dependencies.CommonTest.mockitoKotlin)

    // Espresso
    androidTestImplementation(Dependencies.AppTest.espresso)
    androidTestImplementation(Dependencies.AppTest.testRunner)
    androidTestImplementation(Dependencies.AppTest.testRules)

    debugImplementation(Dependencies.AppTest.fragmentTesting)

    // Dagger Hilt instrumentation tests
    androidTestImplementation(Dependencies.AppTest.hiltAndroidTesting)
    kaptAndroidTest(Dependencies.AppTest.hiltAndroidCompiler)

    implementation(project(":data"))
}
