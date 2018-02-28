package br.com.rotacilio.githubclient.common;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by rodrigo on 28/02/18.
 */
public abstract class InternetConnection {

    public static boolean internetAvailable(Context context){
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
