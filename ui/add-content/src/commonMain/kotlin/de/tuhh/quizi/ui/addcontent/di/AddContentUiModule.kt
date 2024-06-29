package de.tuhh.quizi.ui.addcontent.di

import de.tuhh.quizi.ui.addcontent.state.AddContentViewModel
import org.koin.dsl.module

val AddContentUiModule = module {
    factory { ::AddContentViewModel }
}