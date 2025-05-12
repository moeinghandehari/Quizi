package de.tuhh.quizi.functionality.quiz.data.implementations.di

import de.tuhh.quizi.functionality.quiz.abstractions.QuizRepository
import de.tuhh.quizi.functionality.quiz.data.api.di.QuizNetworkModule
import de.tuhh.quizi.functionality.quiz.data.implementations.QuizRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val QuizDataModule = module {
    singleOf(::QuizRepositoryImpl) bind QuizRepository::class

    includes(QuizNetworkModule)
}