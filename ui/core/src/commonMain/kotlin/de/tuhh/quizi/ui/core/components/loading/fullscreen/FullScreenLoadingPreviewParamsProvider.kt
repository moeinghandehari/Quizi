package de.tuhh.quizi.ui.core.components.loading.fullscreen

import de.tuhh.quizi.ui.core.components.loading.fullscreen.FullScreenLoadingPreviewParamsProvider.FullScreenLoadingParams
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

internal class FullScreenLoadingPreviewParamsProvider :
    PreviewParameterProvider<FullScreenLoadingParams> {

    override val values: Sequence<FullScreenLoadingParams> = sequenceOf(
//        FullScreenLoadingParams.RegistrationFullLoading,
//        FullScreenLoadingParams.LoginFullLoading,
    )

    internal sealed class FullScreenLoadingParams(val titleResId: StringResource) {
//        data object RegistrationFullLoading : FullScreenLoadingParams(
//            titleResId = Res.string.registration_loading_description,
//        )
//
//        data object LoginFullLoading : FullScreenLoadingParams(
//            titleResId = Res.string.login_loading_description,
//        )
    }
}
