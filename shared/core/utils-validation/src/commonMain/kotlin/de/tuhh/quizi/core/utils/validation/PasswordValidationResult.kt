package de.tuhh.quizi.core.utils.validation

data class PasswordValidationResult(
    val hasEnoughCharacter: Boolean,
    val hasEnoughSpecialCharacter: Boolean,
    val hasEnoughCapitalCase: Boolean,
    val hasEnoughLowerCase: Boolean,
    val hasEnoughDigit: Boolean,
) {
    val isValid = hasEnoughCapitalCase &&
        hasEnoughLowerCase &&
        hasEnoughCharacter &&
        hasEnoughDigit &&
        hasEnoughSpecialCharacter
}