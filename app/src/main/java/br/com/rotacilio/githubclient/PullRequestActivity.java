package br.com.rotacilio.githubclient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.com.rotacilio.githubclient.R;
import br.com.rotacilio.githubclient.adapter.RepositoryAdapter;
import br.com.rotacilio.githubclient.common.CallbackRequest;
import br.com.rotacilio.githubclient.common.InternetConnection;
import br.com.rotacilio.githubclient.common.RecyclerViewClick;
import br.com.rotacilio.githubclient.model.ItemList;
import br.com.rotacilio.githubclient.request.PullRequestRequest;

/**
 * Created by rodrigo on 28/02/18.
 */
@EActivity(R.layout.activity_pull_request)
public class PullRequestActivity extends AppCompatActivity implements RecyclerViewClick, CallbackRequest {

    private ItemList repo;
//    private String repo;

    private RepositoryAdapter repositoryAdapter;

    @InstanceState
    @NonConfigurationInstance
    public ArrayList<ItemList> itemListList = new ArrayList<>();;

    @InstanceState
    @NonConfigurationInstance
    public LinearLayoutManager.SavedState savedState;

    private PullRequestRequest pullRequestRequest;
    private ProgressDialog progress;
    private LinearLayoutManager manager;

    @ViewById
    RecyclerView rvRepository;

    @ViewById
    TextView tvqtdClosed;

    @ViewById
    TextView tvqtdOpen;

    @ViewById
    Toolbar my_toolbar;

    @ViewById
    LinearLayout llNoPullRequest;

    @ViewById
    ImageView ivllNoPullRequest;

    @AfterViews
    void initPullRequest(){
        manager = new LinearLayoutManager(this);

        repo = (ItemList) getIntent().getParcelableExtra("itemList");

        my_toolbar.setTitle(repo.getTitle());
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(itemListList.size() == 0){
            retrieveData();
        } else {
            if(savedState != null){
                showQtdPullRequest();
                createAdapterRecyclerView();
                recyclerViewConfigurations();
                rvRepository.getLayoutManager().onRestoreInstanceState(savedState);
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        savedState = (LinearLayoutManager.SavedState) rvRepository.getLayoutManager().onSaveInstanceState();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onItemClick(Object object) {
        ItemList itemList = (ItemList) object;
        Uri uri = Uri.parse(itemList.getUrlPullRequest());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void response(Object object) {

        itemListList.addAll((List<ItemList>)object);
        createAdapterRecyclerView();

        showBackgroundNoPullRequest();
        showQtdPullRequest();
        progress.dismiss();

    }

    @Override
    public void failure() {
        showBackgroundNoPullRequest();
        progress.dismiss();

    }

    private void showQtdPullRequest(){
        tvqtdOpen.setText(((itemListList.size() > 0) ? ""+itemListList.size():""));
    }

    private void showBackgroundNoPullRequest(){
        if(itemListList.size() == 0){
            llNoPullRequest.setVisibility(View.VISIBLE);
            rvRepository.setVisibility(View.GONE);

            if(!InternetConnection.internetAvailable(this)){
                ivllNoPullRequest.setImageResource(R.drawable.sem_conexao);
            }
        }
    }

    private void retrieveData(){

        progress = new ProgressDialog(this);
        progress.setTitle("Aguarde");
        progress.setMessage("Buscando Pull Requests...");
        progress.setCancelable(false);
        progress.show();

        pullRequestRequest = new PullRequestRequest();
        pullRequestRequest.call(repo.getName(), repo.getTitle(), this, this);
        recyclerViewConfigurations();
    }

    private void recyclerViewConfigurations(){
        rvRepository.setLayoutManager(manager);
        rvRepository.addItemDecoration(new DividerItemDecoration(rvRepository.getContext(),
                manager.getOrientation()));
    }

    private void createAdapterRecyclerView(){
        repositoryAdapter = new RepositoryAdapter(this, itemListList, this);
        rvRepository.setAdapter(repositoryAdapter);
    }
}
