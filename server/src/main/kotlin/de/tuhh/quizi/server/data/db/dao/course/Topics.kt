package de.tuhh.quizi.server.data.db.dao.course

import de.tuhh.quizi.server.data.model.Topic
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

private const val NAME_LENGTH = 255

object Topics : IntIdTable() {
    val name = varchar("name", NAME_LENGTH)
    val courseId = reference("course_id", Courses)
}

class TopicEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TopicEntity>(Topics)

    var name by Topics.name
    var courseId by CourseEntity referencedOn Topics.courseId
}

internal fun TopicEntity.toModel(): Topic = Topic(
    id = id.value,
    name = name,
    courseId = courseId.id.value,
    questions = emptyList(), // TODO
)