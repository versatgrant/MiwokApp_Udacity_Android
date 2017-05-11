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

public class FamilyMembersActivity extends AppCompatActivity {
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
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange ==
                    AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
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
            } /*else if (focusChange ==
                    AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // Lower the volume, because something else is also
                // playing audio over you.
                // i.e. for notifications or navigation directions
                // Depending on your audio playback, you may prefer to
                // pause playback here instead. You do you.
                mPlayer.pause();
            }*/ else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // Resume playback, because you hold the Audio Focus
                // again!
                // i.e. the phone call ended or the nav directions
                // are finished
                // If you implement ducking and lower the volume, be
                // sure to return it to normal here, as well.
                mPlayer.seekTo(0);
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
        final ArrayList<Word> familyWords = new ArrayList<Word>();
        familyWords.add(new Word("father","әpә", R.drawable.family_father, R.raw.family_father));
        familyWords.add(new Word("mother","әṭa", R.drawable.family_mother, R.raw.family_mother));
        familyWords.add(new Word("son","angsi", R.drawable.family_son, R.raw.family_son));
        familyWords.add(new Word("daughter","tune", R.drawable.family_daughter, R.raw.family_daughter));
        familyWords.add(new Word("older brother","tachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        familyWords.add(new Word("younger brother","chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        familyWords.add(new Word("older sister","teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        familyWords.add(new Word("younger sister","kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        familyWords.add(new Word("grandmother","ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        familyWords.add(new Word("grandfather","paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        // Create a {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter familyAdapter = new WordAdapter(this, familyWords, R.color.category_family);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(familyAdapter);
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
                    mPlayer = MediaPlayer.create(FamilyMembersActivity.this, familyWords.get(position).getAudioResourceId());

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
