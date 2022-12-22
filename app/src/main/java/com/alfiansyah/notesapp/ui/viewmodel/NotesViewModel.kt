package com.alfiansyah.notesapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.alfiansyah.notesapp.db.NotesDatabase
import com.alfiansyah.notesapp.model.Notes
import com.alfiansyah.notesapp.repository.NotesRepository
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NotesRepository

    init {

        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)

    }

    fun addNotes(title: String, subTitle: String, notes: String, date: String, priority: String) =
        viewModelScope.launch {

            val data = Notes(
                title = title,
                subTitle = subTitle,
                notes = notes,
                date = date,
                priority = priority
            )
            repository.insertNotes(data)
        }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun deleteNotes(id: Int) = viewModelScope.launch {
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) = viewModelScope.launch {
        repository.updateNotes(notes)
    }
}