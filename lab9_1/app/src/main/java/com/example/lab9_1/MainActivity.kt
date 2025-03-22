package com.example.lab9_1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var editTextCourseName: EditText
    private lateinit var editTextCourseDescription: EditText
    private lateinit var buttonAddCourse: Button
    private lateinit var recyclerViewCourses: RecyclerView
    private lateinit var courseAdapter: CourseAdapter
    private lateinit var database: AppDatabase

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextCourseName = findViewById(R.id.editTextCourseName)
        editTextCourseDescription = findViewById(R.id.editTextCourseDescription)
        buttonAddCourse = findViewById(R.id.buttonAddCourse)
        recyclerViewCourses = findViewById(R.id.recyclerViewCourses)

        database = AppDatabase.getDatabase(this)

        recyclerViewCourses.layoutManager = LinearLayoutManager(this)
        courseAdapter = CourseAdapter(mutableListOf(), {}, {})
        recyclerViewCourses.adapter = courseAdapter

        buttonAddCourse.setOnClickListener {
            val name = editTextCourseName.text.toString()
            val description = editTextCourseDescription.text.toString()
            if (name.isNotEmpty() && description.isNotEmpty()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    database.courseDao().insertCourse(Course(name = name, description = description))
                    loadCourses()
                }
            }
        }
    }

    private fun loadCourses() {
        lifecycleScope.launch(Dispatchers.IO) {
            val courses = database.courseDao().getAllCourses()
            withContext(Dispatchers.Main) {
                courseAdapter.updateCourses(courses)
            }
        }
    }
}
