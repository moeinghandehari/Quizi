package de.tuhh.quizi.theme.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.theme.AppTheme

/**
 * This defines the text style pallet used in the App. New styles must be registered here.
 */
data class AppTypography(
    val template: TemplateTypography,
    val title1: Title1,
    val title2: Title2,
    val headline: Headline,
    val dialog: Dialog,
    val overline: TextStyle,
    val body: Body,
    val paragraph: Paragraph,
    val button: TextStyle,
    val caption: TextStyle,
    val tabItem: TextStyle,
) {
    data class Title1(
        val regular: TextStyle,
        val emphasized: TextStyle,
    )

    data class Title2(
        val regular: TextStyle,
        val medium: TextStyle,
        val emphasized: TextStyle,
        val inputSpacing: TextStyle,
    )

    data class Headline(
        val regular: TextStyle,
        val emphasized: TextStyle,
    )

    data class Dialog(
        val question: TextStyle,
    )

    data class Body(
        val regular: TextStyle,
        val medium: TextStyle,
        val emphasized: TextStyle,
    )

    data class Paragraph(
        val regular: TextStyle,
    )
}

/**
 * This class reflects the typography pallet used in the template for demo purposes.
 * The template styles are wrapped in this class for easy removal once all components are migrated to used the real
 * typography from [AppTypography].
 */
data class TemplateTypography(
    val title: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val label: TextStyle,
)

@Composable
fun AppTypographyOverview(
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(24.dp),
) {
    Column {
        Text(
            text = "Title1Regular",
            style = AppTheme.typography.title1.regular,
            color = AppTheme.colors.template.onBackground,
        )
        Text(
            text = "Title1Emphasized",
            style = AppTheme.typography.title1.emphasized,
            color = AppTheme.colors.template.onBackground,
        )
    }
    Column {
        Text(
            text = "Title2Emphazised",
            style = AppTheme.typography.title2.emphasized,
            color = AppTheme.colors.template.onBackground,
        )
        Text(
            text = "Title2Medium",
            style = AppTheme.typography.title2.medium,
            color = AppTheme.colors.template.onBackground,
        )
        Text(
            text = "Title2Regular",
            style = AppTheme.typography.title2.regular,
            color = AppTheme.colors.template.onBackground,
        )
        Text(
            text = "Title2InputSpacing",
            style = AppTheme.typography.title2.inputSpacing,
            color = AppTheme.colors.template.onBackground,
        )
    }
    Column {
        Text(
            text = "HeadlineRegularText",
            style = AppTheme.typography.headline.regular,
            color = AppTheme.colors.template.onBackground,
        )
        Text(
            text = "HeadlineEmphasizedText",
            style = AppTheme.typography.headline.emphasized,
            color = AppTheme.colors.template.onBackground,
        )
    }
    Column {
        Text(
            text = "DialogQuestion",
            style = AppTheme.typography.dialog.question,
            color = AppTheme.colors.template.onBackground,
        )
    }
    Text(
        text = "Overline",
        style = AppTheme.typography.overline,
        color = AppTheme.colors.template.onBackground,
    )
    Column {
        Text(
            text = "BodyRegular",
            style = AppTheme.typography.body.regular,
            color = AppTheme.colors.template.onBackground,
        )
        Text(
            text = "BodyMedium",
            style = AppTheme.typography.body.medium,
            color = AppTheme.colors.template.onBackground,
        )
        Text(
            text = "BodyEmphasized",
            style = AppTheme.typography.body.emphasized,
            color = AppTheme.colors.template.onBackground,
        )
    }
    Column {
        Text(
            text = "ParagraphRegular",
            style = AppTheme.typography.paragraph.regular,
            color = AppTheme.colors.template.onBackground,
        )
    }
    Text(
        text = "Button",
        style = AppTheme.typography.button,
        color = AppTheme.colors.template.onBackground,
    )
    Text(
        text = "Caption",
        style = AppTheme.typography.caption,
        color = AppTheme.colors.template.onBackground,
    )
}