package dev.rk.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private final List<Quiz> quizList = Quiz.defaultQuizList();
    private List<String> currentAnswers;

    public int bestScore = 0;
    public int score = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        runMain(); // no score = first enter
    }

    private void runMain() {
        setContentView(R.layout.activity_main);

        // load elements
        TextView bestScoreText = findViewById(R.id.bestScore);
        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView scoreText = findViewById(R.id.score);

        if(score == -1) {
            scoreText.setVisibility(View.GONE);
            scoreLabel.setVisibility(View.GONE);
        } else {
            scoreText.setText(String.valueOf(score));
        }

        bestScoreText.setText(String.valueOf(bestScore));

        findViewById(R.id.exitBtn).setOnClickListener(v -> {
            finish();
            System.exit(0);
        });

        findViewById(R.id.startBtn).setOnClickListener(v -> {
            currentAnswers = new ArrayList<>(quizList.size());
            for (int i = 0; i < quizList.size(); i++) currentAnswers.add("");

            runQuiz(0);
        });
    }

    private void runQuiz(int quizIndex) {
        Quiz quiz = quizList.get(quizIndex);
        String currentAnswer = currentAnswers.get(quizIndex); // "" or "Actual answer"

        // load reusable elements
        setContentView(R.layout.quiz);
        Button nextQuizBtn = findViewById(R.id.nextQuizBtn);
        Button findOutScoreBtn = findViewById(R.id.findOutScoreBtn);

        ((TextView)findViewById(R.id.question)).setText(quiz.question);
        Picasso.get().load(quiz.image).into((ImageView) findViewById(R.id.quizImage));

        findViewById(R.id.exitBtn).setOnClickListener(v -> {
            finish();
            System.exit(0);
        });

        if(quizIndex == quizList.size() - 1) {
            nextQuizBtn.setVisibility(View.GONE);
            findOutScoreBtn.setVisibility(View.VISIBLE);
            findOutScoreBtn.setOnClickListener(v -> {

                int rightCount = 0;
                for (int i = 0; i < currentAnswers.size(); i++) {
                    if(Objects.equals(currentAnswers.get(i), quizList.get(i).rightAnswer)) {
                        ++rightCount;
                    }
                }
                score = (int) Math.round(rightCount * 100.0 / currentAnswers.size());
                if(score > bestScore) bestScore = score;

                runMain();
            });
        } else {
            nextQuizBtn.setOnClickListener(btnView -> runQuiz(quizIndex + 1));
        }

        if(!currentAnswer.isEmpty()) {
            nextQuizBtn.setEnabled(true);
            findOutScoreBtn.setEnabled(true);
        }

        initAnswersRadioGroup(
                findViewById(R.id.answers),
                quiz, quizIndex, currentAnswer,
                quizIndex == quizList.size() - 1 ? findOutScoreBtn : nextQuizBtn
        );

        initPreviousQuizBtn(quizIndex);
    }

    private void initAnswersRadioGroup(RadioGroup radioGroup, Quiz quiz, int quizIndex, String currentAnswer, Button actionBtn) {
        for (String answer: quiz.answers) {
            RadioButton radioButton = new RadioButton(this);
            int id = View.generateViewId();
            radioButton.setId(id);

            if(Objects.equals(currentAnswers.get(quizIndex), answer)) {
                radioGroup.check(id);
            }

            radioButton.setChecked(Objects.equals(currentAnswer, answer));
            radioButton.setText(answer);
            radioButton.setPadding(0, 30, 0, 30);
            radioGroup.addView(radioButton);

            radioButton.setOnClickListener(v -> {
                currentAnswers.set(quizIndex, answer);
                if(!actionBtn.isEnabled()) actionBtn.setEnabled(true);
            });
        }
    }

    private void  initPreviousQuizBtn(int quizIndex) {
        if(quizIndex != 0) {
            Button previousQuizBtn = findViewById(R.id.previousQuizBtn);
            previousQuizBtn.setVisibility(View.VISIBLE);
            previousQuizBtn.setOnClickListener(v -> runQuiz(quizIndex - 1));
        }
    }



}