package de.tuhh.quizi.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import de.tuhh.quizi.components.TrueFalseQuizView

@Composable
internal fun QuizScreen(
    // questions: List<Question>,
) {
    Scaffold(
        topBar = {
            // TopBar()
        },
    ) {
        TrueFalseQuizView(
            "Berlin is capital of germany",
            isTrue = true,
            {},
        )
    }
}