package com.alfiansyah.notesapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.alfiansyah.notesapp.db.NotesDatabase
import com.alfiansyah.notesapp.model.Notes
import com.alfiansyah.notesapp.repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NotesRepository

    init {

        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)

    }

    fun addNotes(notes: Notes) {
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun deleteNotes(id: Int) {
        repository.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}