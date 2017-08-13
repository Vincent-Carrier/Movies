package com.example.android.popularmovies.mainscreen;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.detailsscreen.DetailsActivity;
import com.example.android.popularmovies.pojos.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popularmovies.pojos.PosterWidth.IMAGE_BASE_URL;
import static com.example.android.popularmovies.pojos.PosterWidth.XLARGE;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

	private List<Movie> movies;
	private Context context;

	MovieAdapter(Context context) {
		this.context = context;
	}

	List<Movie> getMovies() {
		return movies;
	}

	void setMovies(List<Movie> movies) {
		this.movies = movies;
		notifyDataSetChanged();
	}

	static class MovieViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.movie_poster)
		ImageView poster;

		MovieViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

	@Override
	public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.movie_grid_item, parent, false);
		return new MovieViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MovieViewHolder holder, int position) {
//		int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
		Movie movie = movies.get(position);
		Picasso.with(context)
				.load(IMAGE_BASE_URL + XLARGE.width + movie.posterPath)
//				.resize(screenWidth, (int) (screenWidth*1.5))
				.placeholder(R.drawable.poster_placeholder)
				.into(holder.poster);
		holder.poster.setContentDescription(movie.title);
		holder.poster.setOnClickListener((view) ->
				context.startActivity(new Intent(context, DetailsActivity.class).putExtra("movie", movie))
		);
	}

	@Override
	public int getItemCount() {
		return movies != null ? movies.size() : 0;
	}
}
