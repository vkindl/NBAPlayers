package io.github.vkindl.nbaplayers.feature.team.di

import io.github.vkindl.nbaplayers.feature.team.data.TeamRepositoryImpl
import io.github.vkindl.nbaplayers.feature.team.domain.GetTeamUseCase
import io.github.vkindl.nbaplayers.feature.team.domain.TeamRepository
import io.github.vkindl.nbaplayers.feature.team.ui.TeamViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val teamModule = module {
    factoryOf(::GetTeamUseCase)
    singleOf(::TeamRepositoryImpl) bind TeamRepository::class
    viewModelOf(::TeamViewModel)
}
