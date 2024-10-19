package com.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.Model.Note

@Dao
interface NoteDao {

    //used to handle scenarios where the app tries to insert a row that already exists in the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note_table where title LIKE :query OR description LIKE :query ORDER BY id DESC")
    fun searchNote(query: String?): LiveData<List<Note>>

}