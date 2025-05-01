package de.tuhh.quizi.ui.addcontent.shared.model

import de.tuhh.quizi.ui.core.state.ButtonOption
import org.jetbrains.compose.resources.StringResource

data class SelectionPageModel(
    val title: StringResource,
    val screenButtons: List<ButtonOption>,
)