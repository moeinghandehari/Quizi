package de.tuhh.quizi.functionality.add.content.data.implementations.di

import de.tuhh.quizi.functionality.add.content.abstractions.AddContentRepository
import de.tuhh.quizi.functionality.add.content.data.api.di.AddContentNetworkModule
import de.tuhh.quizi.functionality.add.content.data.implementations.AddContentRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val AddContentDataModule = module {
    singleOf(::AddContentRepositoryImpl) bind AddContentRepository::class

    includes(AddContentNetworkModule)
}