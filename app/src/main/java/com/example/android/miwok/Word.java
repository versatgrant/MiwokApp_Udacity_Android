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

    /** Miwok translation for the word*/
    private String mMiwokTranslation;

    /** Default translation for the word*/
    private String mDefaultTranslation;



    public Word(String defaultTranslation, String miwokTranslation, int ImageResourceId){
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mImageResourceId = ImageResourceId;
    }

    public Word(String defaultTranslation, String miwokTranslation){
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
    }

    /** Get the Miwok translation for the word*/
    public int getImageResourceId(){
        return mImageResourceId;
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
