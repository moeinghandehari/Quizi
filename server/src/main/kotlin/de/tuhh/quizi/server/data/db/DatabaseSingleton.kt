package de.tuhh.quizi.server.data.db

import de.tuhh.quizi.server.data.db.dao.course.Courses
import de.tuhh.quizi.server.data.db.dao.question.Questions
import de.tuhh.quizi.server.data.db.dao.course.Topics
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseSingleton {
    private const val JDBC_URL = "jdbc:h2:file:./build/db"
    private const val DRIVER_CLASS_NAME = "org.h2.Driver"

    fun init() {
        val database = Database.connect(
            url = JDBC_URL,
            user = "root",
            driver = DRIVER_CLASS_NAME,
            password = ""
        )
        transaction(database) {
            SchemaUtils.create(Courses, Topics, Questions)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}
