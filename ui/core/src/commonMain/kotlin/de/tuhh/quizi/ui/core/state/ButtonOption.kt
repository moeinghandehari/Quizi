package de.tuhh.quizi.ui.core.state

import org.jetbrains.compose.resources.StringResource

data class ButtonOption(val option: Pair<StringResource, () -> Unit>)