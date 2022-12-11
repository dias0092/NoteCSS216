package kz.sdu190103159.notesappcss_216.scrnss


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.material.*


import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.compose.rememberNavController

import kz.sdu190103159.notesappcss_216.ui.theme.NotesAppCSS216Theme

@Composable
fun NoteScreen(navController: NavHostController) {
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
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Title",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    Text(text = "SubTitle",
                        fontSize = 40.sp,

                        modifier = Modifier.padding(top = 15.dp)
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun prevNoteScreen(){
        NotesAppCSS216Theme() {
            NoteScreen(navController = rememberNavController())
        }
}