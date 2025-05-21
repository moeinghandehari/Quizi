package de.tuhh.quizi.ui.quiz.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.tuhh.quizi.functionality.quiz.entities.types.Option
import de.tuhh.quizi.ui.core.components.button.primary.PositiveButton
import de.tuhh.quizi.ui.core.components.card.BigTextCard
import de.tuhh.quizi.ui.core.theme.AppTheme

@Composable
fun MultipleChoiceQuizView(
    question: String,
    options: List<Option>,
    selectedIndices: List<Int>,
    isAnswered: Boolean,
    isCorrect: Boolean,
    onSelect: (Int) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(AppTheme.dimensions.padding.l),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space.m),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BigTextCard(text = question, modifier = Modifier.fillMaxWidth())

        options.forEachIndexed { index, option ->
            val isSelected = index in selectedIndices
            val borderModifier = when {
                !isAnswered && isSelected -> Modifier.border(
                    AppTheme.dimensions.space.xs,
                    AppTheme.colors.element.color.primary
                )

                isAnswered && isSelected && isCorrect -> Modifier.border(
                    AppTheme.dimensions.space.xs,
                    AppTheme.colors.element.color.positive
                )

                isAnswered && isSelected && !isCorrect -> Modifier.border(
                    AppTheme.dimensions.space.xs,
                    AppTheme.colors.element.color.error
                )

                else -> Modifier
            }

            BigTextCard(
                text = option.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .then(borderModifier)
                    .clickable(enabled = !isAnswered) {
                        onSelect(index)
                    }
            )
        }

        if (!isAnswered) {
            PositiveButton(
                label = "Submit",
                onClick = onSubmit,
                modifier = Modifier.padding(top = AppTheme.dimensions.padding.l)
            )
        }
    }
}
