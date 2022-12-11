package kz.sdu190103159.notesappcss_216.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kz.sdu190103159.notesappcss_216.database.DatabaseRepository
import kz.sdu190103159.notesappcss_216.model.Note
import kz.sdu190103159.notesappcss_216.utils.LOGIN
import kz.sdu190103159.notesappcss_216.utils.PASSWORD

import kz.sdu190103159.notesappcss_216.utils.Constants.Keys.SUBTITLE
import kz.sdu190103159.notesappcss_216.utils.Constants.Keys.TITLE
import kz.sdu190103159.notesappcss_216.utils.FIREBASE_ID

class AppFirebaseRepository : DatabaseRepository{
    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

    override val readAll: LiveData<List<Note>> = NtsLiveData()


    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        val noteId = database.push().key.toString()
        val mapNote = hashMapOf<String, Any>()
        mapNote[FIREBASE_ID] = noteId
        mapNote[TITLE] = note.title
        mapNote[SUBTITLE] = note.subtitle

        database.child(noteId)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to add note to database") }
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun singOut() {
        mAuth.signOut()
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(LOGIN, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(LOGIN, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString()) }
            }


    }
}