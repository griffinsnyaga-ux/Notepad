package com.notes.simple

import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNote: EditText
    private lateinit var buttonSave: Button
    private lateinit var listViewNotes: ListView
    private lateinit var notesList: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // IMPORTANT: Load the notes screen layout
        setContentView(R.layout.activity_main)

        editTextNote = findViewById(R.id.editTextNote)
        buttonSave = findViewById(R.id.buttonSave)
        listViewNotes = findViewById(R.id.listViewNotes)

        // Load saved notes
        notesList = loadNotes().toMutableList()

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            notesList
        )

        listViewNotes.adapter = adapter

        buttonSave.setOnClickListener {
            val noteText = editTextNote.text.toString().trim()

            if (noteText.isNotEmpty()) {
                notesList.add(noteText)
                saveNotes(notesList)
                adapter.notifyDataSetChanged()
                editTextNote.text.clear()
                Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Write something first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveNotes(notes: List<String>) {
        val sharedPreferences = getSharedPreferences("notes_pref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putStringSet("notes", notes.toSet())
        editor.apply()
    }

    private fun loadNotes(): Set<String> {
        val sharedPreferences = getSharedPreferences("notes_pref", Context.MODE_PRIVATE)
        return sharedPreferences.getStringSet("notes", emptySet()) ?: emptySet()
    }
}
