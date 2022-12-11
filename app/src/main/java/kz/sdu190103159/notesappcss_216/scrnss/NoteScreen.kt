package kz.sdu190103159.notesappcss_216.scrnss


import android.app.Application
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState


import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import kz.sdu190103159.notesappcss_216.MainViewMdl
import kz.sdu190103159.notesappcss_216.MainViewMdlFactory
import kz.sdu190103159.notesappcss_216.model.Note
import kz.sdu190103159.notesappcss_216.navigation.NavRoute

import kz.sdu190103159.notesappcss_216.ui.theme.NotesAppCSS216Theme
import kz.sdu190103159.notesappcss_216.utils.Constants
import kz.sdu190103159.notesappcss_216.utils.Constants.Keys.DELETE
import kz.sdu190103159.notesappcss_216.utils.Constants.Keys.NAV_BACK
import kz.sdu190103159.notesappcss_216.utils.Constants.Keys.NONE
import kz.sdu190103159.notesappcss_216.utils.Constants.Keys.SUBTITLE
import kz.sdu190103159.notesappcss_216.utils.Constants.Keys.TITLE
import kz.sdu190103159.notesappcss_216.utils.Constants.Keys.UPDATE
import kz.sdu190103159.notesappcss_216.utils.Constants.Keys.UPDATE_NOTE

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteScreen(navController: NavHostController, viewModel: MainViewMdl, noteId: String?) {
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note = notes.firstOrNull{it.id == noteId?.toInt()} ?: Note(title = NONE , subtitle = NONE )
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutionScope = rememberCoroutineScope()
    var title by remember { mutableStateOf("Empty") }
    var subtitle by remember { mutableStateOf("Empty") }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 32.dp)
                ){  Text(
                    text = "Edit note",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier =  Modifier.padding(vertical = 8.dp)
                )
                    TextField(
                        value = title ,
                        onValueChange = { title = it },
                        label = { Text(text = TITLE)},
                        isError = title.isEmpty()
                    )
                    TextField(
                        value = subtitle ,
                        onValueChange = { subtitle = it },
                        label = { Text(text = SUBTITLE)},
                        isError = subtitle.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            viewModel.updateNote(
                                note =
                                Note(id = note.id, title = title, subtitle = subtitle)
                            ){
                                navController.navigate(NavRoute.MainScreen.route)
                            }
                        })
                    {
                        Text(text = UPDATE)
                    }
                }
            }
        }
    ) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(15.dp)
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = note.title,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    Text(
                        text = note.subtitle,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 15.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    coroutionScope.launch {
                        title = note.title
                        subtitle = note.subtitle
                        bottomSheetState.show()
                    }
                } ){
                    Text(text = UPDATE)
                }
                Button(onClick = {
                        viewModel.deleteNote(note = note ){
                            navController.navigate(NavRoute.MainScreen.route)
                        }
                } ){
                    Text(text = DELETE)
                }
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                    onClick = {
                        navController.navigate(NavRoute.MainScreen.route)
                } ){
                    Text(text = NAV_BACK)
                }
            }
        }


        }

    }

}

@Preview(showBackground = true)
@Composable
fun prevNoteScreen(){
        NotesAppCSS216Theme() {
            val context = LocalContext.current
            val mViewModel: MainViewMdl =
                viewModel(factory = MainViewMdlFactory(context.applicationContext as Application))
            NoteScreen(
                navController = rememberNavController(),
                viewModel = mViewModel,
                noteId = "1"
            )
        }
}