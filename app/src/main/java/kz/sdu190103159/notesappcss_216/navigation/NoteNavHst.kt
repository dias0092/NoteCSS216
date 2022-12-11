package kz.sdu190103159.notesappcss_216.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kz.sdu190103159.notesappcss_216.scrnss.StartScreen
import kz.sdu190103159.notesappcss_216.scrnss.MainScreen
import kz.sdu190103159.notesappcss_216.scrnss.NoteScreen
import kz.sdu190103159.notesappcss_216.scrnss.AddScreen
import kz.sdu190103159.notesappcss_216.MainViewMdl

sealed class NavRoute(val route:String ){
    object StartScreen: NavRoute("start_screen")
    object MainScreen: NavRoute("main_screen")
    object AddScreen: NavRoute("add_screen")
    object NoteScreen: NavRoute("note_screen")
}
@Composable
fun NoteNavHst(mViewModel: MainViewMdl) {
    val navController = rememberNavController()

    NavHost(navController = navController , startDestination = NavRoute.StartScreen.route ) {
        composable(NavRoute.StartScreen.route) {
            StartScreen(navController = navController , viewModel = mViewModel)
        }
        composable(NavRoute.MainScreen.route) {
            MainScreen(navController = navController , viewModel = mViewModel)
        }
        composable(NavRoute.AddScreen.route) {
            AddScreen(navController = navController , viewModel = mViewModel)
        }
        composable(NavRoute.NoteScreen.route) {
            NoteScreen(navController = navController, viewModel = mViewModel)
        }
    }
}