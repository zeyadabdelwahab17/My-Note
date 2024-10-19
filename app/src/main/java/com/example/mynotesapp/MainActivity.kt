package com.example.mynotesapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import com.Database.NoteDatabase
import com.Repo.NoteRepository
import com.ViewModel.NoteViewModel
import com.ViewModel.NoteViewModelFactory
import com.example.mynotesapp.R

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragmentContainerView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpViewModel()

    }
    private fun setUpViewModel(){
        val noteRepository = NoteRepository(NoteDatabase(this))
        val noteViewModelFactory = NoteViewModelFactory(application,noteRepository)
        noteViewModel = ViewModelProvider(this,noteViewModelFactory).get(NoteViewModel::class.java)

    }
}