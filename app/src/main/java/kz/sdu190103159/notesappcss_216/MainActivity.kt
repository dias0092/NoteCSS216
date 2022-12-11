package kz.sdu190103159.notesappcss_216

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kz.sdu190103159.notesappcss_216.ui.theme.NotesAppCSS216Theme
import kz.sdu190103159.notesappcss_216.navigation.NoteNavHst

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppCSS216Theme {
                val context = LocalContext.current
                val mViewModel: MainViewMdl =
                    viewModel(factory = MainViewMdlFactory(context.applicationContext as Application))
              Scaffold(topBar = {
                  TopAppBar( title = { Text(text = "Notes")} , backgroundColor = Color.Blue , contentColor = Color.White , elevation =  12.dp )


              }  ,
                  content = {
                      Surface(
                          modifier = Modifier.fillMaxSize(),
                          color = MaterialTheme.colors.background
                      ) {
                          NoteNavHst(mViewModel)
                      }
                  })

              }
            }
        }
    }




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotesAppCSS216Theme {

    }
}