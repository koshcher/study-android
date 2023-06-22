package dev.rk.courseshmors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import java.util.List;

import dev.rk.courseshmors.db.CourseShmorsDb;
import dev.rk.courseshmors.db.loaders.CourseWithStudents;
import dev.rk.courseshmors.db.loaders.StudentWithCourses;
import dev.rk.courseshmors.db.models.Course;
import dev.rk.courseshmors.db.models.Enrollment;
import dev.rk.courseshmors.db.models.Student;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(() -> {
            CourseShmorsDb db = Room.databaseBuilder(getApplicationContext(), CourseShmorsDb.class, "neotcourseshomorsdb").build();

            Student roma = new Student("roma", 17);
            Student maria = new Student("maria", 17);
            Student vitaliy = new Student("vitaliy", 1000);

            db.student().insert(roma);
            db.student().insert(maria);

            List<Student> students = db.student().getAll();

            db.student().replace(students.get(0), vitaliy);

            students = db.student().getAll();

            db.course().insert(new Course("c++", "bad hell"));
            db.course().insert(new Course("zig", "nice hell"));

            List<Course> courses = db.course().getAll();

            db.enrollment().insert(new Enrollment(students.get(0).id, courses.get(0).id));
            db.enrollment().insert(new Enrollment(students.get(0).id, courses.get(1).id));
            db.enrollment().insert(new Enrollment(students.get(1).id, courses.get(1).id));

            List<CourseWithStudents> coursesWithStudents = db.course().courseWithStudentsList();
            List<StudentWithCourses> studentsWithCourses = db.student().studentWithCoursesList();
        }).start();
    }
}