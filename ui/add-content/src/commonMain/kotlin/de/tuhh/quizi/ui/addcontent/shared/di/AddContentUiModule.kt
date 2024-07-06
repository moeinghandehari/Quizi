package de.tuhh.quizi.ui.addcontent.shared.di

import de.tuhh.quizi.functionality.add.content.usecases.di.AddContentFunctionalityModule
import de.tuhh.quizi.ui.addcontent.shared.state.AddContentSharedViewModel
import org.koin.dsl.module

val AddContentUiModule = module {
/*    single<AddContentSharedViewModel> {
        AddContentSharedViewModel(get<SavedStateHandle>(), get(), get())
    }*/

    single<AddContentSharedViewModel> {
        AddContentSharedViewModel(get(), get())
    }

    includes(AddContentFunctionalityModule)
}