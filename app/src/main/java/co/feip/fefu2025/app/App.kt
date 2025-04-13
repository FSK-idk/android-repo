package co.feip.fefu2025.app

import android.app.Application
import co.feip.fefu2025.di.dataModule
import co.feip.fefu2025.di.domainModule
import co.feip.fefu2025.di.navModule
import co.feip.fefu2025.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(
                listOf(
                    presentationModule,
                    domainModule,
                    dataModule,
                    navModule
                )
            )
        }
    }
}