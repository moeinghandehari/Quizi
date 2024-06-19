@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.core.theme.tokens

import androidx.compose.ui.graphics.Color
import de.tuhh.quizi.ui.core.theme.model.AppColors

/**
 * This is the configuration of the light color tokens used in the App. Configure new colors here.
 */
internal val ColorsPermanent = AppColors.Permanent(
    white = AppColors.Permanent.White(
        high = Color(0xFFFFFFFF),
        medium = Color(0xCCFFFFFF),
        low = Color(0x99FFFFFF),
        disabled = Color(0x33FFFFFF),
    ),
    black = AppColors.Permanent.Black(
        high = Color(0xE5121415),
        medium = Color(0xCC121415),
        low = Color(0x99121415),
        accent = Color(0x4D121415),
        highlight = Color(0x26121415),
    ),
)