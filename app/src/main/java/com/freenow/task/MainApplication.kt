package com.freenow.task

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @HiltAndroidApp triggers Hilt's code generation, including a base class for your application
 * that serves as the application-level dependency container.
 *
 * And annotation enables member injection (i.e) field and method injection in your Application class.
 */
@HiltAndroidApp
class MainApplication : Application() {

}