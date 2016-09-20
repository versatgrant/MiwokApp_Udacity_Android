package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        //Add Number words to ArrayList
        ArrayList<String> numberWords = new ArrayList<String>();
        numberWords.add("one");numberWords.add("two");numberWords.add("three");numberWords.add("four");numberWords.add("five");
        numberWords.add("six");numberWords.add("seven");numberWords.add("eight");numberWords.add("nine");numberWords.add("ten");

        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView_numbers);

        for(int i=0;i<numberWords.size();i++){
            TextView wordView = new TextView(this);
            wordView.setText(numberWords.get(i));
            rootView.addView(wordView);
        }
    }
}
