package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create a list of words
        ArrayList<Word> familyWords = new ArrayList<Word>();
        familyWords.add(new Word("father","әpә"));
        familyWords.add(new Word("mother","әṭa"));
        familyWords.add(new Word("son","angsi"));
        familyWords.add(new Word("daughter","tune"));
        familyWords.add(new Word("older brother","tachi"));
        familyWords.add(new Word("younger brother","chalitti"));
        familyWords.add(new Word("older sister","teṭe"));
        familyWords.add(new Word("younger sister","kolliti"));
        familyWords.add(new Word("grandmother","ama"));
        familyWords.add(new Word("grandfather","paapa"));

        // Create a {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter itemsAdapter = new WordAdapter(this, familyWords);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(itemsAdapter);
    }
}
