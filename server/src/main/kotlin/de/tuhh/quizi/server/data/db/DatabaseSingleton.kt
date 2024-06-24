package de.tuhh.quizi.server.data.db

import de.tuhh.quizi.server.data.db.dao.course.Courses
import de.tuhh.quizi.server.data.db.dao.course.Topics
import de.tuhh.quizi.server.data.db.dao.question.Questions
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseSingleton {
    private const val JDBC_URL = "jdbc:postgresql://localhost:5432/"
    private const val DRIVER_CLASS_NAME = "org.postgresql.Driver"

    fun init() {
        val database = Database.connect(
            url = JDBC_URL,
            user = "postgres",
            driver = DRIVER_CLASS_NAME,
            password = "quizi",
        )
        transaction(database) {
            SchemaUtils.create(Courses, Topics, Questions)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}
