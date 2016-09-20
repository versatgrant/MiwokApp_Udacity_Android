package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        //Add Number words to ArrayList
        ArrayList<Word> numberWords = new ArrayList<Word>();
        numberWords.add(new Word("one","lutti"));
        numberWords.add(new Word("two","otiiko"));
        numberWords.add(new Word("three","tolookosu"));
        numberWords.add(new Word("four","oyyisa"));
        numberWords.add(new Word("five","massokka"));
        numberWords.add(new Word("six","temmokka"));
        numberWords.add(new Word("seven","kenekaku"));
        numberWords.add(new Word("eight","kawinta"));
        numberWords.add(new Word("nine","wo'e"));
        numberWords.add(new Word("ten","na'aacha"));

        WordAdapter itemsAdapter = new WordAdapter(this, numberWords);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }
}
