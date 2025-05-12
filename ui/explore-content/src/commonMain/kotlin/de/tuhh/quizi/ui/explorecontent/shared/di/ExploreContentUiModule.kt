package de.tuhh.quizi.ui.explorecontent.shared.di

import de.tuhh.quizi.functionality.explore.content.usecases.di.ExploreContentFunctionalityModule
import de.tuhh.quizi.ui.explorecontent.shared.state.ExploreContentSharedViewModel
import org.koin.dsl.module

val AddContentUiModule = module {
    single<ExploreContentSharedViewModel> {
        ExploreContentSharedViewModel()
    }

    includes(ExploreContentFunctionalityModule)
}