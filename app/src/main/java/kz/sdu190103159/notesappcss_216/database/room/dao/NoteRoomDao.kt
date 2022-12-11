package kz.sdu190103159.notesappcss_216.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kz.sdu190103159.notesappcss_216.model.Note


@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM notes_table")
    fun getAllNote(): LiveData<List<Note>>

    @Insert
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}