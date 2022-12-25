package com.alfiansyah.notesapp.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alfiansyah.notesapp.R
import com.alfiansyah.notesapp.databinding.FragmentEditNotesBinding
import com.alfiansyah.notesapp.ui.viewmodel.NotesViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EditNotesFragment : Fragment() {

    private var _binding: FragmentEditNotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var priority: String

    private val oldNotes by navArgs<EditNotesFragmentArgs>()

    private val viewModel: NotesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val notes = oldNotes.data
            edtTitle.setText(notes.title)
            edtSubTitle.setText(notes.subTitle)
            edtNotes.setText(notes.notes)

            val priorityOne = resources.getString(R.string.priority_one)
            val priorityTwo = resources.getString(R.string.priority_two)
            val priorityThree = resources.getString(R.string.priority_three)

            when (notes.priority) {
                priorityOne -> {
                    priority = resources.getString(R.string.priority_one)

                    greenDot.setImageResource(R.drawable.ic_check_24)
                    redDot.setImageResource(0)
                    yellowDot.setImageResource(0)
                }
                priorityTwo -> {
                    priority = resources.getString(R.string.priority_two)

                    yellowDot.setImageResource(R.drawable.ic_check_24)
                    redDot.setImageResource(0)
                    greenDot.setImageResource(0)

                }
                priorityThree -> {
                    priority = resources.getString(R.string.priority_three)

                    redDot.setImageResource(R.drawable.ic_check_24)
                    yellowDot.setImageResource(0)
                    greenDot.setImageResource(0)

                }
            }
            btnEditSaveNotes.setOnClickListener {
                updateNotes()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateNotes() {

        val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
        val currentDate = LocalDateTime.now().format(formatter)

        binding.apply {

            val title = edtTitle.text.toString()
            val subTitle = edtSubTitle.text.toString()
            val notes = edtNotes.text.toString()

            viewModel.updateNotes(
                oldNotes.data.id ?: 0,
                title,
                subTitle,
                notes,
                currentDate.toString(),
                priority
            )
            findNavController().navigate(
                R.id.action_editNotesFragment_to_homeFragment
            )

        }

    }

}