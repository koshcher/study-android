package dev.rk.courseshmors.db.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Student.class, parentColumns = "id",
                childColumns = "student_id", onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = Course.class, parentColumns = "id",
                childColumns = "course_id", onDelete = ForeignKey.CASCADE
        ),
    },
    primaryKeys = {"student_id", "course_id"}
)
public class Enrollment {
    @ColumnInfo(name = "student_id")
    public int studentId;
    @ColumnInfo(name = "course_id")
    public int courseId;

    public Enrollment(int studentId, int courseId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }
}
