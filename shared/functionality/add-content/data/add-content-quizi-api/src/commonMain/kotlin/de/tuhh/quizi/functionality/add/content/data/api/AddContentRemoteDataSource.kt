package de.tuhh.quizi.functionality.add.content.data.api

import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.functionality.add.content.entities.NewCourse
import kotlinx.coroutines.flow.Flow

interface AddContentRemoteDataSource {
    fun addCourse(newCourse: NewCourse): Flow<LoadingEvent<Boolean>>
}