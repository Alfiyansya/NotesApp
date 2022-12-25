package com.alfiansyah.notesapp.ui.adapter

import android.view.View
import com.alfiansyah.notesapp.model.Notes

interface OnItemNotesClickCallback {
    fun onItemClicked(view: View, notes: Notes)
}