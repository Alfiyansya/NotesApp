package com.alfiansyah.notesapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alfiansyah.notesapp.model.Notes

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes")
    fun getNotes():LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes )

    @Query("DELETE FROM Notes WHERE id=:id")
    suspend fun deleteNotes(id: Int)

    @Update
    suspend fun updateNotes(notes: Notes)

}