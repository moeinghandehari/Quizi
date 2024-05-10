package de.tuhh.quizi.server.data.repository

import de.tuhh.quizi.server.data.model.Course
import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.Topic

interface QuizRepository{
    suspend fun addCourse(courseName: String): Course
    suspend fun getCourseById(courseId: Int): Course?
    suspend fun getAllCourses(): List<String>
    suspend fun updateCourse(course: Course): Course
    suspend fun deleteCourse(courseId: Int): Boolean

    suspend fun addTopic(courseId: Int, topic: Topic): Int
    suspend fun getTopicById(topicId: Int): Topic?
    suspend fun getAllTopicsByCourseId(courseId: Int): List<Topic>
    suspend fun updateTopic(topicId: Int, topic: Topic): Topic
    suspend fun deleteCourseTopic(topicId: Int): Boolean

    suspend fun addQuestion(courseId: Int, topicId: Int, question: Question): Int
    suspend fun getQuestionById(questionId: Int): Question?
    suspend fun getAllQuestionsByCourseId(courseId: Int): List<Question>
    suspend fun updateQuestion(questionId: Int, question: Question): Question
    suspend fun deleteQuestion(questionId: Int): Boolean
}