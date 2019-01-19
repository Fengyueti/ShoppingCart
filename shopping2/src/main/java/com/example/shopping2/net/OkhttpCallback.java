package com.example.shopping2.net;

public interface OkhttpCallback {
    void success(String result);
    void failure(String msg);
}
