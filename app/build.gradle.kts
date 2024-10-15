plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "duyndph34554.fpoly.shop_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "duyndph34554.fpoly.shop_app"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures { //Dung de bat tat cac tinh nang tuy chon ma ban co thu su dung trong Android
        viewBinding=true //Cho phep de dang truy cap truc tiep cac thanh pha layout
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
//    Thu vien de su dung voi Firebase
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

//    Lifecycle cac thu vien ben duoi duoc su dung de quan ly vong doi, trang thai
//    dac biet khi su dung mo hinh MVVM
//    lifecycle-extensions cung cap cac tinh nang lien quan den vong doi
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
//    Cung cap cac extension Kotlin lam viec voi lifecycle ho tro coroutine va Flow
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
//    Thuc hien cac tac vu dong bo trong ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
//    Tich hop tot voi coroutine va giup quan ly du lieu mot cach de dang
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

//    ViewModel
//    Giup lam viec voi Activity tro nen de dang hon, ho tro coroutine
//    va cu phap Kotlin gon gang hon
    implementation("androidx.activity:activity-ktx:1.4.0")
//    ho tro tai, hien thi va cache hinh anh trong Android
    implementation("com.github.bumptech.glide:glide:4.12.0")
//    chuyen doi giua Json sang doi tuong va nguoc lai
    implementation("com.google.code.gson:gson:2.9.1")
//    Tao indicator dang cham tron cho ViewPager hoac ViewPager2
    implementation("com.tbuonomo:dotsindicator:5.0")
}