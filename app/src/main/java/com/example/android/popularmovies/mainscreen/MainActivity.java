package com.example.android.popularmovies.mainscreen;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
		movieGrid.setItemViewCacheSize(20);
		movieGrid.setDrawingCacheEnabled(true);
		movieGrid.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
		movieGrid.setLayoutManager(new GridLayoutManager(this, 2));
		movieGrid.setAdapter(new MovieAdapter(this));

		viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
		executeMoviesRequest();
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
				executeMoviesRequest();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void executeMoviesRequest() {
		viewModel.requestMovies().subscribe(theMovieDbResponse ->
				// TODO: Make sure the app doesn't crash when offline
				((MovieAdapter) movieGrid.getAdapter()).setMovies(theMovieDbResponse.movies)
		);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// TODO: Save and restore scroll position
	}
}
