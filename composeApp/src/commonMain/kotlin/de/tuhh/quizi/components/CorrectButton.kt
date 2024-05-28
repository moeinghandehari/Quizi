package de.tuhh.quizi.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import quizi.composeapp.generated.resources.Res
import quizi.composeapp.generated.resources.check_24px

@Composable
internal fun CorrectButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(Res.drawable.check_24px),
            contentDescription = "True",
            tint = Color.White,
            modifier = Modifier
                .padding(10.dp)
                .background(Color.Green),
        )
    }
}

@Preview
@Composable
private fun TrueFalseButtonPreview() {
    MaterialTheme {
        CorrectButton(onClick = { /*TODO*/ })
    }
}