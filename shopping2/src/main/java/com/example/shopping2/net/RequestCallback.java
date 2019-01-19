package com.example.shopping2.net;

public interface RequestCallback {
    void success(String result);
    void failure(String msg);
}
