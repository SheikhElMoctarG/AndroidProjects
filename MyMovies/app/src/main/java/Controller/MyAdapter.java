package Controller;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sheikh.mymovies.InfoActivity;
import com.sheikh.mymovies.R;

import java.util.List;

import Model.Movie;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Movie> moviesList;
    private Context context;

    public MyAdapter(Context context,List<Movie> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getDescription());
        holder.year.setText(movie.getYear());
        holder.cover.setImageResource(movie.getCover());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView cover;
        private TextView title,description,year;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            year = itemView.findViewById(R.id.year);
            cover.setOnClickListener(this);
            title.setOnClickListener(this);
            description.setOnClickListener(this);
            year.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Movie movie = moviesList.get(position);
            Intent intent = new Intent(context, InfoActivity.class);
            intent.putExtra("title",movie.getTitle());
            intent.putExtra("desc",movie.getDescription());
            intent.putExtra("year",movie.getYear());
            intent.putExtra("cover",movie.getCover());
            context.startActivity(intent);
        }
    }
}
