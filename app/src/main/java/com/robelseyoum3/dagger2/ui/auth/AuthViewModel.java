package com.robelseyoum3.dagger2.ui.auth;

import android.service.autofill.AutofillService;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.robelseyoum3.dagger2.model.User;
import com.robelseyoum3.dagger2.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: viewmodel is working...");
    }

    public LiveData<AuthResource<User>> observeUser(){
        return authUser;
    }

    public void authenticateWithId(int userId){
        authUser.setValue(AuthResource.loading((User)null));

      final LiveData<AuthResource<User>> source = LiveDataReactiveStreams.fromPublisher(
              authApi.getUser(userId)

                      //instead of calling onError (error happens)
              .onErrorReturn(new Function<Throwable, User>() {
                  @Override
                  public User apply(Throwable throwable) throws Exception {
                      User errorUser = new User();
                      errorUser.setId(-1);
                      return errorUser;
                  }
              })
              .map(new Function<User, AuthResource<User>>() {
                  @Override
                  public AuthResource<User> apply(User user) throws Exception {
                      if(user.getId() == -1){
                          return AuthResource.error("Could not authenticate", (User)null);
                      }
                      return AuthResource.authenticated(user);
                  }
              })
              .subscribeOn(Schedulers.io())
      );

      authUser.addSource(source, new Observer<AuthResource<User>>() {
          @Override
          public void onChanged(AuthResource<User> userAuthResource) {
              authUser.setValue(userAuthResource);
              authUser.removeSource(source);
          }
      });

    }


}
