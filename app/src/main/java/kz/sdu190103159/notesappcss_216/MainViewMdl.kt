package kz.sdu190103159.notesappcss_216

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kz.sdu190103159.notesappcss_216.model.Note

import kz.sdu190103159.notesappcss_216.utils.TYPE_ROOM

class MainViewMdl(application: Application) : AndroidViewModel(application) {
    val readTest: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }
    val databaseType: MutableLiveData<String> by lazy{
        MutableLiveData<String>(TYPE_ROOM)
    }
    init {
        readTest.value =
            when(databaseType.value) {
                TYPE_ROOM -> {
                    listOf<Note>(
                        Note(title = "Note 1" , subtitle = "SubNote 1"),
                        Note(title = "Note 1" , subtitle = "SubNote 2"),
                        Note(title = "Note 5" , subtitle = "SubNote 3"),
                        Note(title = "Note 4" , subtitle = "SubNote 4")
                    )
                } else -> listOf()
            }
    }
    fun initDatabase(type : String) {
        databaseType.value = type
        Log.d("checkData", "MainViewMdl initDatabase with type: $type")
    }
}

class MainViewMdlFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewMdl::class.java)) {
            return MainViewMdl(application = application)  as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}