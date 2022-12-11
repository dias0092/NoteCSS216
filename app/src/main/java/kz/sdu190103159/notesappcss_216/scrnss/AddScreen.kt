package kz.sdu190103159.notesappcss_216.scrnss

import androidx.compose.foundation.clickable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.compose.rememberNavController
import kz.sdu190103159.notesappcss_216.navigation.NavRoute
import kz.sdu190103159.notesappcss_216.ui.theme.NotesAppCSS216Theme

@Composable
fun AddScreen(navController: NavHostController) {
    var title by remember { mutableStateOf( "")}
    var subtitle by remember { mutableStateOf( "")}
    Scaffold {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
           Text(
               text = "Add new note",
               fontSize = 18.sp,
               fontWeight = FontWeight.Bold,
               modifier = Modifier.padding(vertical = 8.dp)

           )

            TextField(
                value = title,
                onValueChange = {title = it },
                label = {Text(text = "Note title")}
            )
            TextField(
                value = subtitle,
                onValueChange = {subtitle = it },
                label = {Text(text = "Note subtitle")}
            )
            Button(
                modifier = Modifier.padding( top = 16.dp),
                onClick = {
                    navController.navigate(NavRoute.MainScreen.route)
                }
            ) {
                Text(text = "Add note")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevAddScreen() {
    NotesAppCSS216Theme() {
        AddScreen(navController = rememberNavController())
    }
}