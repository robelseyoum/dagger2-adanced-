package com.robelseyoum3.dagger2.di;

import androidx.lifecycle.ViewModelProvider;

import com.robelseyoum3.dagger2.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);

    /**
     * It is also possible to do it this way..
     * @param //factory
     * @return
     */
//    @Provides
//    static ViewModelProviderFactory bindFactory(ViewModelProviderFactory factory){
//        return  factory;
//    }

}
