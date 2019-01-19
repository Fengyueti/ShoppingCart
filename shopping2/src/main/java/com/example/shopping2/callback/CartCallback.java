package com.example.shopping2.callback;

public interface CartCallback {
    void nitifyNum();
    void notifyItem(boolean isChecked, int position);
}
