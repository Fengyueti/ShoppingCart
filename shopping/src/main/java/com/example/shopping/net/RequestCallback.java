package com.example.shopping.net;

public interface RequestCallback {
    void success(String result);
    void failure(String msg);
}
