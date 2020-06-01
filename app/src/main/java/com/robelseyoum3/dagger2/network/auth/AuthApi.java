package com.robelseyoum3.dagger2.network.auth;

import com.robelseyoum3.dagger2.model.User;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    // /users/id
    @GET("users/{id}")
    Flowable<User> getUser(
            @Path("id") int id
    );
}
