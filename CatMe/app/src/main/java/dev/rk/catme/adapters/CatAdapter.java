package dev.rk.catme.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.rk.catme.R;
import dev.rk.catme.models.Cat;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    private final List<Cat> cats;
    private final Context ctx;

    public CatAdapter(Context context, List<Cat> cats) {
        this.cats = cats;
        this.ctx = context;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.ctx).inflate(R.layout.cat_card, parent, false);
        return new CatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = cats.get(position);
        holder.ageText.setText(String.valueOf(cat.getAge()));
        holder.nameText.setText(String.valueOf(cat.getName()));
    }

    @Override
    public int getItemCount() { return cats.size(); }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView ageText;
        TextView nameText;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            ageText = itemView.findViewById(R.id.age);
            nameText = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Cat cat = cats.get(position);

            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle("Remove")
                   .setMessage("Are you sure you want to remove: " + cat.getName() + "?")
                   .setPositiveButton("Yes", (dialogInterface, i) -> {
                        cats.remove(position);
                        notifyDataSetChanged();
                   })
                   .setNegativeButton("No", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                   })
                    .create().show();
        }
    }
}
