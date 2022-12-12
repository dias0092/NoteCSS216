package kz.sdu190103159.notesappcss_216.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kz.sdu190103159.notesappcss_216.utils.Constants.Keys.NOTE_TABLE

@Entity(tableName = NOTE_TABLE)
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val title: String = "",
    @ColumnInfo
    val subtitle: String = "",
    val fbId: String = ""
)
