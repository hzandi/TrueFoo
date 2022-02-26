plugins {
    id("com.android.library")
    kotlin("android")
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
}
