package io.github.vkindl.nbaplayers

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NBAPlayersApplication : Application()  {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NBAPlayersApplication)
            // modules(appModule)
        }
    }
}
