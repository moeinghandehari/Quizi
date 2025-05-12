package de.tuhh.quizi.functionality.explore.content.data.api.di

import de.tuhh.quizi.core.api.di.QuiziApiModule
import de.tuhh.quizi.functionality.explore.content.data.api.ExploreContentRemoteDataSource
import de.tuhh.quizi.functionality.explore.content.data.api.ExploreContentRemoteDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val QuizNetworkModule = module {
    singleOf(::ExploreContentRemoteDataSourceImpl) bind ExploreContentRemoteDataSource::class

    includes(QuiziApiModule)
}