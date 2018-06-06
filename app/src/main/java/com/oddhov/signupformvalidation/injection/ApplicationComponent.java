package com.oddhov.signupformvalidation.injection;

import com.oddhov.signupformvalidation.App;
import com.oddhov.signupformvalidation.tools.view.EmailValidationEditText;
import com.oddhov.signupformvalidation.tools.view.PasswordValidationTextInputEditText;
import com.oddhov.signupformvalidation.ui.MainActivity;
import com.oddhov.signupformvalidation.ui.MainActivityComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
	MainActivityComponent plus(MainActivityComponent.MainActivityModule module);

	void inject(App app);

    void inject(EmailValidationEditText emailValidationEditText);

	void inject(PasswordValidationTextInputEditText passwordValidationTextInputEditText);
}
