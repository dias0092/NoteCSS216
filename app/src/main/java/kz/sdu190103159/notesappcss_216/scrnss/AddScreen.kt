package kz.sdu190103159.notesappcss_216.scrnss

import android.app.Application
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.rememberNavController
import kz.sdu190103159.notesappcss_216.MainViewMdl
import kz.sdu190103159.notesappcss_216.MainViewMdlFactory
import kz.sdu190103159.notesappcss_216.model.Note
import kz.sdu190103159.notesappcss_216.navigation.NavRoute
import kz.sdu190103159.notesappcss_216.ui.theme.NotesAppCSS216Theme
import kz.sdu190103159.notesappcss_216.utils.Constants.Keys.ADD_NEW_NOTE
import kz.sdu190103159.notesappcss_216.utils.Constants.Keys.NOTE_ADD
import kz.sdu190103159.notesappcss_216.utils.Constants.Keys.NOTE_SUBTITLE
import kz.sdu190103159.notesappcss_216.utils.Constants.Keys.NOTE_TITLE


@Composable
fun AddScreen(navController: NavHostController, viewModel: MainViewMdl) {
    var title by remember { mutableStateOf( "")}
    var subtitle by remember { mutableStateOf( "")}
    var isButtonEnb by remember { mutableStateOf(false) }
    Scaffold {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
           Text(
               text = ADD_NEW_NOTE,
               fontSize = 18.sp,
               fontWeight = FontWeight.Bold,
               modifier = Modifier.padding(vertical = 8.dp)

           )

            TextField(
                value = title,
                onValueChange = {
                    title = it
                    isButtonEnb = title.isNotEmpty() && subtitle.isNotEmpty()
                                },
                label = {Text(text = NOTE_TITLE)},
                isError = title.isEmpty()
            )
            TextField(
                value = subtitle,
                onValueChange = {
                    subtitle = it
                    isButtonEnb = title.isNotEmpty() && subtitle.isNotEmpty()
                                },
                label = {Text(text = NOTE_SUBTITLE)},
                isError = subtitle.isEmpty()
            )
            Button(
                modifier = Modifier.padding( top = 16.dp),
                enabled = isButtonEnb,
                onClick = {
                    viewModel.addNote(note = Note(title = title , subtitle = subtitle)){
                        navController.navigate(NavRoute.MainScreen.route)
                    }

                }
            ) {
                Text(text = NOTE_ADD)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevAddScreen() {
    NotesAppCSS216Theme() {
        val context = LocalContext.current
        val mViewModel: MainViewMdl =
            viewModel(factory = MainViewMdlFactory(context.applicationContext as Application))
        AddScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}