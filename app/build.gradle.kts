plugins {
    id("com.android.application")
    // Add the Google services Gradle plugin
    id ("com.google.gms.google-services")

}

android {
    namespace = "com.finalproject.travelya"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.finalproject.travelya"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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

    buildFeatures{
        viewBinding = true
        mlModelBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database:20.3.1")
    implementation("com.google.firebase:firebase-firestore:24.11.1")
    implementation("androidx.activity:activity:1.8.0")
    implementation("org.tensorflow:tensorflow-lite-support:0.1.0")
    implementation("org.tensorflow:tensorflow-lite-metadata:0.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.google.android.material:material:1.13.0-alpha01")
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))

    // Firebase Authentication
    implementation("com.google.firebase:firebase-auth:22.3.1")

    // Firebase Realtime Database
    implementation("com.google.firebase:firebase-database:20.3.1")

    implementation("androidx.camera:camera-camera2:1.4.0-alpha05")
    implementation("androidx.camera:camera-lifecycle:1.4.0-alpha05")
    implementation("androidx.camera:camera-view:1.4.0-alpha05")

    implementation ("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("com.google.android.gms:play-services-location:21.3.0")


    implementation ("org.tensorflow:tensorflow-lite:2.7.0")
    implementation ("org.tensorflow:tensorflow-lite-support:0.3.1")
    implementation ("org.tensorflow:tensorflow-lite-metadata:0.1.0")



}