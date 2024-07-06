package de.tuhh.quizi.functionality.add.content.usecases.di

import de.tuhh.quizi.functionality.add.content.usecases.AddCourseUseCase
import org.koin.dsl.module

val AddContentFunctionalityModule = module {
    factory<AddCourseUseCase> {
        AddCourseUseCase(get())
    }
}