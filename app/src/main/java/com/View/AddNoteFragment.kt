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
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.navigation.findNavController
import com.Model.Note
import com.ViewModel.NoteViewModel
import com.example.mynotesapp.MainActivity
import com.example.mynotesapp.R
import com.example.mynotesapp.databinding.FragmentAddNoteBinding
import com.example.mynotesapp.databinding.FragmentHomeBinding

class AddNoteFragment : Fragment(R.layout.fragment_add_note),MenuProvider {

    private var addNoteBinding: FragmentAddNoteBinding? = null
    private val binding get() = addNoteBinding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var addNoteView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        addNoteBinding = FragmentAddNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner)
        noteViewModel = (activity as MainActivity).noteViewModel

        addNoteView = view

    }

    private fun saveNote(view: View){

        val noteTittle = binding.addNoteTitle.text.toString().trim()
        val noteDescription = binding.addNoteDesc.text.toString().trim()

        if (noteTittle.isNotEmpty()){
            val note= Note(0,noteTittle,noteDescription)
            noteViewModel.addNote(note)

            Toast.makeText(addNoteView.context, "Note saved", Toast.LENGTH_SHORT).show()

            //get back to home page after saving
            view.findNavController().popBackStack(R.id.home,false)
        }else{

            Toast.makeText(addNoteView.context, "Please enter note title", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.add_note_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

        return when (menuItem.itemId){

            R.id.saveMenu -> {
                saveNote(addNoteView)
                true
            }
            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addNoteBinding = null
    }


}