package com.example.tms;

public class Uploads {
    private String mName;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    private String mImageUrl;
    public  Uploads(){

    }
    public Uploads(String name,String imageUrl){
        if(name.trim().equals("")){
            mName="No name";
        }
        mName=name;
        mImageUrl=imageUrl;
    }
}

