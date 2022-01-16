package com.example.mechanic.api;

import  android.app.Application;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mechanic.User;


import org.json.JSONException;
import org.json.JSONObject;

public class WebApi implements API {

   public static final String BASE_URL = "https://mybusses.herokuapp.com/";

   private final Application mApplication;
   private final RequestQueue mRequestQueue;


   public WebApi(Application application){
       mApplication = application;
       mRequestQueue = Volley.newRequestQueue(application);
    }
//login any user
    public void login(String username,String password,final APIListener listener){
       String url = BASE_URL + "api/login_users_api.php";
       JSONObject jsonObject = new JSONObject();


       try {

          jsonObject.put("username",username);
          jsonObject.put("password",password);

          Response.Listener<JSONObject> successListener = response -> {


             try {
                User user = User.getUser(response);
                listener.onLogin(user);
                }
             catch (JSONException e) {
                try {
                   User error =User.getErr(response);
                   listener.onLogin(error);
                  // Toast.makeText(mApplication, "ERROR: "+ error.getError(), Toast.LENGTH_LONG).show();

                } catch (JSONException jsonException) {
                   Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();
                }
             }
          };


          Response.ErrorListener errorListener = error ->{
             if (error instanceof com.android.volley.NoConnectionError) {
                Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
             }else{
                Toast.makeText(mApplication, "There was an error", Toast.LENGTH_LONG).show();
             }
          };

          JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
          mRequestQueue.add(request);
       } catch (JSONException e) {
          Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
       }

    }

   @Override
   public void register(String username, String password, String email, APIListener listener) {

   }

   @Override
   public void hostels(String id, APIListener listener) {

   }

   //displaying landlord items
   public void landlord(String id, APIListener listener) {

   }


   // add hostel
   public void addmechanic(String usernamev,String emailv,String password1v,String phonev,String
           plocationv,String location, final APIListener listener){

      String url = BASE_URL + "api/add_mechanic_api.php";
      JSONObject jsonObject = new JSONObject();
      try {

         jsonObject.put("email",emailv);
         jsonObject.put("phone",phonev);
         jsonObject.put("username",usernamev);
         jsonObject.put("password",password1v);
         jsonObject.put("location",location);
         jsonObject.put("plocation",plocationv);

         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getmechanic(response);
               listener.onaddmechanic(user);
            }
            catch (JSONException e) {
               try {
                  User error =User.getErr(response);
                  listener.onaddmechanic(error);

               } catch (JSONException jsonException) {
                  Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();
               }
            }
         };


         Response.ErrorListener errorListener = error ->{
            if (error instanceof com.android.volley.NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }else{
               Toast.makeText(mApplication, "There was an error", Toast.LENGTH_LONG).show();
            }
         };

         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }

}
