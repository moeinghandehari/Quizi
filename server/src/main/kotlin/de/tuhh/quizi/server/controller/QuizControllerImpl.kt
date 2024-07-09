package de.tuhh.quizi.server.controller

import de.tuhh.quizi.server.data.model.AbstractTopic
import de.tuhh.quizi.server.data.model.Course
import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.QuestionType
import de.tuhh.quizi.server.data.model.Topic
import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.Option
import de.tuhh.quizi.server.data.repository.QuizRepository

class QuizControllerImpl(private val quizRepository: QuizRepository) : QuizController {
    override suspend fun addCourse(courseName: String): Course {
        return quizRepository.addCourse(courseName)
    }

    override suspend fun getCourseById(courseId: Int): Course? =
        quizRepository.getCourseById(courseId)

    override suspend fun getAllCourses(): List<Course> =
        quizRepository.getAllCourses()

    //    suspend fun updateCourse(course: Course): Course {
//        return quizRepository.updateCourse(course)
//    }
//
//    suspend fun deleteCourse(courseId: Int): Boolean {
//        return quizRepository.deleteCourse(courseId)
//    }
//
    override suspend fun addTopic(courseId: Int, topicName: String): Topic? =
        quizRepository.addTopic(courseId, topicName)

    override suspend fun getTopicById(topicId: Int): Topic? =
        quizRepository.getTopicById(topicId)

    override suspend fun getAllTopicsByCourseId(courseId: Int): List<AbstractTopic> {
        return quizRepository.getAllTopicsByCourseId(courseId)
    }

    //    suspend fun updateTopic(topicId: Int, topic: Topic): Topic {
//        return quizRepository.updateTopic(topicId, topic)
//    }
//
//    suspend fun deleteCourseTopic(topicId: Int): Boolean {
//        return quizRepository.deleteCourseTopic(topicId)
//    }
//
    override suspend fun addQuestion(
        topicId: Int,
        questionType: QuestionType,
        description: Description,
        options: List<Option>,
        hint: Hint?,
    ): Question = quizRepository.addQuestion(topicId, questionType, description, options, hint)
//
//    suspend fun getQuestionById(questionId: Int): Question? {
//        return quizRepository.getQuestionById(questionId)
//    }
//
//    suspend fun getAllQuestionsByCourseId(courseId: Int): List<Question> {
//        return quizRepository.getAllQuestionsByCourseId(courseId)
//    }
//
//    suspend fun updateQuestion(questionId: Int, question: Question): Question {
//        return quizRepository.updateQuestion(questionId, question)
//    }
//
//    suspend fun deleteQuestion(questionId: Int): Boolean {
//        return quizRepository.deleteQuestion(questionId)
//    }
}