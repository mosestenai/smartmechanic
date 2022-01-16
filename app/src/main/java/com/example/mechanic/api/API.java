package com.example.mechanic.api;

public interface API {
    void landlord(String id,APIListener listener);
    void login(String username, String password,APIListener listener);
    void register(String username,String password,String email,APIListener listener);
    void hostels(String id,APIListener listener);
    void addmechanic(String usernamev,String emailv,String password1v,String phonev,String
            plocationv,String location,APIListener listener);
}
