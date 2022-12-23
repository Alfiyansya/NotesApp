package com.alfiansyah.notesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfiansyah.notesapp.R
import com.alfiansyah.notesapp.databinding.ItemNotesBinding
import com.alfiansyah.notesapp.model.Notes

class NotesAdapter( private val notesList: List<Notes>): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    class NotesViewHolder(private val binding : ItemNotesBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(notes: Notes){
            with(binding){
                itemNotesTitle.text = notes.title
                itemNotesSubTitle.text = notes.subTitle
                itemNotesDate.text = notes.date
                when(notes.priority){
                    FIRST_PRIORITY ->{
                        viewPriority.setBackgroundResource(R.drawable.green_dot)
                    }
                    SECOND_PRIORITY ->{
                        viewPriority.setBackgroundResource(R.drawable.yellow_dot)

                    }
                    THIRD_PRIORITY ->{
                        viewPriority.setBackgroundResource(R.drawable.red_dot)

                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val notes = notesList[position]
        holder.bind(notes)
    }

    override fun getItemCount() = notesList.size
    companion object{
        private const val FIRST_PRIORITY = "1"
        private const val SECOND_PRIORITY = "2"
        private const val THIRD_PRIORITY = "3"
    }
}