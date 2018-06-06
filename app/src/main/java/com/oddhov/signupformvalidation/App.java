package com.oddhov.signupformvalidation;

import android.app.Application;
import android.content.Context;

import com.oddhov.signupformvalidation.injection.ApplicationComponent;
import com.oddhov.signupformvalidation.injection.ApplicationModule;
import com.oddhov.signupformvalidation.injection.DaggerApplicationComponent;

public class App extends Application {
	private ApplicationComponent applicationComponent;

	public static App get(Context context) {
		return (App) context.getApplicationContext();
	}

	@Override
	public void onCreate() {
		super.onCreate();

		getApplicationComponent().inject(this);
	}

	public ApplicationComponent getApplicationComponent() {
		if (applicationComponent == null) {
			applicationComponent = DaggerApplicationComponent.builder()
					.applicationModule(new ApplicationModule(this))
					.build();
		}

		return applicationComponent;
	}
}

