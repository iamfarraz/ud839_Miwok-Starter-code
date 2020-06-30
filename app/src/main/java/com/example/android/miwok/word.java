package com.example.android.miwok;

import android.media.Image;

class word{
    private String word;
    private String tranlation;


    private int mImageResourceId=-1;
    private int mAudioResourceId;
    public word(String word,String tranlation,int mAudioResourceId) {
        this.word = word;
        this.tranlation=tranlation;
    this.mAudioResourceId=mAudioResourceId;}

    public word(String word,String tranlation,int mImageResourceId,int mAudioResourceId) {
        this.word = word;
        this.tranlation=tranlation;
        this.mImageResourceId=mImageResourceId;
        this.mAudioResourceId=mAudioResourceId;
    }

    public String getWord() {
        return word;
    }

    public String getTranlation() {
        return tranlation;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public int getmAudioResourceId() {
        return mAudioResourceId;
    }

    public boolean hasimage(){
        if(mImageResourceId==-1) return false;
          else return true;
    }
}
