import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.ndm.messagedemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ndm.messagedemo"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled;true

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


}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-messaging:23.4.0")
    implementation("com.google.firebase:firebase-firestore:24.10.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //navigation
    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("androidx.navigation:navigation-ui:2.5.3")


    //circle
    implementation("de.hdodenhof:circleimageview:3.1.0")

    implementation("com.google.firebase:firebase-bom:32.7.0")

    implementation("androidx.multidex:multidex:2.0.1")


    implementation("androidx.room:room-runtime:2.4.3")


    annotationProcessor("androidx.room:room-compiler:2.4.0-beta01")

  /*  implementation("io.socket:socket.io-client:2.0.0") {
        exclude(group = "org.json", module = "json")
    } */

    implementation ("io.socket:socket.io-client:1.0.0")

    implementation("com.google.code.gson:gson:2.10")
}



