package com.robelseyoum3.dagger2.di.main;


import androidx.lifecycle.ViewModel;

import com.robelseyoum3.dagger2.di.ViewModelKey;
import com.robelseyoum3.dagger2.ui.auth.AuthViewModel;
import com.robelseyoum3.dagger2.ui.main.posts.PostsViewModel;
import com.robelseyoum3.dagger2.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds //multi binding
    @IntoMap //mapping to particular key
    @ViewModelKey(ProfileViewModel.class) //this dependency is mapping to this
    public abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);


    @Binds //multi binding
    @IntoMap //mapping to particular key
    @ViewModelKey(PostsViewModel.class) //this dependency is mapping to this
    public abstract ViewModel bindPostsViewModel(PostsViewModel postsViewModel);
}
