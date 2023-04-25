package dev.rk.tasker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import dev.rk.tasker.R;
import dev.rk.tasker.models.Task;

public class TaskAdapter extends ArrayAdapter<Task> {
    private Context context;
    private List<Task> tasks;
    private LayoutInflater inflater;
    private int layoutId;

    public TaskAdapter(
            @NonNull Context context, int resource, @NonNull List<Task> objects
    ) {
        super(context, resource, objects);

        this.context = context;
        this.tasks = objects;
        inflater = LayoutInflater.from(context);
        layoutId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Task task = tasks.get(position);
        View view = convertView == null
                ? inflater.inflate(this.layoutId, parent, false)
                : convertView;

        TextView headerText = view.findViewById(R.id.headerText);
        TextView contentText = view.findViewById(R.id.contentText);

        headerText.setText(task.getHeader());
        contentText.setText(task.getContent());

        return  view;
    }


}
