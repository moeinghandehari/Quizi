package de.tuhh.quizi.functionality.add.content.data.api.di

import de.tuhh.quizi.core.quizi.api.di.QuiziApiModule
import de.tuhh.quizi.functionality.add.content.data.api.AddContentRemoteDataSource
import de.tuhh.quizi.functionality.add.content.data.api.AddContentRemoteDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val AddContentNetworkModule = module {
    singleOf(::AddContentRemoteDataSourceImpl) bind AddContentRemoteDataSource::class

    includes(QuiziApiModule)
}