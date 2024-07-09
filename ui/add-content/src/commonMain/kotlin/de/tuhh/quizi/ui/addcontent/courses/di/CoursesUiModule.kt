package de.tuhh.quizi.ui.addcontent.courses.di

import de.tuhh.quizi.functionality.add.content.usecases.di.AddContentFunctionalityModule
import de.tuhh.quizi.ui.addcontent.courses.state.CoursesViewModel
import org.koin.dsl.module

val CoursesUiModule = module {
    factory<CoursesViewModel> {
        CoursesViewModel(get(), get(), get())
    }

    includes(AddContentFunctionalityModule)
}