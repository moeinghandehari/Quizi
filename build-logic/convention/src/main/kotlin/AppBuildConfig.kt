import models.FlavorDimension

object AppBuildConfig {
    const val versionName = "1"
    const val targetAndCompileSdk = 34
    const val minSdk = 24
    const val jvmToolchain = 17

    // The flavors the app supports (e.g. for api environments). If none are needed, leave it empty.
    val flavorDimensions = setOf<FlavorDimension<*>>(
        BrandFlavorDimension,
    )

    val jsonPlaceholderApiBuildConfig = JsonPlaceholderApiBuildConfig(baseUrl = "https://jsonplaceholder.typicode.com/")

    val quiziApiBuildConfig = QuiziApiBuildConfig(
        baseUrlProd = "",
        baseUrlDev = "",
    )

    /**
     * Definition of the "brand" flavor.
     */
    object BrandFlavorDimension : FlavorDimension<BrandFlavor> {

        override val name = "brand"

        override val productFlavors = setOf(
            BrandFlavor(
                name = "quizi",
                aggregateTestCoverage = true,
                applicationId = "de.tuhh.quizi",
                translationSheet = "Localizations-QUIZI",
            ),
        )
    }
}
