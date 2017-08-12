package com.example.android.popularmovies.di;

import com.example.android.popularmovies.mainscreen.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetModule.class})
public interface NetComponent {
	void inject(MainViewModel viewModel);
}
