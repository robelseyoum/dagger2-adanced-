package com.robelseyoum3.dagger2.di.main;


import com.robelseyoum3.dagger2.network.main.MainApi;
import com.robelseyoum3.dagger2.ui.main.posts.PostsRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class MainModule {

    @Provides
    static MainApi provideAuthApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }

    @Provides
    static PostsRecyclerAdapter provideAdapter(){
        return new PostsRecyclerAdapter();
    }
}
