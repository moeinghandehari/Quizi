package de.tuhh.quizi.server.data.db.dao.course

import de.tuhh.quizi.server.data.model.Course
import de.tuhh.quizi.server.data.model.Topic

interface CourseDao {
    suspend fun addCourse(courseName: String): Course
    suspend fun getCourseById(courseId: Int): Course?
    suspend fun getAllCourses(): List<Course>
    suspend fun updateCourse(course: Course): Course
    suspend fun deleteCourse(courseId: Int): Boolean

    suspend fun addTopic(courseId: Int, topicName: String): Topic
    suspend fun getTopicById(topicId: Int): Topic?
    suspend fun getTopicsByCourseId(courseId: Int): List<Topic>
}