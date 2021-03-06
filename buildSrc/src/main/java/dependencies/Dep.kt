package dependencies

object Dep {

    object Plugin {
        const val gradle = "com.android.tools.build:gradle:7.1.3"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Google.Hilt.version}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.5.0"
        const val appCompat = "androidx.appcompat:appcompat:1.3.0"
        const val activityCompose = "androidx.activity:activity-compose:1.3.0-beta01"
        const val constraintLayoutCompose = "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha08"

        object Compose {
            const val version = "1.1.1"
            const val material = "androidx.compose.material:material:$version"
            const val runtime = "androidx.compose.runtime:runtime-livedata:$version"

            object UI {
                const val ui = "androidx.compose.ui:ui:$version"
                const val tooling = "androidx.compose.ui:ui-tooling:$version"
                const val test = "androidx.compose.ui:ui-test-junit4:$version"
            }
        }

        object Navigation {
            const val compose = "androidx.navigation:navigation-compose:2.4.0-alpha02"
        }

        object Hilt {
            const val navigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
        }

        object Test {
            const val junit = "androidx.test.ext:junit:1.1.2"
            const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
        }
    }

    object Google {
        const val material = "com.google.android.material:material:1.3.0"
        const val gson = "com.google.code.gson:gson:2.8.6"

        object Accompanist {
            const val version = "0.12.0"
            const val coil = "com.google.accompanist:accompanist-coil:$version"
            const val pager = "com.google.accompanist:accompanist-pager:$version"
            const val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:$version"
        }

        object Hilt {
            const val version = "2.41"
            const val android = "com.google.dagger:hilt-android:$version"
            const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        }
    }

    object JUnit {
        const val junit = "junit:junit:4.+"
    }

    object Square {
        object OkHttp {
            const val version = "4.9.0"
            const val okHttp = "com.squareup.okhttp3:okhttp:$version"
            const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        }

        object Retrofit {
            const val version = "2.9.0"
            const val retrofit = "com.squareup.retrofit2:retrofit:$version"
            const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
        }
    }

    object Other {
        const val kotlinResult = "com.michael-bull.kotlin-result:kotlin-result:1.1.10"
        const val timber = "com.jakewharton.timber:timber:4.7.1"
    }
}