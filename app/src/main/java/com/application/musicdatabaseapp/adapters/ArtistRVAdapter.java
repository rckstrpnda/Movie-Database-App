package com.application.musicdatabaseapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.musicdatabaseapp.EditMovieActivity;
import com.application.musicdatabaseapp.R;
import com.application.musicdatabaseapp.models.ArtistModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ArtistRVAdapter extends RecyclerView.Adapter<ArtistRVAdapter.ViewHolder> {
    
    private Context context;
    private ArrayList<ArtistModel> artistModelArrayList;

    public ArtistRVAdapter(Context context, ArrayList<ArtistModel> artistModelArrayList) {
        this.context = context;
        this.artistModelArrayList = artistModelArrayList;
    }

    @NonNull
    @Override
    public ArtistRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.artist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistRVAdapter.ViewHolder holder, int position) {
        holder.bind(artistModelArrayList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return artistModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView artistId, artistName, artistAge, artistSex, artistLanguage, songsComposed, editArtist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            artistId = itemView.findViewById(R.id.movId);
            artistName = itemView.findViewById(R.id.movName);
            artistAge = itemView.findViewById(R.id.act);
            artistSex = itemView.findViewById(R.id.acc);
            artistLanguage = itemView.findViewById(R.id.yor);
            songsComposed = itemView.findViewById(R.id.dir);
            editArtist = itemView.findViewById(R.id.editMov);
        }

        public void bind(ArtistModel artistModel, int position) {
            artistId.setText("Movie id: "+artistModel.getMov_id());
            artistName.setText("Movie Name: "+artistModel.getMovName());
            artistAge.setText("Lead Actor: "+artistModel.getAct());
            artistSex.setText("Actress: "+artistModel.getAcc());
            artistLanguage.setText("Year of release: "+artistModel.getYor());
            songsComposed.setText("Director Name: "+artistModel.getDir());

            editArtist.setOnClickListener(view -> {
                Gson gson = new Gson();
                String data = gson.toJson(artistModel, ArtistModel.class);

                Intent intent = new Intent(context, EditMovieActivity.class);
                intent.putExtra("artist", data);
                context.startActivity(intent);
            });
        }
    }
}
