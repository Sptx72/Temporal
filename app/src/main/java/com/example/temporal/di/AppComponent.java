package com.example.temporal.di;

import com.example.temporal.presentation.auth.AuthActivity;
import com.example.temporal.presentation.auth.LoginFragment;
import com.example.temporal.presentation.auth.RegisterFragment;
import com.example.temporal.presentation.home.HomeActivity;
import com.example.temporal.presentation.menu_options.MenuOptionsFragment;
import com.example.temporal.presentation.profile.ProfileDataFragment;
import com.example.temporal.presentation.profile.ProfileFormFragment;
import com.example.temporal.presentation.profile.ProfileFragment;
import com.example.temporal.presentation.publications.PublicationsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MenuOptionsFragment menuOptionsFragment);
    void inject(ProfileFormFragment profileFormFragment);
    void inject(ProfileDataFragment profileDataFragment);
    void inject(ProfileFragment profileFragment);
    void inject(HomeActivity homeActivity);
    void inject(LoginFragment loginFragment);
    void inject(RegisterFragment registerFragment);
    void inject(AuthActivity authActivity);
    void inject(PublicationsFragment publicationsFragment);
}
