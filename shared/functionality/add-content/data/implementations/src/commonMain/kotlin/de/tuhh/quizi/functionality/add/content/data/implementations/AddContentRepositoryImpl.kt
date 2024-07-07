package de.tuhh.quizi.functionality.add.content.data.implementations

import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.functionality.add.content.abstractions.AddContentRepository
import de.tuhh.quizi.functionality.add.content.data.api.AddContentRemoteDataSource
import de.tuhh.quizi.functionality.add.content.entities.NewCourse
import kotlinx.coroutines.flow.Flow

internal class AddContentRepositoryImpl(
    private val addContentRemoteDataSource: AddContentRemoteDataSource,
) : AddContentRepository {

    override fun addCourse(newCourse: NewCourse): Flow<LoadingEvent<Boolean>> =
        addContentRemoteDataSource.addCourse(newCourse)
}