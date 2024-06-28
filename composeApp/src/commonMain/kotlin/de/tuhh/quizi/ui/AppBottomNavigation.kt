package de.tuhh.quizi.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.union
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec
import de.tuhh.quizi.ui.core.navigation.spec.NavGraphSpec
import de.tuhh.quizi.ui.core.theme.AppTheme
import de.tuhh.quizi.ui.state.NavItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppBottomNavigation(
    items: List<NavItem>,
    currentDestination: DestinationSpec<*>?,
    onItemClick: (navGraph: NavGraphSpec) -> Unit,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = AppBottomNavigationDefaults.windowInsets,
) {
    Surface(
        modifier = modifier,
        shadowElevation = 8.dp,
        color = Color.Transparent,
    ) {
        NavigationBar(
            containerColor = AppTheme.colors.customeColor.tabbarBackgroundColor,
            contentColor = AppTheme.colors.customeColor.tabbarBackgroundColor,
            windowInsets = windowInsets,
        ) {
            items.fastForEach { item ->
                val isSelected = item.navGraph.destinationsByRoute.containsValue(
                    currentDestination,
                )
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onItemClick(item.navGraph) },
                    icon = {
                        Icon(
                            painter = painterResource(if (isSelected) item.selectedIcon else item.icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp),
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(item.name),
                            textAlign = TextAlign.Center,
                            style = AppTheme.typography.tabItem,
                            maxLines = 1,
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AppTheme.colors.background.vibrant.primary,
                        selectedTextColor = AppTheme.colors.element.grey.high,
                        indicatorColor = Color.Transparent,
                        unselectedIconColor = AppTheme.colors.element.grey.high,
                        unselectedTextColor = AppTheme.colors.element.grey.high,
                    ),
                )
            }
        }
    }
}

object AppBottomNavigationDefaults {
    val windowInsets: WindowInsets
        @Composable get() = NavigationBarDefaults.windowInsets
            .union(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
}