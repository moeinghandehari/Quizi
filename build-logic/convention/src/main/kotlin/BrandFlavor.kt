import models.Flavor

data class BrandFlavor(
    override val name: String,
    override val aggregateTestCoverage: Boolean,
    val applicationId: String,
    val translationSheet: String,
) : Flavor