@file:Suppress("MagicNumber")
package de.tuhh.quizi.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.tuhh.quizi.theme.AppTheme
import kotlin.math.ceil
import kotlin.math.roundToInt

@Composable
@Preview(showBackground = true, heightDp = 650, widthDp = 1100)
private fun AppColorsPreview(
    @PreviewParameter(ThemePreviewProvider::class) category: ThemeColorCategory,
) {
    Column {
        Text(
            text = category.label,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 30.dp, top = 30.dp, bottom = 0.dp),
        )
        Row {
            THEME_SETTINGS.forEach { themeSetting ->

                Column(Modifier.padding(20.dp)) {
                    Text(
                        text = themeSetting.label,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                    )
                    AppTheme(
                        isDarkTheme = themeSetting.isDarkTheme,
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            category.modes.forEach {
                                PreviewColorCard(it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalStdlibApi::class)
@Composable
private fun PreviewColorCard(themeColor: ThemeColor) {
    Card(
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(Modifier.padding(12.dp)) {
            Spacer(
                Modifier
                    .height(80.dp)
                    .width(120.dp)
                    .background(
                        color = themeColor.color(),
                        shape = RoundedCornerShape(4.dp),
                    ),
            )
            Text(
                text = themeColor.label,
                style = AppTheme.typography.headline.regular,
                modifier = Modifier.padding(top = 10.dp),
            )
            val hex = Integer.toHexString(themeColor.color().toArgb())
            val percent = hex.let { (it.substring(0..1).hexToInt() / 255f) }
            val roundedPercent = percent.let { ceil((it * 100f)).roundToInt() }
            val printedPercent = if (roundedPercent < 100) {
                "| $roundedPercent%"
            } else {
                ""
            }
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "#${hex.uppercase().drop(2)} $printedPercent",
            )
        }
    }
}