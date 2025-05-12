package de.tuhh.quizi.functionality.explore.content.data.implementations.di

import de.tuhh.quizi.functionality.explore.content.abstractions.ExploreContentRepository
import de.tuhh.quizi.functionality.explore.content.data.api.di.QuizNetworkModule
import de.tuhh.quizi.functionality.explore.content.data.implementations.ExploreContentRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val ExploreContentDataModule = module {
    singleOf(::ExploreContentRepositoryImpl) bind ExploreContentRepository::class

    includes(QuizNetworkModule)
}