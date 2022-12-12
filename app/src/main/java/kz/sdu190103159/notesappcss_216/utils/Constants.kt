package kz.sdu190103159.notesappcss_216.utils

import androidx.compose.runtime.mutableStateOf
import kz.sdu190103159.notesappcss_216.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"
const val FIREBASE_ID = "firebase id"



lateinit var REPOSITORY: DatabaseRepository
lateinit var LOGIN: String
lateinit var PASSWORD: String
var DB_TYPE = mutableStateOf("")


object Constants {
    object Keys {
        const val NOTE_DATABASE = "notes_databasee"
        const val NOTE_TABLE = "notes_table"
        const val ADD_NEW_NOTE = "add new note"
        const val NOTE_TITLE = "title"
        const val NOTE_SUBTITLE = "subtitle"
        const val NOTE_ADD  = "note add"
        const val TITLE = "title"
        const val SUBTITLE = "subtitle"
        const val ID = "id"
        const val NONE = "none"
        const val UPDATE = "update"
        const val DELETE = "delete"
        const val NAV_BACK ="back"
        const val LOG_IN = "Log In"
        const val LOGIN_TEXT = "Login"
        const val PASSWORD_TEXT = "Password"
        const val UPDATE_NOTE = "Update note"
        const val SIGN_IN = "Sign in"
        const val EMPTY = ""
    }
    object Screens{
        const val START_SCREEN = "start_screen"
        const val ADD_SCREEN = "add_screen"
        const val MAIN_SCREEN = "main_screen"
        const val NOTE_SCREEN = "note_screen"
    }
}