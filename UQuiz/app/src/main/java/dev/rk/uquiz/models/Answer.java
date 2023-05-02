package dev.rk.uquiz.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

public class Answer implements Parcelable {
    // to imitate realistic data from db
    private Long questionId;
    private String userAnswer;

    public String getUserAnswer() {
        return userAnswer;
    }

    public Long getQuestionId() { return questionId; }

    public void setQuestionId(Long questionId) { this.questionId = questionId; }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Answer(Long questionId, String userAnswer) {
        this.questionId = questionId;
        this.userAnswer = userAnswer;
    }

    protected Answer(Parcel in) {
        questionId = in.readLong();
        userAnswer = in.readString();
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) { return new Answer(in); }

        @Override
        public Answer[] newArray(int size) { return new Answer[size]; }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(questionId);
        parcel.writeString(userAnswer);
    }

}
