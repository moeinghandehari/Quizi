package de.tuhh.quizi.ui.addcontent.topics.di

import de.tuhh.quizi.functionality.add.content.usecases.di.AddContentFunctionalityModule
import de.tuhh.quizi.ui.addcontent.topics.state.TopicsViewModel
import org.koin.dsl.module

val TopicsUiModule = module {
    factory<TopicsViewModel> { params -> TopicsViewModel(params.get(), get(), get(), get()) }

    includes(AddContentFunctionalityModule)
}