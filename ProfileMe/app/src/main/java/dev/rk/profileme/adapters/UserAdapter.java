package dev.rk.profileme.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dev.rk.profileme.R;
import dev.rk.profileme.fragments.UserListFragment;
import dev.rk.profileme.models.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> users;
    private UserListFragment.UserClickListener clickListener;

    public UserAdapter(List<User> users, UserListFragment.UserClickListener clickListener) {
        this.users = users;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);

        holder.nameText.setText(user.name);
        Picasso.get().load(user.image).into(holder.imageView);

        holder.itemView.setOnClickListener((view -> {
            if(clickListener != null) clickListener.onClick(user);
        }));
    }

    @Override
    public int getItemCount() { return users.size(); }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView nameText;
        private ImageView imageView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.nameText);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
