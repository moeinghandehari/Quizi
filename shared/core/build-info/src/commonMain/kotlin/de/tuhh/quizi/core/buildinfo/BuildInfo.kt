package de.tuhh.quizi.core.buildinfo

data class BuildInfo(
    val appVersionName: String,
    val appVersionCode: String,
    val isDebuggable: Boolean,
)