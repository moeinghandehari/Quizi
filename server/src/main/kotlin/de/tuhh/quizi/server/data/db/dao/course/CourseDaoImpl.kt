package de.tuhh.quizi.server.data.db.dao.course

import de.tuhh.quizi.server.data.db.DatabaseSingleton.dbQuery
import de.tuhh.quizi.server.data.db.dao.question.Questions
import de.tuhh.quizi.server.data.model.Course
import de.tuhh.quizi.server.data.model.MultipleChoiceQuestion
import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.SingleChoiceQuestion
import de.tuhh.quizi.server.data.model.Topic
import de.tuhh.quizi.server.data.model.TrueFalseQuestion
import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.deserializeOptions
import de.tuhh.quizi.server.exceptionHandler.DbExceptionHandler
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

@Suppress("TooManyFunctions")
class CourseDaoImpl : CourseDao {

    private fun resultRowToCourse(row: ResultRow): Course {
        return Course(
            id = row[Courses.id].value,
            name = row[Courses.name],
            topics = listOf(),
        )
    }

    private fun resultRowToQuestion(row: ResultRow): Question = when (row[Questions.type]) {
        0 -> MultipleChoiceQuestion(
            id = row[Questions.id].value,
            question = Description(row[Questions.question]),
            options = deserializeOptions(row[Questions.options]),
            hint = row[Questions.hint]?.let { Hint(it) },
            topicId = row[Questions.topicId].value,
        )

        1 -> SingleChoiceQuestion(
            id = row[Questions.id].value,
            question = Description(row[Questions.question]),
            options = deserializeOptions(row[Questions.options]),
            hint = row[Questions.hint]?.let { Hint(it) },
            topicId = row[Questions.topicId].value,
        )

        2 -> TrueFalseQuestion(
            id = row[Questions.id].value,
            question = Description(row[Questions.question]),
            options = deserializeOptions(row[Questions.options]),
            hint = row[Questions.hint]?.let { Hint(it) },
            topicId = row[Questions.topicId].value,
        )

        else -> throw IllegalArgumentException("Unknown question type")
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
        val course = Courses
            .selectAll()
            .where { Courses.id eq courseId }
            .mapNotNull { row -> resultRowToCourse(row) }
            .singleOrNull()

        course?.let {
            val topics = Topics
                .selectAll()
                .where { Topics.courseId eq courseId }
                .map { topicRow ->
                    val topicId = topicRow[Topics.id].value
                    val questions = Questions
                        .selectAll()
                        .where { Questions.topicId eq topicId }
                        .map { questionRow -> resultRowToQuestion(questionRow) }

                    Topic(
                        id = topicId,
                        name = topicRow[Topics.name],
                        courseId = topicRow[Topics.courseId].value,
                        questions = questions,
                    )
                }

            course.copy(topics = topics)
        }
    }

    override suspend fun getAllCourses(): List<Course> = dbQuery {
        Courses
            .selectAll()
            .distinct()
            .map { row ->
                Course(
                    id = row[Courses.id].value,
                    name = row[Courses.name],
                    topics = emptyList()
                )
            }
    }

    override suspend fun updateCourse(course: Course): Course {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCourse(courseId: Int): Boolean {
        TODO("Not yet implemented")
    }

    private fun resultRowToTopic(row: ResultRow): Topic {
        return Topic(
            id = row[Topics.id].value,
            name = row[Topics.name],
            courseId = row[Topics.courseId].value,
            questions = listOf(),
        )
    }

    override suspend fun addTopic(courseId: Int, topicName: String): Topic = dbQuery {
        val insertStatement = Topics.insert {
            it[this.courseId] = courseId
            it[name] = topicName
        }
        val addedTopic = insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToTopic)
        if (addedTopic == null) {
            throw DbExceptionHandler.InsertionException("Topic could not be added")
        } else {
            return@dbQuery addedTopic
        }
    }

    override suspend fun getTopicById(topicId: Int): Topic? = dbQuery {
        Topics
            .selectAll().where { Topics.id eq topicId }
            .map(::resultRowToTopic)
            .firstOrNull()
    }

    override suspend fun getTopicsByCourseId(courseId: Int): List<Topic> = dbQuery {
        Topics
            .selectAll().where { Topics.courseId eq courseId }
            .map(::resultRowToTopic)
    }
}