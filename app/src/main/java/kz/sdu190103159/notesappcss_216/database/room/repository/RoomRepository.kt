package kz.sdu190103159.notesappcss_216.database.room.repository

import androidx.lifecycle.LiveData
import kz.sdu190103159.notesappcss_216.database.DatabaseRepository
import kz.sdu190103159.notesappcss_216.database.room.dao.NoteRoomDao
import kz.sdu190103159.notesappcss_216.model.Note

class RoomRepository (private val noteRoomDao: NoteRoomDao): DatabaseRepository{
    override val readAll: LiveData<List<Note>>
        get() = noteRoomDao.getAllNote()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note = note)
        onSuccess()
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = note)
        onSuccess()
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note = note)
        onSuccess()
    }
}