package de.tuhh.quizi.server.data.db.dao.course

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Topics : IntIdTable() {
    val name = varchar("name", 255)
    val courseId = reference("course_id", Courses)
}

class TopicEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TopicEntity>(Topics)

    var name by Topics.name
    var courseId by CourseEntity referencedOn Topics.courseId
}