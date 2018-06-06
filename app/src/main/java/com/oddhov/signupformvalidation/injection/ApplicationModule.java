package com.oddhov.signupformvalidation.injection;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

	private final Application application;

	public ApplicationModule(Application application) {
		this.application = application;
	}

	@Provides
	Context context() {
		return application;
	}

	@Provides
	Application application() {
		return application;
	}
}
