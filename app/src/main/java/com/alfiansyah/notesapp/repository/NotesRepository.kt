package com.alfiansyah.notesapp.repository

import androidx.lifecycle.LiveData
import com.alfiansyah.notesapp.db.dao.NotesDao
import com.alfiansyah.notesapp.model.Notes

class NotesRepository(private val dao: NotesDao) {
    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getNotes()
    }

    suspend fun insertNotes(notes: Notes) {
        dao.insertNotes(notes)
    }

    suspend fun deleteNotes(id: Int) {
        dao.deleteNotes(id)
    }

    suspend fun updateNotes(notes: Notes) {
        dao.updateNotes(notes)
    }
}