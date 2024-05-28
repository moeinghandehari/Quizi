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
import quizi.composeapp.generated.resources.close_24px

@Composable
internal fun WrongButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(Res.drawable.close_24px),
            contentDescription = "False",
            tint = Color.White,
            modifier = Modifier
                .padding(10.dp)
                .background(Color.Red),
        )
    }
}

@Preview
@Composable
private fun TrueFalseButtonPreview() {
    MaterialTheme {
        WrongButton(onClick = { /*TODO*/ })
    }
}