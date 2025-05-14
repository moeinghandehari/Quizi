package de.tuhh.quizi.server.data.repository

import de.tuhh.quizi.server.data.db.dao.course.CourseDao
import de.tuhh.quizi.server.data.db.dao.question.QuestionDao
import de.tuhh.quizi.server.data.model.AbstractTopic
import de.tuhh.quizi.server.data.model.Course
import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.Topic
import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.Option

@Suppress("TooManyFunctions")
class ContentRepositoryImpl(
    private val courseDao: CourseDao,
    private val questionDao: QuestionDao,
) : ContentRepository {
    override suspend fun addCourse(courseName: String) = courseDao.addCourse(courseName)

    override suspend fun getCourseById(courseId: Int): Course? =
        courseDao.getCourseById(courseId)

    override suspend fun getAllCourses(): List<Course> = courseDao.getAllCourses()

    override suspend fun updateCourse(course: Course): Course {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCourse(courseId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun addTopic(courseId: Int, topicName: String): Topic =
        courseDao.addTopic(courseId, topicName)

    override suspend fun getTopicById(topicId: Int): Topic? =
        courseDao.getTopicById(topicId)

    override suspend fun getAllTopicsByCourseId(courseId: Int): List<AbstractTopic> =
        courseDao.getTopicsByCourseId(courseId)

    override suspend fun updateTopic(topicId: Int, topic: Topic): Topic {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCourseTopic(topicId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun addTrueFalseQuestion(
        topicId: Int,
        description: Description,
        answer: Int,
        hint: Hint?,
    ): Question = questionDao.addTrueFalseQuestion(topicId, description, answer, hint)

    override suspend fun addSingleChoiceQuestion(
        topicId: Int,
        description: Description,
        options: List<Option>,
        answer: Int,
        hint: Hint?,
    ): Question = questionDao.addSingleChoiceQuestion(topicId, description, options, answer, hint)

    override suspend fun addMultipleChoiceQuestion(
        topicId: Int,
        description: Description,
        options: List<Option>,
        answer: Int,
        hint: Hint?,
    ): Question = questionDao.addMultipleChoiceQuestion(topicId, description, options, answer, hint)

    override suspend fun getQuestionById(questionId: Int): Question? {
        TODO("Not yet implemented")
    }

    override suspend fun getAllQuestionsByCourseId(courseId: Int): List<Question> {
        TODO("Not yet implemented")
    }

    override suspend fun updateQuestion(questionId: Int, question: Question): Question {
        TODO("Not yet implemented")
    }

    override suspend fun deleteQuestion(questionId: Int): Boolean {
        TODO("Not yet implemented")
    }
}