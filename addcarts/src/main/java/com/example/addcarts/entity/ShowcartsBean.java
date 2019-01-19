package com.example.addcarts.entity;

import java.util.List;

public class ShowcartsBean {
    public String msg;
    public String code;
    public List<Data> data;
    public class Data{
        public String sellerName;
        public String sellerid;
        public List<LA> list;
        public class LA{
            public String name;
            public double price;
            public String image;
        }
    }
}
