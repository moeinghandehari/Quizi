package de.tuhh.quizi.server.controller

import de.tuhh.quizi.server.data.model.Course
import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.QuestionType
import de.tuhh.quizi.server.data.model.Topic
import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.Option

interface QuizController {
    suspend fun addCourse(courseName: String): Course
    suspend fun getCourseById(courseId: Int): Course?
    suspend fun getAllCourses(): List<String>
    suspend fun addTopic(courseId: Int, topicName: String): Topic?
    suspend fun getTopicById(topicId: Int): Topic?
    suspend fun addQuestion(
        topicId: Int,
        questionType: QuestionType,
        description: Description,
        options: List<Option>,
        hint: Hint?,
    ): Question
}