package com.robelseyoum3.dagger2.di;

import com.robelseyoum3.dagger2.di.auth.AuthViewModelsModule;
import com.robelseyoum3.dagger2.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector (
        modules = { AuthViewModelsModule.class }
        )
    abstract AuthActivity contributeAuthActivity();

}
