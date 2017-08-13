package com.example.android.popularmovies.mainscreen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
		viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			movieGrid.setLayoutManager(new GridLayoutManager(this, 2));
		} else { movieGrid.setLayoutManager(new GridLayoutManager(this, 4)); }
		movieGrid.setHasFixedSize(true);
		movieGrid.setItemViewCacheSize(20);
		movieGrid.setDrawingCacheEnabled(true);
		movieGrid.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
		movieGrid.setAdapter(new MovieAdapter(this));

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
				// TODO: Fix to account for failed/interrupted requests. Cycle through sorting methods
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
		viewModel.requestMovies().subscribe(
				theMovieDbResponse -> ((MovieAdapter) movieGrid.getAdapter()).setMovies(theMovieDbResponse.movies),
				error -> Log.e("MainActivity", "Movies request failed")
				// TODO: Implement a proper error
		);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// TODO: Save and restore scroll position
	}
}
