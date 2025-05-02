package de.tuhh.quizi.ui.explorecontent.courses.di

import de.tuhh.quizi.functionality.explore.content.usecases.di.QuizFunctionalityModule
import de.tuhh.quizi.ui.explorecontent.courses.state.CoursesViewModel
import org.koin.dsl.module

val CoursesUiModule = module {
    factory<CoursesViewModel> { CoursesViewModel(get(), get()) }

    includes(QuizFunctionalityModule)
}