package de.tuhh.quizi

import android.app.Application
import de.tuhh.quizi.di.appModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            modules(
//                appModule
//            )
//        }
    }
}
