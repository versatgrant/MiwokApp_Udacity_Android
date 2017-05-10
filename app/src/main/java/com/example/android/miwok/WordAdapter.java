package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nicola on 9/20/2016.
 */
public class WordAdapter extends ArrayAdapter<Word> {
    //Variable to store the color of the list that the
    // Adapter is handling
    private int mListColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> wordList, int ListColorResourceId){
        super(context, 0, wordList);
        mListColorResourceId = ListColorResourceId;
    }


    /*
* {@link WordAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
* based on a data source, which is a list of {@link Word} objects.
* */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        final Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwokText_ListItem
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwokText_ListItem);
        // Get the Miwok Translation from the current Word object and
        // set this text on the miwokText_ListItem TextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID defaultText_ListItem
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.defaultText_ListItem);
        // Get the default translation from the current Word object and
        // set this text on the defaultText_ListItem TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the LinearLayout in the list_item.xml layout with the ID groupText_ListItem
        LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.groupText_ListItem);
        // Set its background to be the color given in the mListColorResourceId
        linearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), mListColorResourceId));

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);

        /*
        Check if the current word object has an Image Resource ID and
        if it does, set the ImageView with the Image Resource, otherwise
        Remove the ImageView from the list item ViewGroup
        */
        if(currentWord.hasImage()){
            // Get the image resource ID from the current Word object and
            // set the image to iconView
            iconView.setImageResource(currentWord.getImageResourceId());
            iconView.setVisibility(View.VISIBLE);
        }else{
            iconView.setVisibility(View.GONE);
        }

        // Return the whole list item layout (containing 2 TextViews an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
