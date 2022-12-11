package kz.sdu190103159.notesappcss_216.scrnss

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.rememberNavController
import kz.sdu190103159.notesappcss_216.MainViewMdl
import kz.sdu190103159.notesappcss_216.MainViewMdlFactory
import kz.sdu190103159.notesappcss_216.navigation.NavRoute
import kz.sdu190103159.notesappcss_216.ui.theme.NotesAppCSS216Theme
import kz.sdu190103159.notesappcss_216.model.Note

@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val mViewModel: MainViewMdl =
        viewModel(factory = MainViewMdlFactory(context.applicationContext as Application))
    val notes = mViewModel.readTest.observeAsState(listOf()).value
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoute.AddScreen.route)
                })
            {
                Icon(imageVector = Icons.Filled.Add , contentDescription = "Add Icons" , tint = Color.Gray)
            }
        }
    ){
        LazyColumn {
            items(notes) {
                note -> NoteItem(note = note , navController = navController)
            }
        }

    }
}

@Composable
fun NoteItem(note: Note, navController: NavHostController){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 20.dp)
            .clickable {
                navController.navigate(NavRoute.NoteScreen.route)
            },
        elevation = 6.dp
    ){
        Column(modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = note.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = note.subtitle)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun prevMainScreen(){
    NotesAppCSS216Theme {
        MainScreen(navController = rememberNavController())
    }
}