package de.tuhh.quizi.ui.core.navigation.annotations

@RequiresOptIn(message = "This API is internal to Compose Destinations library. Do NOT use it!")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
annotation class InternalDestinationsApi
