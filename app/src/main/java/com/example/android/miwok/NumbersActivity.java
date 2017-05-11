package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    /** Handles playback of all the sound files */
    private MediaPlayer mPlayer;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer m){
            releaseMediaPlayer();
        }
    };

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener(){
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                // Pause playback because your Audio Focus was
                // temporarily stolen, but will be back soon.
                // i.e. for a phone call
                mPlayer.pause();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                /** Stop playback, because you lost the Audio Focus.
                 * i.e. the user started some other playback app
                 * Remember to unregister your controls/buttons here.
                 * And release the kra — Audio Focus!
                 * You’re done.
                 * mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener); <-- Implemented in releaseMediaPlayer() method
                 */
                releaseMediaPlayer();
            } else if (focusChange ==
                    AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // Lower the volume, because something else is also
                // playing audio over you.
                // i.e. for notifications or navigation directions
                // Depending on your audio playback, you may prefer to
                // pause playback here instead. You do you.
                mPlayer.setVolume((float)0.1, (float)0.1);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // Resume playback, because you hold the Audio Focus
                // again!
                // i.e. the phone call ended or the nav directions
                // are finished
                // If you implement ducking and lower the volume, be
                // sure to return it to normal here, as well.
                mPlayer.setVolume((float)0.99, (float)0.99);
                mPlayer.start();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

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

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int audioFocusChangeResult = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(audioFocusChangeResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    // We have audio focus now.

                    //create and setup the {@link MediaPlayer} for the audio resource
                    // associated with the current word
                    mPlayer = MediaPlayer.create(NumbersActivity.this, numberWords.get(position).getAudioResourceId());

                    //Start Playing the audio file
                    mPlayer.start();
                    //Setup a Listener on the Media player so that we can stop and release
                    //the media player once the sound has stopped playing
                    mPlayer.setOnCompletionListener(mOnCompletionListener);
                }
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

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
