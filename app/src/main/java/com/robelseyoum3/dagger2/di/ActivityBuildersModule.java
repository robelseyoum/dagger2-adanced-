package com.robelseyoum3.dagger2.di;

import com.robelseyoum3.dagger2.di.auth.AuthModule;
import com.robelseyoum3.dagger2.di.auth.AuthViewModelsModule;
import com.robelseyoum3.dagger2.di.main.MainFragmentBuildersModule;
import com.robelseyoum3.dagger2.di.main.MainModule;
import com.robelseyoum3.dagger2.di.main.MainViewModelsModule;
import com.robelseyoum3.dagger2.ui.auth.AuthActivity;
import com.robelseyoum3.dagger2.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {


    //here used to create auth activity sub component
    @ContributesAndroidInjector (
        modules = { AuthViewModelsModule.class, AuthModule.class}
        )
    abstract AuthActivity contributeAuthActivity();

    //here used to create main activity sub component
    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class,
                    MainViewModelsModule.class,
                    MainModule.class
            }
    )
    abstract MainActivity contributeMainActivity();
}
