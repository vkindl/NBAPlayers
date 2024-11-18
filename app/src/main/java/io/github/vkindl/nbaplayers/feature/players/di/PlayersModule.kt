package io.github.vkindl.nbaplayers.feature.players.di

import androidx.paging.PagingSource
import io.github.vkindl.nbaplayers.core.domain.model.Player
import io.github.vkindl.nbaplayers.feature.players.data.PlayerRepositoryImpl
import io.github.vkindl.nbaplayers.feature.players.domain.PlayerRepository
import io.github.vkindl.nbaplayers.feature.players.ui.PlayersViewModel
import io.github.vkindl.nbaplayers.feature.players.data.PlayerPagingSource
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val playersModule = module {
    singleOf(::PlayerRepositoryImpl) bind PlayerRepository::class
    single<PagingSource<Int, Player>> { PlayerPagingSource(get()) }
    viewModelOf(::PlayersViewModel)
}
