package dev.rk.uquiz;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dev.rk.uquiz.adapters.QuestionWithAnswerAdapter;
import dev.rk.uquiz.models.Answer;
import dev.rk.uquiz.models.Question;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> launcher;
    List<Question> questions = Question.defaultQuizList();
    List<Answer> answers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView quizList = findViewById(R.id.quizList);
        Button startQuizBtn = findViewById(R.id.startQuizBtn);

        ArrayAdapter<String> startQuestionsAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                questions.stream().map(Question::getQuestion).collect(Collectors.toList())
        );

        quizList.setAdapter(startQuestionsAdapter);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() != Activity.RESULT_OK) return;
                    Intent data = result.getData();
                    if (data == null) return;
                    answers = data.getParcelableArrayListExtra("answers");

                    QuestionWithAnswerAdapter questionWithAnswerAdapter = new QuestionWithAnswerAdapter(
                            this, R.layout.question_with_answer, answers, questions
                    );
                    quizList.setAdapter(questionWithAnswerAdapter);
                }
        );


        startQuizBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putParcelableArrayListExtra(
                    "questions", (ArrayList<Question>) questions
            );
            launcher.launch(intent);
        });
    }


}