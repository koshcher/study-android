package dev.rk.uquiz.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question implements Parcelable {
    // to imitate realistic data from db
    private long id;
    private String question;
    private List<String> answers;
    private String rightAnswer;


    // to make ids unique
    private static long counter = 1L;

    public Question(String question, List<String> answers, String rightAnswer) {
        this.id = counter++;
        this.question = question;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
    }

    protected Question(Parcel in) {
        id = in.readLong();
        question = in.readString();
        answers = in.createStringArrayList();
        rightAnswer = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) { return new Question[size]; }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(question);
        parcel.writeStringList(answers);
        parcel.writeString(rightAnswer);
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public Long getId() {
        return id;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }


    public static List<Question> defaultQuizList() {
        List<String> q1answers = new ArrayList<>(Arrays.asList("Nassau", "Buenos Aires", "Yerevan", "Vienna"));
        Question q1 = new Question( "Capital of Austria", q1answers, "Vienna");

        List<String> q2answers = new ArrayList<>(Arrays.asList("Brussels", "Belmopan", "Thimphu", "Porto-Novo"));
        Question q2 = new Question("Capital of Belgium", q2answers, "Brussels");

        List<String> q3answers = new ArrayList<>(Arrays.asList("Bujumbura", "Sofia", "Phnom Penh", "Praia"));
        Question q3 = new Question("Capital of Bulgaria", q3answers, "Sofia");

        List<String> q4answers = new ArrayList<>(Arrays.asList("San Jose", "Yamoussoukro", "Yaounde", "Santiago"));
        Question q4 = new Question("Capital of Chile", q4answers, "Santiago");

        List<String> q5answers = new ArrayList<>(Arrays.asList("Nicosia", "Bogota", "Havana", "Moroni"));
        Question q5 = new Question("Capital of Cuba", q5answers, "Havana");

        List<String> q6answers = new ArrayList<>(Arrays.asList("Malabo", "Cairo", "Asmara", "San Salvador"));
        Question q6 = new Question("Capital of Egypt", q6answers, "Cairo");

        List<String> q7answers = new ArrayList<>(Arrays.asList("Asmara", "Suva", "Tallinn", "Helsinki"));
        Question q7 = new Question("Capital of Estonia", q7answers, "Tallinn");

        List<String> q8answers = new ArrayList<>(Arrays.asList("Tbilisi", "Accra", "Libreville", "Paris"));
        Question q8 = new Question("Capital of Georgia", q8answers, "Tbilisi");

        List<String> q9answers = new ArrayList<>(Arrays.asList("Accra", "Saint George's", "Athens", "Berlin"));
        Question q9 = new Question("Capital of Greece", q9answers, "Athens");

        List<String> q10answers = new ArrayList<>(Arrays.asList("Baghdad", "Budapest", "New Delhi", "Reykjavik"));
        Question q10 = new Question("Capital of Iceland", q10answers, "Reykjavik");

        return new ArrayList<>(Arrays.asList(q1, q2, q3, q4, q5, q6, q7, q8, q9, q10));
    }
}
