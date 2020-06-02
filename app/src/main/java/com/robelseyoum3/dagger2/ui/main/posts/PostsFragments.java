package com.robelseyoum3.dagger2.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.robelseyoum3.dagger2.R;
import com.robelseyoum3.dagger2.model.Post;
import com.robelseyoum3.dagger2.ui.main.Resource;
import com.robelseyoum3.dagger2.util.VerticalSpaceItemDecoration;
import com.robelseyoum3.dagger2.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragments  extends DaggerFragment {
    private static final String TAG = "PostsFragments";

    private PostsViewModel viewModel;
    private RecyclerView recyclerView;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    PostsRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        viewModel = new ViewModelProvider(this, providerFactory).get(PostsViewModel.class);
        initRecyclerView();
        subscribeObservers();
    }

    private void subscribeObservers(){
//        viewModel.observePosts().removeObserver((Observer<? super Resource<List<Post>>>) getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if(listResource != null){
                    Log.d(TAG, "onChanged: "+listResource.data);
                    switch (listResource.status){
                        case LOADING:{
                            Log.d(TAG, "onChanged: LOADING...");
                            break;
                        }
                        case SUCCESS:{
                            Log.d(TAG, "onChanged: SUCCESS got posts");
                            adapter.setPosts(listResource.data);
                            break;
                        }
                        case ERROR:{
                            Log.d(TAG, "onChanged: ERROR..."+listResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpaceItemDecoration itemDecoration = new VerticalSpaceItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
    }


}
