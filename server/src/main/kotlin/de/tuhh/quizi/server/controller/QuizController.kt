package de.tuhh.quizi.server.controller

import de.tuhh.quizi.server.data.model.AbstractTopic
import de.tuhh.quizi.server.data.model.Course
import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.Topic
import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.Option

interface QuizController {
    suspend fun addCourse(courseName: String): Course
    suspend fun getCourseById(courseId: Int): Course?
    suspend fun getAllCourses(): List<Course>
    suspend fun addTopic(courseId: Int, topicName: String): Topic?
    suspend fun getAllTopicsByCourseId(courseId: Int): List<AbstractTopic>
    suspend fun getTopicById(topicId: Int): Topic?
    suspend fun addTrueFalseQuestion(
        topicId: Int,
        description: Description,
        answer: Int,
        hint: Hint?,
    ): Question

    suspend fun addSingleChoiceQuestion(
        topicId: Int,
        description: Description,
        options: List<Option>,
        answer: Int,
        hint: Hint?,
    ): Question

    suspend fun addMultipleChoiceQuestion(
        topicId: Int,
        description: Description,
        options: List<Option>,
        answer: Int,
        hint: Hint?,
    ): Question
}