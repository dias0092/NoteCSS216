package kz.sdu190103159.notesappcss_216.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kz.sdu190103159.notesappcss_216.scrnss.StartScreen
import kz.sdu190103159.notesappcss_216.scrnss.MainScreen
import kz.sdu190103159.notesappcss_216.scrnss.NoteScreen
import kz.sdu190103159.notesappcss_216.scrnss.AddScreen
import kz.sdu190103159.notesappcss_216.MainViewMdl
import kz.sdu190103159.notesappcss_216.utils.Constants.Screens.ADD_SCREEN
import kz.sdu190103159.notesappcss_216.utils.Constants.Screens.MAIN_SCREEN
import kz.sdu190103159.notesappcss_216.utils.Constants.Screens.NOTE_SCREEN
import kz.sdu190103159.notesappcss_216.utils.Constants.Screens.START_SCREEN
import kz.sdu190103159.notesappcss_216.utils.Constants.Keys.ID

sealed class NavRoute(val route:String ){
    object StartScreen: NavRoute(START_SCREEN)
    object MainScreen: NavRoute(MAIN_SCREEN)
    object AddScreen: NavRoute(ADD_SCREEN)
    object NoteScreen: NavRoute(NOTE_SCREEN)
}
@Composable
fun NoteNavHst(mViewModel: MainViewMdl, navController: NavHostController) {

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
        composable(NavRoute.NoteScreen.route + "/{${ID}}") { backStackEntry ->
            NoteScreen(navController = navController, viewModel = mViewModel, noteId = backStackEntry.arguments?.getString(ID))
        }
    }
}