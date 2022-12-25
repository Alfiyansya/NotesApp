package com.alfiansyah.notesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.alfiansyah.notesapp.R
import com.alfiansyah.notesapp.databinding.FragmentHomeBinding
import com.alfiansyah.notesapp.model.Notes
import com.alfiansyah.notesapp.ui.adapter.NotesAdapter
import com.alfiansyah.notesapp.ui.adapter.OnItemNotesClickCallback
import com.alfiansyah.notesapp.ui.viewmodel.NotesViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NotesAdapter
    private val viewModel: NotesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            setNotesData(notesList)


        }
        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

    }

    private fun setNotesData(notes: List<Notes>) {
        binding.rvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = NotesAdapter(notes)
        binding.rvAllNotes.adapter = adapter
        adapter.setOnItemClickCallback(object : OnItemNotesClickCallback {
            override fun onItemClicked(view: View, notes: Notes) {
                val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(notes)
                Navigation.findNavController(view).navigate(action)
            }

        })
    }
}