package io.github.vkindl.nbaplayers

import android.app.Application
import io.github.vkindl.nbaplayers.di.appModule
import io.github.vkindl.nbaplayers.feature.playerdetail.di.playerDetailModule
import io.github.vkindl.nbaplayers.feature.players.di.playersModule
import io.github.vkindl.nbaplayers.feature.team.di.teamModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NBAPlayersApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NBAPlayersApplication)
            modules(
                appModule,
                playerDetailModule,
                playersModule,
                teamModule
            )
        }
    }
}
