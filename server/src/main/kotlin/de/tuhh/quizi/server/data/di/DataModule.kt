package de.tuhh.quizi.server.data.di

import de.tuhh.quizi.server.data.db.dao.course.CourseDao
import de.tuhh.quizi.server.data.db.dao.course.CourseDaoImpl
import de.tuhh.quizi.server.data.db.dao.question.QuestionDao
import de.tuhh.quizi.server.data.db.dao.question.QuestionDaoImpl
import de.tuhh.quizi.server.data.db.dao.quiz.QuizDao
import de.tuhh.quizi.server.data.db.dao.quiz.QuizDaoImpl
import de.tuhh.quizi.server.data.repository.ContentRepository
import de.tuhh.quizi.server.data.repository.ContentRepositoryImpl
import de.tuhh.quizi.server.data.repository.QuizRepository
import de.tuhh.quizi.server.data.repository.QuizRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<CourseDao> { CourseDaoImpl() }
    single<QuestionDao> { QuestionDaoImpl() }
    single<QuizDao> { QuizDaoImpl() }

    single<ContentRepository> { ContentRepositoryImpl(courseDao = get(), questionDao = get()) }
    single<QuizRepository> { QuizRepositoryImpl(quizDao = get()) }
}