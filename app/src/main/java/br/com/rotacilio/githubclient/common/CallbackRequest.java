package br.com.rotacilio.githubclient.common;

/**
 * Created by rodrigo on 28/02/18.
 */
public interface CallbackRequest {
    void response(Object object);
    void failure();
}
