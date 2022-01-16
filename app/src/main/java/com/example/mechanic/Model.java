package com.example.mechanic;
import  android.app.Application;

import com.example.mechanic.api.API;
import com.example.mechanic.api.APIListener;
import com.example.mechanic.api.AbstractAPIListener;
import com.example.mechanic.api.WebApi;

public class Model {
    //to make sure there is only one instance of a model class
    private static  Model sInstance = null;

    private final API mApi;
    private User mUser;

    public static Model getInstance(Application application) {

        if (sInstance == null) {
            sInstance = new Model(application);
        }return  sInstance;
    }

    private final Application mApplication;
    private Model(Application application) {
        mApplication = application;
        mApi = new WebApi(mApplication);
    }
    public Application getApplication() { return mApplication;}


    public void login(String username, String password,APIListener listener){
        mApi.login(username, password, listener);
    }
    public  void register(String username,String password, String email,APIListener listener){
        mApi.register(username,password,email,listener);
    }
    public void landlord(String id,APIListener listener){
        mApi.landlord(id,listener);
    }
    protected void addmechanic(String usernamev, String password1v, String emailv, String location,
                             String plocationv, String phonev,APIListener listener){
        mApi.addmechanic(usernamev,password1v,emailv,location,plocationv,phonev,listener);
    }

    public User getUser() {
        return mUser;
    }
   public void seterror(User error){this.mUser= error ;}
    public void setUser(User user) {
        this.mUser = user;
    }
}

