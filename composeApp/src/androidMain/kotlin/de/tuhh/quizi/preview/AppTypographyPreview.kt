package de.tuhh.quizi.preview

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.tuhh.quizi.theme.AppTheme
import de.tuhh.quizi.theme.model.AppTypographyOverview

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = false,
)
private fun AppTypographyOverviewPreview() {
    AppTheme {
        AppTypographyOverview()
    }
}