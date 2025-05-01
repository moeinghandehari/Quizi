package de.tuhh.quizi.ui.core.components.preview

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

data class AppTopAppBarParam(
    val title: String?,
    val navigationIcon: @Composable () -> Unit,
    val actions: @Composable RowScope.() -> Unit,
)
