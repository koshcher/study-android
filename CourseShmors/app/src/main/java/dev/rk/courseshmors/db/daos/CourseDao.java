package dev.rk.courseshmors.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import dev.rk.courseshmors.db.loaders.CourseWithStudents;
import dev.rk.courseshmors.db.models.Course;

@Dao
public abstract class CourseDao {

    @Insert
    public abstract void insert(Course course);

    @Delete
    public abstract void delete(Course course);

    @Update
    public abstract void update(Course course);

    @Query("select * from course")
    public abstract List<Course> getAll();

    @Transaction
    @Query("select * from course")
    public abstract List<CourseWithStudents> courseWithStudentsList();
}
