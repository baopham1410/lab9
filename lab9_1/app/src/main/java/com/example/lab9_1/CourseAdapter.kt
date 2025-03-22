package com.example.lab9_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CourseAdapter(
    var courses: MutableList<Course>,
    private val editClickListener: (Course) -> Unit,
    private val deleteClickListener: (Course) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val courseName: TextView = itemView.findViewById(R.id.textViewCourseName)
        private val courseDescription: TextView = itemView.findViewById(R.id.textViewCourseDescription)
        private val buttonEdit: Button = itemView.findViewById(R.id.buttonUpdate)
        private val buttonDelete: Button = itemView.findViewById(R.id.buttonDelete)

        fun bind(course: Course) {
            courseName.text = course.name
            courseDescription.text = course.description
            buttonEdit.setOnClickListener { editClickListener(course) }
            buttonDelete.setOnClickListener { deleteClickListener(course) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    override fun getItemCount(): Int = courses.size

    fun updateCourses(newCourses: List<Course>) {
        courses.clear()
        courses.addAll(newCourses)
        notifyDataSetChanged()
    }
}
