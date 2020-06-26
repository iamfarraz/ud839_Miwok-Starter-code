package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
       List<String> nums=new ArrayList<>();
        nums.add("one");
        nums.add("two");
        nums.add("three");
        nums.add("four");
        nums.add("five");
        nums.add("six");
        nums.add("seven");
        nums.add("eight");
        nums.add("nine");
        nums.add("ten");
       for(int i=0;i<10;i++) {
           LinearLayout rootview = (LinearLayout) findViewById(R.id.rootview);
           TextView text = new TextView(this);
           text.setText(nums.get(i));
           rootview.addView(text);
       }
    }
}