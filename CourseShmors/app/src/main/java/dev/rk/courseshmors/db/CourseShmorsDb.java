package dev.rk.courseshmors.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dev.rk.courseshmors.db.daos.CourseDao;
import dev.rk.courseshmors.db.daos.EnrollmentDao;
import dev.rk.courseshmors.db.daos.StudentDao;
import dev.rk.courseshmors.db.models.Course;
import dev.rk.courseshmors.db.models.Enrollment;
import dev.rk.courseshmors.db.models.Student;


@Database(version = 1, entities = {Student.class, Course.class, Enrollment.class})
public abstract class CourseShmorsDb extends RoomDatabase {

    public abstract StudentDao student();
    public abstract CourseDao course();
    public abstract EnrollmentDao enrollment();
}
