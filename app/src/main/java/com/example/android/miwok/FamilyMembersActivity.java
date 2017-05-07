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
        familyWords.add(new Word("father","әpә", R.drawable.family_father));
        familyWords.add(new Word("mother","әṭa", R.drawable.family_mother));
        familyWords.add(new Word("son","angsi", R.drawable.family_son));
        familyWords.add(new Word("daughter","tune", R.drawable.family_daughter));
        familyWords.add(new Word("older brother","tachi", R.drawable.family_older_brother));
        familyWords.add(new Word("younger brother","chalitti", R.drawable.family_younger_brother));
        familyWords.add(new Word("older sister","teṭe", R.drawable.family_older_sister));
        familyWords.add(new Word("younger sister","kolliti", R.drawable.family_younger_sister));
        familyWords.add(new Word("grandmother","ama", R.drawable.family_grandmother));
        familyWords.add(new Word("grandfather","paapa", R.drawable.family_grandfather));

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
    }
}
