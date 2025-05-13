@file:Suppress("MagicNumber", "UnusedParameter") // TODO
package de.tuhh.quizi.ui.quiz.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.tuhh.quizi.functionality.quiz.entities.types.Answer
import de.tuhh.quizi.ui.core.components.button.primary.DestructiveButton
import de.tuhh.quizi.ui.core.components.button.primary.PositiveButton
import de.tuhh.quizi.ui.core.components.card.BigTextCard
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TrueFalseQuizView(
    question: String,
    selectedAnswer: Answer?,
    isCorrect: Boolean,
    onClick: (Answer) -> Unit,
    modifier: Modifier = Modifier,
) {
    val borderModifier = when {
        selectedAnswer == null -> Modifier
        isCorrect -> Modifier.border(
            AppTheme.dimensions.space.xs,
            AppTheme.colors.element.color.positive
        )

        else -> Modifier.border(AppTheme.dimensions.space.xs, AppTheme.colors.element.color.error)
    }

    Column(
        modifier = modifier.then(borderModifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        BigTextCard(
            text = question,
            modifier = Modifier
                .padding(bottom = AppTheme.dimensions.padding.xxl)
                .weight(1f)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier.padding(
                horizontal = AppTheme.dimensions.padding.l,
                vertical = AppTheme.dimensions.padding.l
            ),
        ) {
            DestructiveButton(
                label = "False",
                onClick = { onClick(Answer(0)) },
                modifier = Modifier.weight(10f),
                isEnabled = selectedAnswer == null
            )
            Spacer(modifier = Modifier.weight(1f))
            PositiveButton(
                label = "True",
                onClick = { onClick(Answer(1)) },
                modifier = Modifier.weight(10f),
                isEnabled = selectedAnswer == null
            )
        }
    }
}

@Preview
@Composable
private fun TrueFalseQuizViewPreview() {
    TrueFalseQuizView(
        question = "Berlin is capital of Germany",
        selectedAnswer = null,
        isCorrect = false,
        onClick = {},
    )
}