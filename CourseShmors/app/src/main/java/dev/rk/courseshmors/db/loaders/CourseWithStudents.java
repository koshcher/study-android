package dev.rk.courseshmors.db.loaders;


import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import dev.rk.courseshmors.db.models.Course;
import dev.rk.courseshmors.db.models.Enrollment;
import dev.rk.courseshmors.db.models.Student;

public class CourseWithStudents {

    @Embedded
    public Course course;

    @Relation(
            parentColumn = "id",
            entityColumn = "id",
            entity = Student.class,
            associateBy = @Junction(
                    value = Enrollment.class,
                    parentColumn = "course_id", entityColumn = "student_id"
            )
    )
    public List<Student> students;
}
