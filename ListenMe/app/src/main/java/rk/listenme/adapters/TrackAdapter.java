package rk.listenme.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.function.Function;

import rk.listenme.ListenActivity;
import rk.listenme.R;
import rk.listenme.models.Track;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {

    private Context context;
    private List<Track> tracks;


    public TrackAdapter(
            Context context, List<Track> tracks
    ) {
        this.context = context;
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.track_list_item, parent, false);
        return new TrackViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        Track track = tracks.get(position);
        holder.titleText.setText(track.title);

        Picasso.get().load(track.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView titleText;

        public TrackViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Context ctx = view.getContext();
            Intent intent = new Intent(ctx, ListenActivity.class);
            intent.putExtra("trackIndex", position);
            ctx.startActivity(intent);
        }
    }
}
