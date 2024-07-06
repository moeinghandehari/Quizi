package de.tuhh.quizi.ui.quiz.di

import de.tuhh.quizi.ui.quiz.state.QuizViewModel
import org.koin.dsl.module

val QuizUiModule = module {
    factory<QuizViewModel> {
        QuizViewModel(get())
    }
}