package dev.rk.courseshmors.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import dev.rk.courseshmors.db.loaders.CourseWithStudents;
import dev.rk.courseshmors.db.loaders.StudentWithCourses;
import dev.rk.courseshmors.db.models.Course;
import dev.rk.courseshmors.db.models.Student;

@Dao
public abstract class StudentDao {

    @Insert
    public abstract void insert(Student student);

    @Delete
    public abstract void delete(Student student);

    @Update
    public abstract void update(Student student);

    @Query("select * from student")
    public abstract List<Student> getAll();

    @Transaction
    public void replace(Student student, Student newStudent) {
        delete(student);
        insert(newStudent);
    }

    @Transaction
    @Query("select * from student")
    public abstract List<StudentWithCourses> studentWithCoursesList();
}
