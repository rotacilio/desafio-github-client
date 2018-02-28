package br.com.rotacilio.githubclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import br.com.rotacilio.githubclient.adapter.binding.ItemViewBinding;
import br.com.rotacilio.githubclient.adapter.binding.ItemViewBinding_;
import br.com.rotacilio.githubclient.adapter.binding.ProgressViewBiding;
import br.com.rotacilio.githubclient.adapter.binding.ProgressViewBiding_;
import br.com.rotacilio.githubclient.adapter.holder.ItemViewHolder;
import br.com.rotacilio.githubclient.adapter.holder.ProgressViewHolder;
import br.com.rotacilio.githubclient.common.RecyclerViewClick;
import br.com.rotacilio.githubclient.model.ItemList;


/**
 * Created by rodrigo on 28/02/18.
 */
public class RepositoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<ItemList> items;
    private RecyclerViewClick recyclerViewClick;
    private final int VIEW_ITEM = 0;
    private final int VIEW_LOADING = 1;

    public RepositoryAdapter(Context context, List<ItemList> items, RecyclerViewClick recyclerViewClick) {
        this.context = context;
        this.items = items;
        this.recyclerViewClick = recyclerViewClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == VIEW_ITEM){

            ItemViewBinding itemViewBinding_ = ItemViewBinding_.build(context);
            final ItemViewHolder itemViewHolder = new ItemViewHolder(itemViewBinding_);
            itemViewBinding_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClick.onItemClick(items.get(itemViewHolder.getAdapterPosition()));
                }
            });

            return itemViewHolder;

        } else if(viewType == VIEW_LOADING) {
            ProgressViewBiding progressViewBiding_ = ProgressViewBiding_.build(context);
            return new ProgressViewHolder(progressViewBiding_);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ItemViewBinding itemViewBinding = ((ItemViewHolder) holder).getItemViewBinding();
            ItemList itemList = items.get(position);
            itemViewBinding.bind(itemList, context);

        } else if(holder instanceof ProgressViewHolder){
            ProgressViewBiding progressViewBiding = ((ProgressViewHolder) holder).getIProgressViewBiding();
            progressViewBiding.indeterminateProgress(true);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? VIEW_LOADING : VIEW_ITEM;
    }
}
