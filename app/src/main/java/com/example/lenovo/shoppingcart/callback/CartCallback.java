package com.example.lenovo.shoppingcart.callback;

public interface CartCallback {
    void notifyCartItem(boolean isChecked,int position);
    void notifyNum();
}
