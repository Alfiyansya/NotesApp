package com.alfiansyah.notesapp.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alfiansyah.notesapp.R
import com.alfiansyah.notesapp.databinding.FragmentCreateNotesBinding
import com.alfiansyah.notesapp.ui.viewmodel.NotesViewModel
import com.alfiansyah.notesapp.utils.toast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class CreateNotesFragment : Fragment() {
    private var _binding: FragmentCreateNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var priority: String

    private val viewModel: NotesViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)
        binding.apply {
            btnSaveNotes.setOnClickListener {
                createNotes()
            }
            priority = resources.getString(R.string.priority_one)
            greenDot.setImageResource(R.drawable.ic_check_24)
            greenDot.setOnClickListener {
                priority = resources.getString(R.string.priority_one)
                greenDot.setImageResource(R.drawable.ic_check_24)
                redDot.setImageResource(0)
                yellowDot.setImageResource(0)
            }
            redDot.setOnClickListener {
                priority = resources.getString(R.string.priority_two)
                redDot.setImageResource(R.drawable.ic_check_24)
                greenDot.setImageResource(0)
                yellowDot.setImageResource(0)
            }
            yellowDot.setOnClickListener {
                priority = resources.getString(R.string.priority_three)
                yellowDot.setImageResource(R.drawable.ic_check_24)
                redDot.setImageResource(0)
                greenDot.setImageResource(0)
            }
        }
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotes() {

        val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
        val currentDate = LocalDateTime.now().format(formatter)

        binding.apply {

            val title = edtTitle.text.toString()
            val subTitle = edtSubTitle.text.toString()
            val notes = edtNotes.text.toString()

            val notesSuccess =
                resources.getString(R.string.notes_created_success)
            viewModel.addNotes(title, subTitle, notes, currentDate.toString(), priority)
            requireActivity().toast(notesSuccess).also {
                findNavController().navigate(
                    R.id.action_createNotesFragment_to_homeFragment
                )
            }

        }

    }

}