package br.com.rotacilio.githubclient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import br.com.rotacilio.githubclient.common.CallbackRequest;
import br.com.rotacilio.githubclient.common.InternetConnection;
import br.com.rotacilio.githubclient.common.RecyclerViewClick;
import br.com.rotacilio.githubclient.common.RecyclerViewLoadMoreItems;
import br.com.rotacilio.githubclient.model.ItemList;
import br.com.rotacilio.githubclient.adapter.RepositoryAdapter;
import br.com.rotacilio.githubclient.request.RepositoryRequest;

import static android.widget.Toast.makeText;

/**
 * Created by rodrigo on 28/02/18.
 */
@EActivity(R.layout.activity_repository)
public class MainActivity extends AppCompatActivity implements RecyclerViewClick, CallbackRequest, RecyclerViewLoadMoreItems {

    private RepositoryAdapter repositoryAdapter;


    @InstanceState
    @NonConfigurationInstance
    ArrayList<ItemList> itemListList = new ArrayList<>();;

    private RepositoryRequest repositoryRequest;
    private LinearLayoutManager manager;

    @InstanceState
    @NonConfigurationInstance
    public LinearLayoutManager.SavedState savedState;

    private ProgressDialog progress;
    private int lastVisibleItem;
    private int totalItemCount;
    private boolean loading;

    @ViewById
    RecyclerView rvRepository;

    @AfterViews
    void initRepository(){
        manager = new LinearLayoutManager(this);

        if(itemListList.size() == 0){
            retrieveData();
        } else {
            if(savedState != null){
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
    public void onItemClick(Object object) {
        Log.e("aqui_error", "teste");
        ItemList itemList = (ItemList) object;
        Intent it = new Intent(MainActivity.this, PullRequestActivity_.class);
        it.putExtra("itemList", itemList);
        startActivity(it);
    }

    @Override
    public void response(Object object) {

        if(!loading){
            itemListList.addAll((List<ItemList>)object);
            createAdapterRecyclerView();
            progress.dismiss();
        } else {
            this.onLoadMoreItems(object);
        }
    }

    @Override
    public void failure() {
        progress.dismiss();
    }


    @Override
    public void onLoadMoreItems(Object object) {
        itemListList.remove(itemListList.size()-1);
        repositoryAdapter.notifyItemRemoved(itemListList.size());

        List<ItemList> itemLists = (List<ItemList>)object;
        itemListList.addAll(itemLists);

        repositoryAdapter.notifyItemRangeInserted((itemListList.size()-itemLists.size()),itemLists.size());
        loading = false;
    }


    public void retrieveData(){
        progress = new ProgressDialog(this);
        progress.setTitle("Aguarde");
        progress.setMessage("Carregando repositorios...");
        progress.setCancelable(false);
        progress.show();

        makeRequest("1");
        recyclerViewConfigurations();

    }

    private void makeRequest(String page){
        repositoryRequest = new RepositoryRequest();
        repositoryRequest.call(page, this, this);
    }

    private void onScrolledRepositoryRecycler(){
        totalItemCount = manager.getItemCount();
        lastVisibleItem = manager.findLastVisibleItemPosition();
        if(!loading && totalItemCount-1 == lastVisibleItem){
            paginationRepository();
        }
    }

    private void paginationRepository(){
        if(InternetConnection.internetAvailable(this)){
            itemListList.add(null);
            repositoryAdapter.notifyItemInserted(itemListList.size()-1);

            makeRequest(String.valueOf((totalItemCount / 30) + 1));
            loading = true;
        }
    }


    private void createAdapterRecyclerView(){
        repositoryAdapter = new RepositoryAdapter(this, itemListList, this);
        rvRepository.setAdapter(repositoryAdapter);
    }

    private void recyclerViewConfigurations(){
        rvRepository.setLayoutManager(manager);
        rvRepository.addItemDecoration(new DividerItemDecoration(rvRepository.getContext(),
                manager.getOrientation()));

        rvRepository.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                MainActivity.this.onScrolledRepositoryRecycler();
            }
        });
    }
}
