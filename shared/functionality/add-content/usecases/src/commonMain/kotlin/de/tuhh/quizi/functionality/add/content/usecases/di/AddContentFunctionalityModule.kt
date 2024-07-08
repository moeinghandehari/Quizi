package de.tuhh.quizi.functionality.add.content.usecases.di

import de.tuhh.quizi.functionality.add.content.usecases.AddCourseUseCase
import de.tuhh.quizi.functionality.add.content.usecases.GetCoursesUseCase
import org.koin.dsl.module

val AddContentFunctionalityModule = module {
    factory<AddCourseUseCase> {
        AddCourseUseCase(get())
    }
    factory<GetCoursesUseCase> {
        GetCoursesUseCase(get())
    }
}