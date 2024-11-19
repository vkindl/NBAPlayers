package io.github.vkindl.nbaplayers.feature.players.di

import androidx.paging.PagingSource
import io.github.vkindl.nbaplayers.feature.players.data.PlayerPagingSource
import io.github.vkindl.nbaplayers.feature.players.data.PlayerRepositoryImpl
import io.github.vkindl.nbaplayers.feature.players.domain.GetPlayersUseCase
import io.github.vkindl.nbaplayers.feature.players.domain.PlayerRepository
import io.github.vkindl.nbaplayers.feature.players.ui.PlayersViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val playersModule = module {
    singleOf(::PlayerRepositoryImpl) bind PlayerRepository::class
    factoryOf(::GetPlayersUseCase)
    singleOf(::PlayerPagingSource) bind PagingSource::class
    viewModelOf(::PlayersViewModel)
}
