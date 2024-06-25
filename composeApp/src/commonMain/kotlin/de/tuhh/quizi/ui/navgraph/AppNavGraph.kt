package de.tuhh.quizi.ui.navgraph

//import de.tuhh.quizi.RootNavGraph
//import de.tuhh.quizi.ui.addcontent.AddContentNavGraph
//import de.tuhh.quizi.ui.home.HomeNavGraph
//
//internal object AppNavGraph : NavGraphSpec by NavGraph(
//    route = "app",
//    startRoute = HomeNavGraph,
//    destinations = listOf(),
//    nestedNavGraphs = listOf(
//        RootNavGraph(),
//        HomeNavGraph,
//        AddContentNavGraph(),
//    ),
//)
//
//internal data class NavGraph(
//    override val route: String,
//    override val startRoute: String,
//    val destinations: List<DestinationSpec<*>> = emptyList(),
//    override val nestedNavGraphs: List<NavGraphSpec> = emptyList(),
//) : NavGraphSpec {
//    override val destinationsByRoute: Map<String, DestinationSpec<*>> =
//        destinations.associateBy { it.route }
//}