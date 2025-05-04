package de.tuhh.quizi.functionality.explore.content.data.api.di

import de.tuhh.quizi.core.api.di.QuiziApiModule
import de.tuhh.quizi.functionality.explore.content.data.api.QuizRemoteDataSource
import de.tuhh.quizi.functionality.explore.content.data.api.QuizRemoteDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val QuizNetworkModule = module {
    singleOf(::QuizRemoteDataSourceImpl) bind QuizRemoteDataSource::class

    includes(QuiziApiModule)
}