package de.tuhh.quizi.ui.core.components.lottie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import de.tuhh.quizi.ui.core.theme.AppTheme
import io.github.alexzhirkevich.compottie.ExperimentalCompottieApi
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.dynamic.rememberLottieDynamicProperties
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import quizi.ui.core.generated.resources.Res

@OptIn(ExperimentalCompottieApi::class, ExperimentalResourceApi::class)
@Composable
fun LottieView(
    rawResPath: String,
    modifier: Modifier = Modifier,
    tintColor: Color = AppTheme.colors.background.accent.primary,
    isLooping: Boolean = true,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
) {
    if (LocalInspectionMode.current) {
        Spacer(
            modifier = modifier.background(tintColor),
        )
        return
    } else {
        val composition by rememberLottieComposition {
            LottieCompositionSpec.JsonString(
                Res.readBytes(rawResPath).decodeToString()
            )
        }
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = if (isLooping) Int.MAX_VALUE else 1,
        )

        val dynamicProperties = rememberLottieDynamicProperties {
            shapeLayer("Precomposition 1", "Shape Layer 4") {
                transform {
                    rotation { current -> current * progress }
                }
                fill("Group 1", "Fill 4") {
                    color { Color.Red }
                    // alpha { .5f } TODO
                }
                group("Group 4") {
                    ellipse("Ellipse 1") {
                        // configure size, position of the ellipse named "Ellipse 1"
                    }
                    stroke("Ellipse 1 Stroke") {
                        // configure stroke named "Ellipse 1 Stroke" in the same group
                    }
                }
            }
        }

        Image(
            painter = rememberLottiePainter(
                composition = composition,
                progress = { progress },
                dynamicProperties = dynamicProperties,
                enableMergePaths = true,
            ),
            contentDescription = "Lottie animation",
            contentScale = contentScale,
            alignment = alignment,
            modifier = modifier,
        )
    }
}