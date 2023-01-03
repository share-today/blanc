plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.wswon.blanc"
    compileSdk = Versions.Android.compileSdkVersion

    defaultConfig {
        applicationId = "com.wswon.blanc"
        minSdk = Versions.Android.minSdkVersion
        targetSdk = Versions.Android.targetSdkVersion
        versionCode = 1
        val version = "1.0.0"
        versionName = version

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
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
    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompile
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":core-common"))


    implementation(platform("com.google.firebase:firebase-bom:28.4.0"))

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.material3:material3:1.1.0-alpha03")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")


//    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")

    implementation(Deps.Compose.composeLiveData)
    implementation(Deps.Compose.composeMaterial)
    implementation(Deps.Compose.composeFoundation)
    implementation(Deps.Compose.composeUI)
    implementation(Deps.Compose.composeUiTool)
    implementation(Deps.Compose.composeUiToolPreview)
    implementation(Deps.Compose.composeUiTest)
    implementation(Deps.Compose.composeViewModel)
    implementation(Deps.Compose.composeCoil)
    implementation(Deps.Compose.composeConstraintLayout)

    implementation(Deps.Firebase.analytics)
    implementation(Deps.Firebase.crashlytics)
    implementation(Deps.Firebase.config)

    implementation(Deps.Android.X.core)
    implementation(Deps.Android.X.activity)
    implementation(Deps.Android.X.fragment)
    implementation(Deps.Android.X.appCompat)
    implementation(Deps.Android.X.constraintLayout)
    implementation(Deps.Android.X.recyclerView)
    implementation(Deps.Android.X.browser)

    implementation(Deps.Android.material)

    implementation(Deps.Rx.rxAndroid)
    implementation(Deps.Rx.rxJava)

    implementation(Deps.Hilt.hilt)
    kapt(Deps.Hilt.hiltCompiler)

    testImplementation(Deps.Test.archCore)
    testImplementation(Deps.Test.mockk)

    implementation(Deps.Room.room)
    kapt(Deps.Room.roomCompiler)
    implementation(Deps.Room.roomRx)

}