package io.github.vkindl.nbaplayers.feature.playerdetail.di

import io.github.vkindl.nbaplayers.feature.playerdetail.data.PlayerDetailRepositoryImpl
import io.github.vkindl.nbaplayers.feature.playerdetail.domain.GetPlayerUseCase
import io.github.vkindl.nbaplayers.feature.playerdetail.domain.PlayerDetailRepository
import io.github.vkindl.nbaplayers.feature.playerdetail.ui.PlayerDetailViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val playerDetailModule = module {
    factoryOf(::GetPlayerUseCase)
    viewModelOf(::PlayerDetailViewModel)
    singleOf(::PlayerDetailRepositoryImpl) bind PlayerDetailRepository::class
}
