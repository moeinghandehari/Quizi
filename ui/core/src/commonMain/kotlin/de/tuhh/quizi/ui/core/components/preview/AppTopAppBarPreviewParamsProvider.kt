package de.tuhh.quizi.ui.core.components.preview

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import de.tuhh.quizi.ui.core.components.AppTopAppBarDefaults
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

class AppTopAppBarPreviewParamsProvider :
    PreviewParameterProvider<AppTopAppBarParam> {

    private val actions: @Composable RowScope.() -> Unit = {
        AppTopAppBarDefaults.CloseIconButton(
            onClick = {},
            isEnabled = false,
        )
        AppTopAppBarDefaults.CloseIconButton(
            onClick = {},
            isEnabled = true,
        )
    }

    override val values = sequenceOf(
        AppTopAppBarParam(
            title = PreviewText.short,
            navigationIcon = {},
            actions = {},
        ),
        AppTopAppBarParam(
            title = PreviewText.short,
            navigationIcon = { AppTopAppBarDefaults.UpIconButton(onClick = {}) },
            actions = {},
        ),
        AppTopAppBarParam(
            title = PreviewText.long,
            navigationIcon = { AppTopAppBarDefaults.CloseIconButton(onClick = {}) },
            actions = actions,
        ),
        AppTopAppBarParam(
            title = null,
            navigationIcon = { AppTopAppBarDefaults.CloseIconButton(onClick = {}) },
            actions = actions,
        ),
    )
}