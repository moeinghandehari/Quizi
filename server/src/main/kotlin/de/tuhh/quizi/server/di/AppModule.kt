package de.tuhh.quizi.server.di

import de.tuhh.quizi.server.controller.QuizController
import de.tuhh.quizi.server.controller.QuizControllerImpl
import org.koin.dsl.module

val appModule = module {
    single<QuizController> { QuizControllerImpl(get()) }
}