package de.tuhh.quizi.di

import de.tuhh.quizi.core.api.getBaseUrl
import de.tuhh.quizi.core.api.model.QuiziApiConfig
import de.tuhh.quizi.core.buildinfo.BuildInfo
import de.tuhh.quizi.functionality.explore.content.data.implementations.di.ExploreContentDataModule
import de.tuhh.quizi.functionality.quiz.data.implementations.di.QuizDataModule
import de.tuhh.quizi.ui.explorecontent.courses.di.CoursesUiModule
import de.tuhh.quizi.ui.explorecontent.shared.di.AddContentUiModule
import de.tuhh.quizi.ui.explorecontent.topics.di.TopicsUiModule
import de.tuhh.quizi.ui.home.di.HomeUiModule
import de.tuhh.quizi.ui.quiz.di.QuizUiModule
import org.koin.dsl.module

val appModule = module {

    factory<BuildInfo> {
        BuildInfo(
            appVersionName = "1.0.0",
            appVersionCode = "1",
            isDebuggable = true,
        )
    }

    factory<QuiziApiConfig> {
        QuiziApiConfig(
            baseUrl = getBaseUrl(),
        )
    }

    // Data layer provision
    includes(
        ExploreContentDataModule,
        QuizDataModule,
    )

    // UI feature provision
    includes(
        AddContentUiModule,
        CoursesUiModule,
        TopicsUiModule,
        HomeUiModule,
        QuizUiModule,
    )
}
