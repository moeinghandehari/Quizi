import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.tuhh.quizi.functionality.quiz.entities.types.Answer
import de.tuhh.quizi.functionality.quiz.entities.types.Option
import de.tuhh.quizi.functionality.quiz.entities.types.fromIndices
import de.tuhh.quizi.functionality.quiz.entities.types.selectedIndices
import de.tuhh.quizi.ui.core.components.card.BigTextCard
import de.tuhh.quizi.ui.core.theme.AppTheme

@Composable
fun SingleChoiceQuizView(
    question: String,
    options: List<Option>,
    selectedAnswer: Answer?,
    isCorrect: Boolean,
    onAnswer: (Answer) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(AppTheme.dimensions.padding.l),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space.m),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BigTextCard(text = question, modifier = Modifier.fillMaxWidth().weight(1f))

        options.forEachIndexed { index, option ->
            val isSelected = selectedAnswer?.selectedIndices?.contains(index) == true

            val borderModifier = when {
                selectedAnswer == null -> Modifier
                isSelected && isCorrect -> Modifier.border(
                    AppTheme.dimensions.space.xs,
                    AppTheme.colors.element.color.positive
                )

                isSelected && !isCorrect -> Modifier.border(
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
                    .clickable(enabled = selectedAnswer == null) {
                        onAnswer(Answer.fromIndices(listOf(index)))
                    }
            )
        }
    }
}
