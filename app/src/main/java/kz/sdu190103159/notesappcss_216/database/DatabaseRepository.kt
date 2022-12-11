package kz.sdu190103159.notesappcss_216.database

import androidx.lifecycle.LiveData
import kz.sdu190103159.notesappcss_216.model.Note

interface DatabaseRepository {
    val readAll: LiveData<List<Note>>

    suspend fun create(note: Note, onSuccess: ()-> Unit)
    suspend fun update(note: Note, onSuccess: ()-> Unit)
    suspend fun delete(note: Note, onSuccess: ()-> Unit)

    fun singOut() {}
    fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {}
}