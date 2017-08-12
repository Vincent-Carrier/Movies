package com.example.android.popularmovies.mainscreen;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.pojos.SortingMethod;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.movie_grid) RecyclerView movieGrid;
	private MainViewModel viewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		movieGrid.setHasFixedSize(true);
		movieGrid.setLayoutManager(new GridLayoutManager(this, 2));
		movieGrid.setAdapter(new MovieAdapter(this));

		viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
		viewModel.requestMovies().subscribe(theMovieDbResponse ->
				// TODO: Make sure the app doesn't crash without a connection
				((MovieAdapter) movieGrid.getAdapter()).setMovies(theMovieDbResponse.movies)
		);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem sortingMethodMenu = menu.findItem(R.id.change_sorting_method);
		sortingMethodMenu.setTitle(getString(R.string.sorted_by) + " : "
				+ viewModel.getSortingMethod().toString().replace('_', ' '));
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.change_sorting_method:
				if (viewModel.getSortingMethod() == SortingMethod.popular) {
					viewModel.setSortingMethod(SortingMethod.top_rated);
				} else { viewModel.setSortingMethod(SortingMethod.popular); }
				item.setTitle(getString(R.string.sorted_by) + " : "
						+ viewModel.getSortingMethod().toString().replace('_', ' '));
				viewModel.requestMovies().subscribe(theMovieDbResponse ->
						((MovieAdapter) movieGrid.getAdapter()).setMovies(theMovieDbResponse.movies)
				);
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// TODO: Save and restore scroll position
	}
}
