package de.tuhh.quizi.ui.core.components.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.components.chip.ChipDefaults.defaultColors
import de.tuhh.quizi.ui.core.components.chip.ChipDefaults.material3ChipHorizonalPadding
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import quizi.ui.core.generated.resources.Res
import quizi.ui.core.generated.resources.ic_add
import quizi.ui.core.generated.resources.ic_close

@Composable
fun QuiziChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: SelectableChipColors = defaultColors,
    isEnabled: Boolean = true,
    leadingIconRes: DrawableResource? = null,
    trailingIconRes: DrawableResource? = null,
) {
    FilterChip(
        modifier = modifier,
        selected = isSelected,
        label = {
            Text(
                modifier = Modifier
                    .padding(
                        vertical = AppTheme.dimensions.padding.m,
                    )
                    .padding(
                        horizontal = if (leadingIconRes != null) {
                            0.dp
                        } else {
                            AppTheme.dimensions.padding.xl - (material3ChipHorizonalPadding * 2)
                        },
                    ),
                text = text,
                style = AppTheme.typography.button,
            )
        },
        enabled = isEnabled,
        colors = colors,
        shape = AppTheme.shapes.m,
        border = null,
        leadingIcon = if (leadingIconRes != null) {
            {
                Icon(
                    modifier = Modifier.padding(
                        start = AppTheme.dimensions.padding.xl - material3ChipHorizonalPadding,
                    ),
                    painter = painterResource(resource = leadingIconRes),
                    contentDescription = null,
                )
            }
        } else {
            null
        },
        trailingIcon = if (trailingIconRes != null) {
            {
                Icon(
                    modifier = Modifier.padding(
                        end = AppTheme.dimensions.padding.xl - material3ChipHorizonalPadding,
                    ),
                    painter = painterResource(resource = trailingIconRes),
                    contentDescription = null,
                )
            }
        } else {
            null
        },
        onClick = onClick,
    )
}

private object ChipDefaults {
    val material3ChipHorizonalPadding = 8.dp

    val defaultColors
        @Composable get() = FilterChipDefaults.filterChipColors(
            selectedContainerColor = AppTheme.colors.background.vibrant.muted,
            selectedLabelColor = AppTheme.colors.permanent.white.high,
            containerColor = AppTheme.colors.background.accent.primary,
            labelColor = AppTheme.colors.element.color.muted,
            iconColor = AppTheme.colors.element.color.muted,
            selectedLeadingIconColor = AppTheme.colors.permanent.white.high,
            selectedTrailingIconColor = AppTheme.colors.permanent.white.high,
            disabledContainerColor = AppTheme.colors.background.accent.primary,
            disabledLabelColor = AppTheme.colors.permanent.white.high,
            disabledLeadingIconColor = AppTheme.colors.permanent.white.high,
            disabledTrailingIconColor = AppTheme.colors.permanent.white.high,
            disabledSelectedContainerColor = AppTheme.colors.background.vibrant.muted,
        )
}

@Preview
@Composable
private fun ChipPreview() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                QuiziChip(
                    text = "Hund",
                    isSelected = true,
                    onClick = {},
                )
                QuiziChip(
                    text = "Katze",
                    isSelected = false,
                    onClick = {},
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                QuiziChip(
                    text = "Add",
                    isSelected = false,
                    leadingIconRes = Res.drawable.ic_add,
                    onClick = {},
                )
                QuiziChip(
                    text = "Option",
                    isSelected = true,
                    trailingIconRes = Res.drawable.ic_close,
                    onClick = {},
                )
            }
        }
    }
}
