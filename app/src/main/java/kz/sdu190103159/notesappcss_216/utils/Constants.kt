package kz.sdu190103159.notesappcss_216.utils

import kz.sdu190103159.notesappcss_216.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DatabaseRepository

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
    }
    object Screens{
        const val START_SCREEN = "start_screen"
        const val ADD_SCREEN = "add_screen"
        const val MAIN_SCREEN = "main_screen"
        const val NOTE_SCREEN = "note_screen"
    }
}