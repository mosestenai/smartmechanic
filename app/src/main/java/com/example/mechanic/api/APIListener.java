package com.example.mechanic.api;

import com.example.mechanic.User;

public interface APIListener {
    void onLogin(User user);
    void landlordview(User user);
    void onaddmechanic(User user);
}
