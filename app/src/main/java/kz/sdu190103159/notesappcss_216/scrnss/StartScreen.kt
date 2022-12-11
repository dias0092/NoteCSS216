package kz.sdu190103159.notesappcss_216.scrnss

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kz.sdu190103159.notesappcss_216.MainViewMdl
import kz.sdu190103159.notesappcss_216.MainViewMdlFactory
import kz.sdu190103159.notesappcss_216.navigation.NavRoute
import kz.sdu190103159.notesappcss_216.ui.theme.NotesAppCSS216Theme
import kz.sdu190103159.notesappcss_216.utils.TYPE_FIREBASE
import kz.sdu190103159.notesappcss_216.utils.TYPE_ROOM

@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewMdl) {
    val context = LocalContext.current
    val mViewModel: MainViewMdl =
        viewModel(factory = MainViewMdlFactory(context.applicationContext as Application))
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize() ,
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement.Center )
        {
        Text(text = "Zhuzyas NOTES")
        Button(
            onClick = {
                mViewModel.initDatabase(TYPE_ROOM){
                    navController.navigate(route = NavRoute.MainScreen.route)
                }

            },
            modifier = Modifier
                .width(125.dp)
                .padding(vertical = 10.dp)
        ){
            Text(text = "Room")
        }
            Button(
                onClick = {
                    mViewModel.initDatabase(TYPE_FIREBASE){
                        navController.navigate(route = NavRoute.MainScreen.route)
                    }

                },
                modifier = Modifier
                    .width(125.dp)
                    .padding(vertical = 10.dp)
            ){
                Text(text = "Fire")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun prevStartScreen(){
    NotesAppCSS216Theme {
        val context = LocalContext.current
        val mViewModel: MainViewMdl =
            viewModel(factory = MainViewMdlFactory(context.applicationContext as Application))

        StartScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}