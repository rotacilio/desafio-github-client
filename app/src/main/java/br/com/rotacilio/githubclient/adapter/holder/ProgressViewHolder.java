package br.com.rotacilio.githubclient.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import br.com.rotacilio.githubclient.adapter.binding.ProgressViewBiding;

/**
 * Created by rodrigo on 28/02/18.
 */
public class ProgressViewHolder extends RecyclerView.ViewHolder {

    private ProgressViewBiding progressViewBiding;

    public ProgressViewHolder(View itemView) {
        super(itemView);
        progressViewBiding = (ProgressViewBiding) itemView;
    }

    public ProgressViewBiding getIProgressViewBiding() {
        return progressViewBiding;
    }

}
