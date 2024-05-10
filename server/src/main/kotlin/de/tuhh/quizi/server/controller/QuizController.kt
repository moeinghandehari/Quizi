package de.tuhh.quizi.server.controller

import de.tuhh.quizi.server.data.model.Course
import de.tuhh.quizi.server.data.repository.QuizRepository
import de.tuhh.quizi.server.data.repository.QuizRepositoryImpl

class QuizController (
    private val quizRepository: QuizRepository = QuizRepositoryImpl()
) {
    suspend fun addCourse(courseName: String): Course {
        return quizRepository.addCourse(courseName)
    }

    suspend fun getCourseById(courseId: Int): Course? {
        return quizRepository.getCourseById(courseId)
    }

    suspend fun getAllCourses(): List<String> {
        return quizRepository.getAllCourses()
    }

//    suspend fun updateCourse(course: Course): Course {
//        return quizRepository.updateCourse(course)
//    }
//
//    suspend fun deleteCourse(courseId: Int): Boolean {
//        return quizRepository.deleteCourse(courseId)
//    }
//
//    suspend fun addTopic(courseId: Int, topic: Topic): Int {
//        return quizRepository.addTopic(courseId, topic)
//    }
//
//    suspend fun getTopicById(topicId: Int): Topic? {
//        return quizRepository.getTopicById(topicId)
//    }
//
//    suspend fun getAllTopicsByCourseId(courseId: Int): List<Topic> {
//        return quizRepository.getAllTopicsByCourseId(courseId)
//    }
//
//    suspend fun updateTopic(topicId: Int, topic: Topic): Topic {
//        return quizRepository.updateTopic(topicId, topic)
//    }
//
//    suspend fun deleteCourseTopic(topicId: Int): Boolean {
//        return quizRepository.deleteCourseTopic(topicId)
//    }
//
//    suspend fun addQuestion(courseId: Int, topicId: Int, question: Question): Int {
//        return quizRepository.addQuestion(courseId, topicId, question)
//    }
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