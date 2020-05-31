package com.robelseyoum3.dagger2.di.auth;

import androidx.lifecycle.ViewModel;

import com.robelseyoum3.dagger2.di.ViewModelKey;
import com.robelseyoum3.dagger2.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds //multi binding
    @IntoMap //mapping to particular key
    @ViewModelKey(AuthViewModel.class) //this dependency is mapping to this
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
