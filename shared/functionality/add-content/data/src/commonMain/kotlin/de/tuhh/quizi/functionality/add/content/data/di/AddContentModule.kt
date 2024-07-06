package de.tuhh.quizi.functionality.add.content.data.di

import de.tuhh.quizi.functionality.add.content.abstractions.AddContentRepository
import de.tuhh.quizi.functionality.add.content.data.AddContentRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val AddContentModule = module {
    singleOf(::AddContentRepositoryImpl) bind AddContentRepository::class
}