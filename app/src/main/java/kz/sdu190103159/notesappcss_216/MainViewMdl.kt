package kz.sdu190103159.notesappcss_216

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.sdu190103159.notesappcss_216.database.room.AppRoomDatabase
import kz.sdu190103159.notesappcss_216.database.room.repository.RoomRepository
import kz.sdu190103159.notesappcss_216.model.Note

import kz.sdu190103159.notesappcss_216.utils.REPOSITORY

import kz.sdu190103159.notesappcss_216.utils.TYPE_ROOM

class MainViewMdl(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase(type : String, onSuccess: ()-> Unit) {
        Log.d("checkData", "MainViewMdl initDatabase with type: $type")
        when(type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context =  context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }
    fun addNote(note: Note, onSucccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main){
                    onSucccess()
                }
            }
        }
    }
    fun readAllNotes() = REPOSITORY.readAll
}

class MainViewMdlFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewMdl::class.java)) {
            return MainViewMdl(application = application)  as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}