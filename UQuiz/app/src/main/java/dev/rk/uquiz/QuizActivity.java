package dev.rk.uquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import dev.rk.uquiz.models.Answer;
import dev.rk.uquiz.models.Question;

public class QuizActivity extends AppCompatActivity {
    List<Question> questions;
    List<Answer> answers;
    int currentQuestion;

    // elements
    TextView questionText;
    RadioGroup answersGroup;
    Button previousQuestionBtn;
    Button nextQuestionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        answersGroup = findViewById(R.id.answersGroup);
        previousQuestionBtn = findViewById(R.id.previousQuestionBtn);
        nextQuestionBtn = findViewById(R.id.nextQuestionBtn);

        Intent intent = getIntent();
        questions = intent.getParcelableArrayListExtra("questions");
        answers = new ArrayList<>(questions.size());

        previousQuestionBtn.setOnClickListener(v -> {
            if(currentQuestion <= 0) return;
            currentQuestion -= 1;
            runQuestion();
        });

        nextQuestionBtn.setOnClickListener(v -> {
            if(currentQuestion != questions.size() - 1) {
                currentQuestion += 1;
                runQuestion();
                return;
            }

            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent.putParcelableArrayListExtra("answers", (ArrayList<Answer>) answers);
            setResult(RESULT_OK, mainIntent);
            finish();
        });

        runQuestion();
    }

    private void runQuestion() {
        Question question = questions.get(currentQuestion);
        questionText.setText(question.getQuestion());

        Optional<Answer> currentAnswer = answers.stream()
                .filter(a -> Objects.equals(a.getQuestionId(), question.getId()))
                .findFirst();

        previousQuestionBtn.setVisibility(currentQuestion == 0 ? View.GONE : View.VISIBLE);
        nextQuestionBtn.setText(currentQuestion == questions.size() - 1 ? "finish" : "next");
        nextQuestionBtn.setEnabled(currentAnswer.isPresent());

        answersGroup.removeAllViews();
        for (String answer: question.getAnswers()) {
            RadioButton radioButton = new RadioButton(this);
            int id = View.generateViewId();
            radioButton.setId(id);

            if(currentAnswer.isPresent()) {
                boolean isRight = Objects.equals(currentAnswer.get().getUserAnswer(), answer);
                if(isRight) answersGroup.check(id);
                radioButton.setChecked(isRight);
            }

            radioButton.setText(answer);
            radioButton.setPadding(0, 30, 0, 30);

            radioButton.setOnClickListener(v -> {
                nextQuestionBtn.setEnabled(true);

                Optional<Answer> userAnswer = answers.stream()
                        .filter(a -> Objects.equals(a.getQuestionId(), question.getId()))
                        .findFirst();

                if(!userAnswer.isPresent()) {
                    answers.add(new Answer(question.getId(), answer));
                    return;
                }

                Answer a = userAnswer.get();
                a.setUserAnswer(answer);
            });
            answersGroup.addView(radioButton);
        }
    }
}