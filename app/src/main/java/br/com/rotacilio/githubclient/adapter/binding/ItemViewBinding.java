package br.com.rotacilio.githubclient.adapter.binding;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import br.com.rotacilio.githubclient.R;
import br.com.rotacilio.githubclient.model.ItemList;

import static android.widget.Toast.makeText;

/**
 * Created by rodrigo on 28/02/18.
 */
@EViewGroup(R.layout.item_info)
public class ItemViewBinding extends RelativeLayout{

    @ViewById
    TextView tvStars;

    @ViewById
    TextView tvTitle;

    @ViewById
    TextView tvForkDate;

    @ViewById
    TextView tvForkDateLabel;

    @ViewById
    TextView tvContent;

    @ViewById
    TextView tvNome;

    @ViewById
    TextView tvUsuario;

    @ViewById
    TextView tvFork;

    @ViewById
    ImageView ivProfile;

    @ViewById
    ImageView ivStar;

    @ViewById
    ImageView ivFork;

    public ItemViewBinding(Context context) {
        super(context);
    }

    public void bind(ItemList itemList, Context context){
        if(itemList.getStars() != -0){
            ivStar.setVisibility(VISIBLE);
            ivFork.setVisibility(VISIBLE);
            tvStars.setVisibility(VISIBLE);
            tvFork.setVisibility(VISIBLE);
            tvStars.setText(Long.toString(itemList.getStars()));
            tvFork.setText(Long.toString(itemList.getfork()));
            tvForkDate.setVisibility(GONE);
            tvForkDateLabel.setVisibility(GONE);
        } else {
            tvForkDate.setText(itemList.getDatePullRequest());
        }
        tvTitle.setText(itemList.getTitle());
        tvContent.setText(itemList.getDescription());
        tvNome.setText(itemList.getName());
        tvUsuario.setText(itemList.getNameUser());

        if(itemList.getImgUrl() != null && !itemList.getImgUrl().trim().equals("")){
            Picasso.with(context).load(itemList.getImgUrl())
                    .placeholder(R.drawable.avatar)
                    .into(ivProfile, new Callback() {
                        @Override
                        public void onSuccess() {
                            Bitmap imageBitmap = ((BitmapDrawable) ivProfile.getDrawable()).getBitmap();
                            RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
                            imageDrawable.setCircular(true);
                            imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                            ivProfile.setImageDrawable(imageDrawable);
                        }
                        @Override
                        public void onError() {
                            ivProfile.setImageResource(R.drawable.avatar);
                        }
                    });
        }

    }

}
