package de.tuhh.quizi.server.di

import de.tuhh.quizi.server.controller.ContentController
import de.tuhh.quizi.server.controller.ContentControllerImpl
import de.tuhh.quizi.server.controller.QuizController
import de.tuhh.quizi.server.controller.QuizControllerImpl
import org.koin.dsl.module

val appModule = module {
    single<ContentController> { ContentControllerImpl(get()) }
    single<QuizController> { QuizControllerImpl(get()) }
}