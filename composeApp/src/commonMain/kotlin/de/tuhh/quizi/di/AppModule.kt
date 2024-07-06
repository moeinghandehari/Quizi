package de.tuhh.quizi.di

import de.tuhh.quizi.functionality.add.content.data.di.AddContentModule
import de.tuhh.quizi.navigation.AppNavigatorImpl
import de.tuhh.quizi.navigation.NavEventProvider
import de.tuhh.quizi.ui.addcontent.shared.di.AddContentUiModule
import de.tuhh.quizi.ui.core.navigation.AppNavigator
import de.tuhh.quizi.ui.home.di.HomeUiModule
import de.tuhh.quizi.ui.quiz.di.QuizUiModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.binds
import org.koin.dsl.module

val appModule = module {

    singleOf(::AppNavigatorImpl) binds arrayOf(
        AppNavigator::class,
        NavEventProvider::class,
    )

    // Data layer provision
    includes(
        AddContentModule
    )

    // UI feature provision
    includes(
        AddContentUiModule,
        HomeUiModule,
        QuizUiModule,
    )
}
