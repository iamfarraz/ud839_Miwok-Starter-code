/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

/* slide with view pager+ fragmnt adapter
Step 1.declare view pager in xml
step 2.make a custom FragmentPagerAdapter by extending this class
       a) override getitem and getcount mehods
       b)declare constructor by super
Step 3. make a object of viewpager tht contains viewpager xml id
Step4.make object of custom adapter and pass getSupportFragmentManager() in constructor,
           setAdapter with the help of view pager.

*/
public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private TabItem nums,fam,colors,phrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);
         tabLayout=(TabLayout) findViewById(R.id.slide_tab);
            nums=(TabItem) findViewById(R.id.tab_nums);
            fam=(TabItem) findViewById(R.id.tab_fam);
            colors=(TabItem) findViewById(R.id.tab_col);
            phrase=(TabItem)findViewById(R.id.tab_phrse);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        CategoryAdapter adapter=new CategoryAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}