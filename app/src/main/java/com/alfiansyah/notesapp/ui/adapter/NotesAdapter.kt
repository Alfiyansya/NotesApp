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
                    itemView.resources.getString(R.string.priority_one)->{
                        viewPriority.setBackgroundResource(R.drawable.green_dot)
                    }
                    itemView.resources.getString(R.string.priority_two)->{
                        viewPriority.setBackgroundResource(R.drawable.yellow_dot)

                    }
                    itemView.resources.getString(R.string.priority_three) ->{
                        viewPriority.setBackgroundResource(R.drawable.red_dot)

                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    private lateinit var onItemNotesClickCallback: OnItemNotesClickCallback

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val notes = notesList[position]
        holder.bind(notes)
        holder.itemView.setOnClickListener {  onItemNotesClickCallback.onItemClicked(it,notes)}
    }
    fun setOnItemClickCallback(onItemNotesClickCallback: OnItemNotesClickCallback){
        this.onItemNotesClickCallback = onItemNotesClickCallback
    }

    override fun getItemCount() = notesList.size

}