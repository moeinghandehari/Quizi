package de.tuhh.quizi.di

import de.tuhh.quizi.core.buildinfo.BuildInfo
import de.tuhh.quizi.core.quizi.api.model.QuiziApiConfig
import de.tuhh.quizi.functionality.add.content.data.implementations.di.AddContentDataModule
import de.tuhh.quizi.ui.addcontent.courses.di.CoursesUiModule
import de.tuhh.quizi.ui.addcontent.shared.di.AddContentUiModule
import de.tuhh.quizi.ui.addcontent.topics.di.TopicsUiModule
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
            baseUrl = "131.189.200.54",
            // Set your local ip as host
        )
    }

    // Data layer provision
    includes(
        AddContentDataModule
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
