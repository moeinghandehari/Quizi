package de.tuhh.quizi.ui.core.extensions

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

val WindowInsets.Companion.None: WindowInsets
    @Composable get() = WindowInsets(0.dp)