package de.tuhh.quizi.navigation

import kotlinx.coroutines.flow.Flow

internal interface NavEventProvider {
    val navigationEvent: Flow<NavEvent>
}