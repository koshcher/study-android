package dev.rk.employeesmanager.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.EmbossMaskFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dev.rk.employeesmanager.R;
import dev.rk.employeesmanager.models.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    Context context;
    List<Employee> employees;

    public EmployeeAdapter(Context context, List<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.employee_cad, parent, false);
        return new EmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.ageText.setText(String.valueOf(employee.getAge()).concat(" y.o."));
        holder.nameText.setText(employee.getName());

        Picasso.get().load(employee.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() { return employees.size(); }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView ageText;
        TextView nameText;
        ImageView imageView;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            ageText = itemView.findViewById(R.id.ageText);
            nameText = itemView.findViewById(R.id.nameText);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
