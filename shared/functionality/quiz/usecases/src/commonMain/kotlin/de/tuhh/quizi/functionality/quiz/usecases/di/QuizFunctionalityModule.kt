package de.tuhh.quizi.functionality.quiz.usecases.di

import de.tuhh.quizi.functionality.quiz.usecases.GetQuizByTopicUseCase
import org.koin.dsl.module

val QuizFunctionalityModule = module {
    factory<GetQuizByTopicUseCase> {
        GetQuizByTopicUseCase(get())
    }
}