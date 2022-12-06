package com.alfiansyah.notesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alfiansyah.notesapp.databinding.FragmentCreateNotesBinding

class CreateNotesFragment : Fragment() {
    private var _binding:FragmentCreateNotesBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCreateNotesBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

}