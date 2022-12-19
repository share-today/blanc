object Deps {
    object Android {
        object X {
            const val core = "androidx.core:core-ktx:${Versions.coreVersion}"
            const val activity = "androidx.activity:activity-ktx:${Versions.activityVersion}"
            const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"
            const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
            const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
            const val browser = "androidx.browser:browser:${Versions.browserVersion}"
        }
        const val material = "com.google.android.material:material:${Versions.materialVersion}"
    }

    object Firebase {
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
        const val config = "com.google.firebase:firebase-config-ktx"
    }

    object Kakao {
    }

    object Rx {
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroidVersion}"
        const val rxJava = "io.reactivex.rxjava3:rxkotlin:${Versions.rxJavaVersion}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
        const val rxAdapter = "com.squareup.retrofit2:adapter-rxjava3:${Versions.rxAdapterVersion}"
    }

    const val permission = "io.github.ParkSangGwon:tedpermission:${Versions.tedPermissionVersion}"

    object Test {
        const val archCore = "androidx.arch.core:core-testing:${Versions.Test.coreTestingVersion}"
        const val mockk = "io.mockk:mockk:${Versions.Test.mockkVersion}"
    }

    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
    }

    object Room {
        const val room = "androidx.room:room-runtime:${Versions.roomVersion}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
        const val roomRx = "androidx.room:room-rxjava3:${Versions.roomVersion}"
    }

    object Compose {
        const val composeUI = "androidx.compose.ui:ui:${Versions.composeVersion}"
        const val composeUiTool = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
        const val composeUiToolPreview ="androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
        const val composeFoundation =
            "androidx.compose.foundation:foundation:${Versions.composeVersion}"
        const val composeMaterial = "androidx.compose.material:material:${Versions.composeVersion}"
        const val composeIconCore =
            "androidx.compose.material:material-icons-core:${Versions.composeVersion}"
        const val composeIconsExtended =
            "androidx.compose.material:material-icons-extended:${Versions.composeVersion}"
        const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
        const val composeViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"
        const val composeLiveData =
            "androidx.compose.runtime:runtime-livedata:${Versions.composeVersion}"
        const val composeRxJava =
            "androidx.compose.runtime:runtime-rxjava2:${Versions.composeVersion}"
        const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"
        const val composeNavigation =
            "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
        const val composeHilt = "androidx.hilt:hilt-navigation-compose:${Versions.composeHilt}"
        const val composeAnimation =
            "androidx.compose.animation:animation:${Versions.composeVersion}"

        const val composeCoil = "io.coil-kt:coil-compose:${Versions.composeCoil}"
        const val composeConstraintLayout =
            "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayout}"
        const val composePaging = "androidx.paging:paging-compose:${Versions.composePaging}"
    }
}