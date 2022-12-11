package kz.sdu190103159.notesappcss_216.scrnss

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import kz.sdu190103159.notesappcss_216.MainViewMdl
import kz.sdu190103159.notesappcss_216.MainViewMdlFactory
import kz.sdu190103159.notesappcss_216.navigation.NavRoute
import kz.sdu190103159.notesappcss_216.ui.theme.NotesAppCSS216Theme
import kz.sdu190103159.notesappcss_216.utils.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewMdl) {

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutionScope = rememberCoroutineScope()
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 32.dp)
                ) {
                    Text(
                        text = "Edit note",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    TextField(
                        value = login,
                        onValueChange = { login = it },
                        label = { Text(text = Constants.Keys.LOGIN_TEXT) },
                        isError = login.isEmpty()
                    )
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = Constants.Keys.PASSWORD_TEXT) },
                        isError = password.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            LOGIN = login
                            PASSWORD = password
                            viewModel.initDatabase(TYPE_FIREBASE) {
                                DB_TYPE.value = TYPE_FIREBASE
                                navController.navigate(NavRoute.MainScreen.route)

                            }
                        },
                        enabled = login.isNotEmpty() && password.isNotEmpty()
                    )
                    {
                        Text(text = Constants.Keys.SIGN_IN)
                    }
                }
            }
        }
    ) {
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
                       viewModel.initDatabase(TYPE_ROOM) {
                           DB_TYPE.value = TYPE_ROOM
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
                        coroutionScope.launch {
                            bottomSheetState.show()
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