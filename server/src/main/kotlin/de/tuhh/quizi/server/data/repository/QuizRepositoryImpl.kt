package de.tuhh.quizi.server.data.repository

import de.tuhh.quizi.server.data.db.dao.course.CourseDao
import de.tuhh.quizi.server.data.db.dao.question.QuestionDao
import de.tuhh.quizi.server.data.model.Course
import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.QuestionType
import de.tuhh.quizi.server.data.model.Topic
import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.Option

@Suppress("TooManyFunctions")
class QuizRepositoryImpl(
    private val courseDao: CourseDao,
    private val questionDao: QuestionDao,
) : QuizRepository {
    override suspend fun addCourse(courseName: String) = courseDao.addCourse(courseName)

    override suspend fun getCourseById(courseId: Int): Course? =
        courseDao.getCourseById(courseId)

    override suspend fun getAllCourses(): List<String> = courseDao.getAllCourses()

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

    override suspend fun getAllTopicsByCourseId(courseId: Int): List<Topic> {
        TODO("Not yet implemented")
    }

    override suspend fun updateTopic(topicId: Int, topic: Topic): Topic {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCourseTopic(topicId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun addQuestion(
        topicId: Int,
        questionType: QuestionType,
        description: Description,
        options: List<Option>,
        hint: Hint?,
    ): Question = questionDao.addQuestion(topicId, questionType, description, options, hint)

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