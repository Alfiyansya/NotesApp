package com.alfiansyah.notesapp.repository

import androidx.lifecycle.LiveData
import com.alfiansyah.notesapp.db.dao.NotesDao
import com.alfiansyah.notesapp.model.Notes

class NotesRepository(val dao: NotesDao) {
    fun getAllNotes(): LiveData<List<Notes>>{
        return dao.getNotes()
    }
    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }
    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}