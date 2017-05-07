package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create a list of words
        ArrayList<Word> numberWords = new ArrayList<Word>();
        numberWords.add(new Word("one","lutti", R.drawable.number_one));
        numberWords.add(new Word("two","otiiko", R.drawable.number_two));
        numberWords.add(new Word("three","tolookosu", R.drawable.number_three));
        numberWords.add(new Word("four","oyyisa", R.drawable.number_four));
        numberWords.add(new Word("five","massokka", R.drawable.number_five));
        numberWords.add(new Word("six","temmokka", R.drawable.number_six));
        numberWords.add(new Word("seven","kenekaku", R.drawable.number_seven));
        numberWords.add(new Word("eight","kawinta", R.drawable.number_eight));
        numberWords.add(new Word("nine","wo'e", R.drawable.number_nine));
        numberWords.add(new Word("ten","na'aacha", R.drawable.number_ten));

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
    }
}
