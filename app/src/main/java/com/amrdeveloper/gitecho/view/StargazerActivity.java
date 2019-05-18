package com.amrdeveloper.gitecho.view;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.adapter.StarRecyclerAdapter;
import com.amrdeveloper.gitecho.databinding.ActivityStargazerBinding;
import com.amrdeveloper.gitecho.model.contract.StarsContract;
import com.amrdeveloper.gitecho.model.network.stars.StarsViewModel;
import com.amrdeveloper.gitecho.object.Stargazer;
import com.amrdeveloper.gitecho.presenter.StarsPresenter;

public class StargazerActivity extends AppCompatActivity implements StarsContract.View {

    private String username;
    private String repositoryName;
    private StarsPresenter presenter;
    private ActivityStargazerBinding binding;
    private StarRecyclerAdapter starRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_stargazer);
        setRecyclerViewSettings();

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        repositoryName = intent.getStringExtra("repositoryName");

        StarsViewModel.setRequestData(username,repositoryName);
        StarsViewModel itemViewModel = ViewModelProviders.of(this).get(StarsViewModel.class);

        presenter = new StarsPresenter(this,itemViewModel,this);
        presenter.startLoadingData();
    }

    private void setRecyclerViewSettings() {
        starRecyclerAdapter = new StarRecyclerAdapter(this);
        binding.repoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.repoRecyclerView.setHasFixedSize(true);
        binding.repoRecyclerView.setAdapter(starRecyclerAdapter);
    }

    @Override
    public void onLoadFinish(PagedList<Stargazer> stargazers) {
        starRecyclerAdapter.submitList(stargazers);
    }

    @Override
    public void showProgressBar() {
        binding.loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
       binding.loadingIndicator.setVisibility(View.GONE);
    }
}