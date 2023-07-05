// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    kotlin("android").version("1.8.21").apply(false)
    id("com.android.library").version("8.0.2").apply(false)
    id("com.android.application").version("8.0.2").apply(false)
    id("com.google.dagger.hilt.android").version("2.44").apply(false)
    id("com.google.gms.google-services").version("4.3.14").apply(false)
    id("com.google.firebase.crashlytics").version("2.9.1").apply(false)
    id("com.google.firebase.firebase-perf").version("1.4.1").apply(false)
}
