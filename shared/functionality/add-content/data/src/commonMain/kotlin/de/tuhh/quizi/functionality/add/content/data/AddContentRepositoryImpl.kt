package de.tuhh.quizi.functionality.add.content.data

import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.functionality.add.content.abstractions.AddContentRepository
import de.tuhh.quizi.functionality.add.content.entities.Course
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class AddContentRepositoryImpl : AddContentRepository {

    override fun addCourse(newCourse: Course): Flow<LoadingEvent<Unit>> = flowOf() // TODO
}