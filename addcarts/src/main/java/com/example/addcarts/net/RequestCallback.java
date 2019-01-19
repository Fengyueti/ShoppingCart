package com.example.addcarts.net;

public interface RequestCallback {
    void success(String result);
    void failure(String msg);
}
