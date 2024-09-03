package com.maya.notesapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.maya.notesapp.database.Note
import com.maya.notesapp.database.RoomDBHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel (app:Application): AndroidViewModel(app){
    val dp =RoomDBHelper.getInstance(app)
    fun upsert(n: Note){
        viewModelScope.launch(Dispatchers.IO) {
            dp.noteDao.upsertNote(n)
        }
    }
    fun delete(n: Note) {
        viewModelScope.launch(Dispatchers.IO)  {
            dp.noteDao.deleteNote(n)
        }
    }
    fun getNotes()= dp.noteDao.getAllNotes()
}