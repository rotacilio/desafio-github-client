package br.com.rotacilio.githubclient.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.rotacilio.githubclient.adapter.binding.ItemViewBinding;

/**
 * Created by rodrigo on 28/02/18.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder{

    private ItemViewBinding itemViewBinding;

    public ItemViewHolder(View itemView) {
        super(itemView);
        itemViewBinding = (ItemViewBinding) itemView;
    }

    public ItemViewBinding getItemViewBinding() {
        return itemViewBinding;
    }
}
