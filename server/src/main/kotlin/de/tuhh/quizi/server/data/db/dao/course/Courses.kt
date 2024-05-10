package de.tuhh.quizi.server.data.db.dao.course

import de.tuhh.quizi.server.data.model.Course
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Courses : IntIdTable() {
    val name = varchar("name", 255)
}

class CourseEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CourseEntity>(Courses)

    var name by Courses.name
}

internal fun CourseEntity.toModel(): Course = Course(
    id = id.value,
    name = name,
    topics = emptyList() // TODO
)