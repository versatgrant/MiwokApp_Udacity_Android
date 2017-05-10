package com.example.android.miwok;

import android.graphics.drawable.Drawable;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */
public class Word {

    /***/
    private final int NO_IMAGE_PROVIDED = -1;

    /** Drawable resource ID*/
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /**Raw audio file clip*/
    private int mAudioResourceId;

    /** Miwok translation for the word*/
    private String mMiwokTranslation;

    /** Default translation for the word*/
    private String mDefaultTranslation;



    public Word(String defaultTranslation, String miwokTranslation, int ImageResourceId, int AudioResourceId){
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mImageResourceId = ImageResourceId;
        mAudioResourceId = AudioResourceId;
    }

    public Word(String defaultTranslation, String miwokTranslation, int AudioResourceId){
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mAudioResourceId = AudioResourceId;
    }

    /** Get the Image Resource Id for the word*/
    public int getImageResourceId(){
        return mImageResourceId;
    }

    /** Get the Audio Resource Id for the word*/
    public int getAudioResourceId(){
        return  mAudioResourceId;
    }

    /** Get the Miwok translation for the word*/
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    /** Get the Default translation for the word*/
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
