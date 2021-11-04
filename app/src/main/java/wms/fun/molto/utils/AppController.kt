package wms.`fun`.molto.utils

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import timber.log.Timber
import wms.`fun`.molto.BuildConfig

class AppController :Application() {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: java.lang.StackTraceElement): String? {
                    return super.createStackElementTag(element) + ":" + element.lineNumber
                }
            })
        }

        loginState = getSharedPreferences(APP_SHAREDPREFERANCE, Context.MODE_PRIVATE)
    }
}