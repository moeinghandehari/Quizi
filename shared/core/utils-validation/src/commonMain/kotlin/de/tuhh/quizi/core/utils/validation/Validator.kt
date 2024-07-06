package de.tuhh.quizi.core.utils.validation

private const val COURSE_NAME_LENGTH_SHORT = 3
private const val COURSE_NAME_LENGTH_LONG = 20

object Validator {
    private val specialCharacterRegex = Regex("[^a-zA-Z0-9 ]")

    fun isValidName(courseName: String) =
        courseName.matches(specialCharacterRegex) &&
                courseName.length in COURSE_NAME_LENGTH_SHORT..COURSE_NAME_LENGTH_LONG
}
