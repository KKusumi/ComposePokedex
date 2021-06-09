package dependencies

object Dep {

    object Plugin {
        const val gradle = "com.android.tools.build:gradle:7.0.0-beta03"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.5.0"
        const val appCompat = "androidx.appcompat:appcompat:1.3.0"
        const val activityCompose = "androidx.activity:activity-compose:1.3.0-beta01"

        object Test {
            const val junit = "androidx.test.ext:junit:1.1.2"
            const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
        }
    }

    object Google {
        const val material = "com.google.android.material:material:1.3.0"
    }

    object Compose {
        const val version = "1.0.0-beta08"
        const val material = "androidx.compose.material:material:$version"

        object UI {
            const val ui = "androidx.compose.ui:ui:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val test = "androidx.compose.ui:ui-test-junit4:$version"
        }
    }

    object JUnit {
        const val junit = "junit:junit:4.+"
    }
}