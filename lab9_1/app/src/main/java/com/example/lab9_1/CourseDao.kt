package com.example.lab9_1

import androidx.room.*

@Dao
interface CourseDao {
    @Insert
    fun insertCourse(course: Course)

    @Update
    fun updateCourse(course: Course)

    @Delete
    fun deleteCourse(course: Course)

    @Query("SELECT * FROM course_table")
    fun getAllCourses(): List<Course>
}
