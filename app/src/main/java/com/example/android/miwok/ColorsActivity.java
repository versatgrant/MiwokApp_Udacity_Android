package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create a list of words
        ArrayList<Word> colorWords = new ArrayList<Word>();
        colorWords.add(new Word("red","weṭeṭṭi", R.drawable.color_red));
        colorWords.add(new Word("green","chokokki", R.drawable.color_green));
        colorWords.add(new Word("brown","ṭakaakki", R.drawable.color_brown));
        colorWords.add(new Word("grey","ṭopoppi", R.drawable.color_gray));
        colorWords.add(new Word("black","kululli", R.drawable.color_black));
        colorWords.add(new Word("white","kelelli", R.drawable.color_white));
        colorWords.add(new Word("dusty yellow","ṭopiisә", R.drawable.color_dusty_yellow));
        colorWords.add(new Word("mustard yellow","chiwiiṭә", R.drawable.color_mustard_yellow));

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
    }
}
