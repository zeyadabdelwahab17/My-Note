package com.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.Model.Note
import com.ViewModel.NoteViewModel
import com.example.mynotesapp.MainActivity
import com.example.mynotesapp.R
import com.example.mynotesapp.databinding.FragmentEditNoteBinding


class edit_NoteFragment : Fragment(R.layout.fragment_edit__note), MenuProvider {

    private var editNoteBinding : FragmentEditNoteBinding? = null
    private val binding get() = editNoteBinding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var currentNote : Note

    private val args : edit_NoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        editNoteBinding = FragmentEditNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner)
        noteViewModel = (activity as MainActivity).noteViewModel

        currentNote = args.note!!

        binding.editNoteTitle.setText(currentNote.title)
        binding.editNoteDesc.setText(currentNote.description)

        binding.editNoteFab.setOnClickListener {
            val noteTitle = binding.editNoteTitle.text.toString().trim()
            val noteDesc = binding.editNoteDesc.text.toString().trim()

            if (noteTitle.isNotEmpty()){
                val note = Note(currentNote.id,noteTitle,noteDesc)
                noteViewModel.updateNote(note)

                view.findNavController().popBackStack(R.id.home,false)
            }else{
                Toast.makeText(context, "please enter note tittle", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun DeleteNote(){
        AlertDialog.Builder(requireActivity()).apply {
            setTitle("Delete Note")
            setMessage("Do you want to delete this note?")
            setPositiveButton("Delete"){_,_ ->

                noteViewModel.deleteNote(currentNote)
                Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.home,false)

            }

            setNegativeButton("Cancel",null)

        }.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.edit_note_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

        return when(menuItem.itemId){
            R.id.deleteMenu ->{
                DeleteNote()
                true
            }else -> false

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        editNoteBinding = null
    }

}