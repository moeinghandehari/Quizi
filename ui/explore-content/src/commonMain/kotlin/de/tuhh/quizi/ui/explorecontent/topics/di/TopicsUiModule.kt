package de.tuhh.quizi.ui.explorecontent.topics.di

import de.tuhh.quizi.functionality.explore.content.usecases.di.QuizFunctionalityModule
import de.tuhh.quizi.ui.explorecontent.topics.state.TopicsViewModel
import org.koin.dsl.module

val TopicsUiModule = module {
    factory<TopicsViewModel> { params ->
        TopicsViewModel(
            courseId = params.get(),
            courseTitle = params.get(),
            getTopicsUseCase = get(),
            addTopicUseCase = get(),
        )
    }

    includes(QuizFunctionalityModule)
}