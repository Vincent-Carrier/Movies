package com.example.android.popularmovies;

import android.app.Application;

import com.example.android.popularmovies.di.DaggerNetComponent;
import com.example.android.popularmovies.di.NetComponent;

import timber.log.Timber;
import timber.log.Timber.DebugTree;


public class App extends Application {

	private NetComponent netComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		netComponent = DaggerNetComponent.create();
		if (BuildConfig.DEBUG) {
			Timber.plant(new DebugTree());
		}
	}

	public NetComponent getNetComponent() {
		return netComponent;
	}
}
