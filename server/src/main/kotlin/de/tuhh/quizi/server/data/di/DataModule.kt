package de.tuhh.quizi.server.data.di

import de.tuhh.quizi.server.data.db.dao.course.CourseDao
import de.tuhh.quizi.server.data.db.dao.course.CourseDaoImpl
import de.tuhh.quizi.server.data.db.dao.question.QuestionDao
import de.tuhh.quizi.server.data.db.dao.question.QuestionDaoImpl
import de.tuhh.quizi.server.data.repository.QuizRepository
import de.tuhh.quizi.server.data.repository.QuizRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<QuizRepository> { QuizRepositoryImpl(get(), get()) }
    single<CourseDao> { CourseDaoImpl() }
    single<QuestionDao> { QuestionDaoImpl() }
}