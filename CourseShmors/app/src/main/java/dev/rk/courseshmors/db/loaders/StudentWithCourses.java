package dev.rk.courseshmors.db.loaders;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import dev.rk.courseshmors.db.models.Course;
import dev.rk.courseshmors.db.models.Enrollment;
import dev.rk.courseshmors.db.models.Student;

public class StudentWithCourses {
    @Embedded
    public Student student;

    @Relation(
            associateBy = @Junction(
                    value = Enrollment.class,
                    parentColumn = "student_id",
                    entityColumn = "course_id"
            ),
            parentColumn = "id",
            entityColumn = "id",
            entity = Course.class
    )
    public List<Course> courses;
}
