package com.example.shopping.net;

public interface OkhttpCallback {
    void success(String result);
    void failure(String msg);
}
