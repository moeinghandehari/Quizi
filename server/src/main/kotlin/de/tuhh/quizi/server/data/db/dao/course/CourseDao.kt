package de.tuhh.quizi.server.data.db.dao.course

import de.tuhh.quizi.server.data.model.Course

interface CourseDao {
    suspend fun addCourse(courseName: String): Course
    suspend fun getCourseById(courseId: Int): Course?
    suspend fun getAllCourses(): List<String>
    suspend fun updateCourse(course: Course): Course
    suspend fun deleteCourse(courseId: Int): Boolean
}