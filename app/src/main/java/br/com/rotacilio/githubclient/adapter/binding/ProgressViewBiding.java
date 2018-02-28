package br.com.rotacilio.githubclient.adapter.binding;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.rotacilio.githubclient.R;
import br.com.rotacilio.githubclient.common.InternetConnection;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by rodrigo on 28/02/18.
 */
@EViewGroup(R.layout.item_loading)
public class ProgressViewBiding extends ConstraintLayout {

    @ViewById
    ProgressBar progressBar;

    @ViewById
    TextView tvInfo;

    public ProgressViewBiding(Context context) {
        super(context);
    }

    public void indeterminateProgress(boolean indeterminate){
        progressBar.setIndeterminate(indeterminate);
    }
}
