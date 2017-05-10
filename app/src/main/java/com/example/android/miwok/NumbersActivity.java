package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
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
        final ArrayList<Word> numberWords = new ArrayList<Word>();
        numberWords.add(new Word("one","lutti", R.drawable.number_one, R.raw.number_one));
        numberWords.add(new Word("two","otiiko", R.drawable.number_two, R.raw.number_two));
        numberWords.add(new Word("three","tolookosu", R.drawable.number_three, R.raw.number_three));
        numberWords.add(new Word("four","oyyisa", R.drawable.number_four, R.raw.number_four));
        numberWords.add(new Word("five","massokka", R.drawable.number_five, R.raw.number_five));
        numberWords.add(new Word("six","temmokka", R.drawable.number_six, R.raw.number_six));
        numberWords.add(new Word("seven","kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numberWords.add(new Word("eight","kawinta", R.drawable.number_eight, R.raw.number_eight));
        numberWords.add(new Word("nine","wo'e", R.drawable.number_nine, R.raw.number_nine));
        numberWords.add(new Word("ten","na'aacha", R.drawable.number_ten, R.raw.number_ten));

        // Create a {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter numbersAdapter = new WordAdapter(this, numberWords, R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(numbersAdapter);
        //Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //release the media player if it exists because we are about to
                // play a different audio file
                releaseMediaPlayer();
                //create and setup the {@link MediaPlayer} for the audio resource
                // associated with the current word
                mPlayer = MediaPlayer.create(NumbersActivity.this, numberWords.get(position).getAudioResourceId());
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
