package com.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.Model.Note
import com.Repo.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app: Application, private val noteRepository: NoteRepository) : AndroidViewModel(app) {

    fun addNote(note: Note){
        viewModelScope.launch{
            noteRepository.insertNote(note)

        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch{
            noteRepository.updatetNote(note)

        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch{
            noteRepository.deleteNote(note)

        }

    }

    fun searchNote(query: String?) =
        noteRepository.searchNote(query)



    fun getAllNotes() = noteRepository.getAllNotes()

}