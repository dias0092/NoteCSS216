package kz.sdu190103159.notesappcss_216

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.sdu190103159.notesappcss_216.database.firebase.AppFirebaseRepository
import kz.sdu190103159.notesappcss_216.database.room.AppRoomDatabase
import kz.sdu190103159.notesappcss_216.database.room.repository.RoomRepository
import kz.sdu190103159.notesappcss_216.model.Note
import kz.sdu190103159.notesappcss_216.utils.*
import java.lang.IllegalArgumentException

class MainViewMdl (application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase(type: String, onSuccess: ()-> Unit) {
        Log.d("checkData", "MainViewModel initDatabase with type: $type")
        when(type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase(
                    { onSuccess() },
                    { Log.d("checkData", "Error: ${it}")}
                )
            }
        }
    }

    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.update(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun deleteNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun readAllNotes() = REPOSITORY.readAll

    fun signOut(onSuccess: () -> Unit) {
        when (DB_TYPE.value) {
            TYPE_FIREBASE,
            TYPE_ROOM -> {
                REPOSITORY.signOut()
                DB_TYPE.value = ""
                onSuccess()
            }
            else -> { Log.d("checkData", "signOut: ELSE: ${DB_TYPE.value}")}
        }
    }
}

class MainViewMdlFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewMdl::class.java)) {
            return MainViewMdl(application = application) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }

}