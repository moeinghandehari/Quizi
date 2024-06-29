package de.tuhh.quizi.ui.home.di

import de.tuhh.quizi.ui.home.state.HomeViewModel
import org.koin.dsl.module

val HomeUiModule = module {
    factory<HomeViewModel> {
        HomeViewModel(get())
    }
}