package de.tuhh.quizi.server.data.db.dao.course

import de.tuhh.quizi.server.data.db.DatabaseSingleton.dbQuery
import de.tuhh.quizi.server.data.db.dao.question.Questions
import de.tuhh.quizi.server.data.model.Course
import de.tuhh.quizi.server.data.model.Topic
import de.tuhh.quizi.server.exceptionHandler.DbExceptionHandler
import org.jetbrains.exposed.sql.*

class CourseDaoImpl : CourseDao {

    private fun resultRowToCourse(row: ResultRow): Course {
        return Course(
            id = row[Courses.id].value,
            name = row[Courses.name],
            topics = emptyList() // TODO --------------
        )
    }

    override suspend fun addCourse(courseName: String): Course = dbQuery {
        val insertStatement = Courses.insert {
            it[name] = courseName
        }
        val addedCourse = insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToCourse)
        if (addedCourse == null) {
            throw DbExceptionHandler.InsertionException("Course could not be added")
        } else {
            return@dbQuery addedCourse
        }
    }

    override suspend fun getCourseById(courseId: Int): Course? = dbQuery {
        (Courses innerJoin (Topics innerJoin Questions))
            .selectAll()
            .where { Courses.id eq courseId }
            .groupBy(Courses.id, Topics.id, Questions.id)
            .map { row ->
                Course(
                    id = row[Courses.id].value,
                    name = row[Courses.name],
                    topics = listOf(
                        Topic(
                            id = row[Topics.id].value,
                            name = row[Topics.name],
                            questions = listOf(
                                // TODO
                            )
                        )
                    )
                )
            }
            .firstOrNull()
    }

    override suspend fun getAllCourses(): List<String> = dbQuery {
        Courses
            .select(Courses.name)
            .distinct()
            .map { row ->
                row[Courses.name]
            }
    }

    override suspend fun updateCourse(course: Course): Course {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCourse(courseId: Int): Boolean {
        TODO("Not yet implemented")
    }
}