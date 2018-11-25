package com.example.f5onz.applicationbdd

import android.content.ContentValues
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.insert

class CourseDb(private val dbHelper: CourseDbHelper = CourseDbHelper.instance) {
    fun requestCourse() = dbHelper.use {
        select(MobileCourseTable.NAME, MobileCourseTable.ID, MobileCourseTable.TITLE, MobileCourseTable.TIME)
            .parseList(classParser<MobileCourse>())
    }
    fun saveCourse(course: MobileCourse) = dbHelper.use {
        insert(MobileCourseTable.NAME, MobileCourseTable.TITLE to course.title, MobileCourseTable.TIME to course.time)
    }
    fun saveCourses(courseList: List<MobileCourse>) {
        for (c in courseList)
            saveCourse(c)
    }
    fun deleteCourse(id: Int)= dbHelper.use{
        delete(MobileCourseTable.NAME,"_id = {id}", "id" to id)
    }

    fun modifyCourse(id:Int,nom:String,nbHeure:Int)=dbHelper.use{
        val args = ContentValues()
        args.put(MobileCourseTable.TIME,nbHeure)
        args.put(MobileCourseTable.TITLE,nom)
        update(MobileCourseTable.NAME,args,"_id = "+id,null)
    }

}