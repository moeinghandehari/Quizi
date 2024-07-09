package de.tuhh.quizi.functionality.add.content.usecases.di

import de.tuhh.quizi.functionality.add.content.usecases.AddCourseUseCase
import de.tuhh.quizi.functionality.add.content.usecases.AddTopicUseCase
import de.tuhh.quizi.functionality.add.content.usecases.GetCoursesUseCase
import de.tuhh.quizi.functionality.add.content.usecases.GetTopicsUseCase
import org.koin.dsl.module

val AddContentFunctionalityModule = module {
    factory<AddCourseUseCase> {
        AddCourseUseCase(get())
    }
    factory<GetCoursesUseCase> {
        GetCoursesUseCase(get())
    }

    factory<AddTopicUseCase> {
        AddTopicUseCase(get())
    }
    factory<GetTopicsUseCase> {
        GetTopicsUseCase(get())
    }
}