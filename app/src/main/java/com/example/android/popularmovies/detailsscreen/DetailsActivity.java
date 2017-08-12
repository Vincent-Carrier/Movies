package com.example.android.popularmovies.detailsscreen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.pojos.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popularmovies.pojos.PosterWidth.IMAGE_BASE_URL;
import static com.example.android.popularmovies.pojos.PosterWidth.MEDIUM;

public class DetailsActivity extends AppCompatActivity {

	@BindView(R.id.poster_detail) ImageView poster;
	@BindView(R.id.title_detail) TextView title;
	@BindView(R.id.year) TextView year;
	@BindView(R.id.duration) TextView duration;
	@BindView(R.id.vote_average) TextView voteAverage;
	@BindView(R.id.synopsis) TextView synopsis;

	@SuppressLint("SetTextI18n")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		ButterKnife.bind(this);
		Movie movie = getIntent().getParcelableExtra("movie");
		Picasso.with(this)
				.load(IMAGE_BASE_URL + MEDIUM.width + movie.posterPath)
				.placeholder(R.drawable.placeholder)
				.into(poster);
		title.setText(movie.title);
		year.setText(movie.releaseDate.substring(0, 4));
//		duration.setText();
		voteAverage.setText(movie.voteAverage.toString() + "/10");
		synopsis.setText(movie.overview);
	}
}
