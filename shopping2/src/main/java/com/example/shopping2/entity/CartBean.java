package com.example.shopping2.entity;

import java.util.List;

public class CartBean {
    public String code;
    public String msg;
    public List<Data> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data{
        public String sellerName;
        public String sellerid;
        public List<Product> list;
        public boolean ischecked;//一级列表是否选中

        public boolean isIschecked() {
            return ischecked;
        }

        public void setIschecked(boolean ischecked) {
            this.ischecked = ischecked;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getSellerid() {
            return sellerid;
        }

        public void setSellerid(String sellerid) {
            this.sellerid = sellerid;
        }

        public List<Product> getList() {
            return list;
        }

        public void setList(List<Product> list) {
            this.list = list;
        }

        public class Product{
            public String title;
            public String images;
            public double price;
            public String pid;
            public boolean isproductChecked;
            public int productNum;
            public int pos;

            public int getProductNum() {
                return productNum;
            }

            public void setProductNum(int productNum) {
                this.productNum = productNum;
            }

            public int getPos() {
                return pos;
            }

            public void setPos(int pos) {
                this.pos = pos;
            }

            public boolean isIsproductChecked() {
                return isproductChecked;
            }

            public void setIsproductChecked(boolean isproductChecked) {
                this.isproductChecked = isproductChecked;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }
        }
    }
}
