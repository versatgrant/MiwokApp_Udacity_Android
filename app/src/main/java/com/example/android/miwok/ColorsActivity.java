package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mPlayer;
    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer m){
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create a list of words
        final ArrayList<Word> colorWords = new ArrayList<Word>();
        colorWords.add(new Word("red","weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colorWords.add(new Word("green","chokokki", R.drawable.color_green, R.raw.color_green));
        colorWords.add(new Word("brown","ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colorWords.add(new Word("grey","ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colorWords.add(new Word("black","kululli", R.drawable.color_black, R.raw.color_black));
        colorWords.add(new Word("white","kelelli", R.drawable.color_white, R.raw.color_white));
        colorWords.add(new Word("dusty yellow","ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colorWords.add(new Word("mustard yellow","chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        // Create a {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter colorsAdapter = new WordAdapter(this, colorWords, R.color.category_colors);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(colorsAdapter);
        //Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //release the media player if it exists because we are about to
                // play a different audio file
                releaseMediaPlayer();
                //create and setup the {@link MediaPlayer} for the audio resource
                // associated with the current word
                mPlayer = MediaPlayer.create(ColorsActivity.this, colorWords.get(position).getAudioResourceId());
                mPlayer.start();
                //Setup a Listener on the Media player so that we can stop and release
                //the media player once the sound has stopped playing
                mPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mPlayer = null;
        }
    }
}
