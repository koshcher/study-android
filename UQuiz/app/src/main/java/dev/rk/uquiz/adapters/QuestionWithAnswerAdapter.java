package dev.rk.uquiz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import dev.rk.uquiz.R;
import dev.rk.uquiz.models.Answer;
import dev.rk.uquiz.models.Question;

public class QuestionWithAnswerAdapter extends ArrayAdapter<Answer> {
    private Context context;
    private LayoutInflater inflater;
    private int layoutId;

    private List<Answer> answers;
    private List<Question> questions;


    public QuestionWithAnswerAdapter(
            @NonNull Context context, int resource, @NonNull List<Answer> objects, List<Question> questions
    ) {
        super(context, resource, objects);
        this.context = context;
        inflater = LayoutInflater.from(context);
        layoutId = resource;

        this.questions = questions;
        this.answers = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Answer answer = answers.get(position);
        Question question = questions.stream()
                .filter(q -> Objects.equals(q.getId(), answer.getQuestionId()))
                .findFirst().get();

        View view = convertView == null
                ? inflater.inflate(this.layoutId, parent, false)
                : convertView;

        TextView questionText = view.findViewById(R.id.questionText);
        TextView rightAnswerText = view.findViewById(R.id.rightAnswerText);
        TextView userAnswerText = view.findViewById(R.id.userAnswerText);

        questionText.setText(question.getQuestion());
        rightAnswerText.setText(question.getRightAnswer());
        userAnswerText.setText(answer.getUserAnswer());

        return  view;
    }
}
