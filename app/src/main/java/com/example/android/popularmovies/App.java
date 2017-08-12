package com.example.android.popularmovies;

import android.app.Application;

import com.example.android.popularmovies.di.DaggerNetComponent;
import com.example.android.popularmovies.di.NetComponent;


public class App extends Application {

	private NetComponent netComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		netComponent = DaggerNetComponent.create();
	}

	public NetComponent getNetComponent() {
		return netComponent;
	}
}
