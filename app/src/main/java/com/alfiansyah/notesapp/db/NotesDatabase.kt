package com.alfiansyah.notesapp.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alfiansyah.notesapp.db.dao.NotesDao

abstract class NotesDatabase : RoomDatabase(){
    abstract fun myNotesDao(): NotesDao

    companion object {
        @Volatile
        var INSTANCE: NotesDatabase?= null

        fun getDatabaseInstance(context: Context): NotesDatabase{

            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val roomDatabaseInstance = Room.databaseBuilder(context, NotesDatabase::class.java,"NotesDatabase").build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }

        }
    }
}