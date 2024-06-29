package de.tuhh.quizi.di

import de.tuhh.quizi.navigation.AppNavigatorImpl
import de.tuhh.quizi.navigation.NavEventProvider
import de.tuhh.quizi.ui.addcontent.di.AddContentUiModule
import de.tuhh.quizi.ui.core.navigation.AppNavigator
import de.tuhh.quizi.ui.home.di.HomeUiModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.binds
import org.koin.dsl.module

val appModule = module {

    singleOf(::AppNavigatorImpl) binds arrayOf(
        AppNavigator::class,
        NavEventProvider::class,
    )

    // UI feature provision
    includes(
        AddContentUiModule,
        HomeUiModule,
    )
}
