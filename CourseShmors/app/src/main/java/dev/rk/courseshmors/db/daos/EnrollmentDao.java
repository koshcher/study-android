package dev.rk.courseshmors.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;

import dev.rk.courseshmors.db.models.Course;
import dev.rk.courseshmors.db.models.Enrollment;

@Dao
public abstract class EnrollmentDao {

    @Insert
    public abstract void insert(Enrollment enrollment);
}
