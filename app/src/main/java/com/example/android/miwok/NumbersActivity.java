package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                   if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback
                       mediaPlayer.pause();
                       mediaPlayer.seekTo(0);
                    }
                   else if(focusChange== AudioManager.AUDIOFOCUS_LOSS){
                      ///stop media playing and release the resources
                       releaseMediaPlayer();
                   }
                   else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //resume from where it paused
                       mediaPlayer.start();
                    }
                }
            };

    //aise hi bna dia h baar baar nye objects naaa bnane pd islie
    private MediaPlayer.OnCompletionListener complete_and_release=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //to add back button



        audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Adapter +list view method
        final ArrayList<word> words=new ArrayList<word>();
        words.add(new word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

       wordAdapter itemsAdapter = new wordAdapter(this, words ,R.color.category_numbers);
                                                //constructor have a predefined resource which converts array list to view which stored in list view,gridview or spinner
      //array adapter by default takes only one textView but we are passing our custom layout so we have
        //to make customm adapter to achiev this
        ListView listView = (ListView) findViewById(R.id.list_nums);//list is id of layout of this page
        listView.setAdapter(itemsAdapter);
//set is used to display things

        // without recycler view(list view +Array adapter)
//       for(int i=0;i<10;i++) {
//           LinearLayout rootview = (LinearLayout) findViewById(R.id.rootview);
//           TextView text = new TextView(this);
//           text.setText( w.word.get(i));
//           rootview.addView(text);
//       }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                word w=words.get(i);
                int result = audioManager.requestAudioFocus(audioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Gained audio focus now play ..

                    releaseMediaPlayer();
                    //this release help to avoid overlap by releasing tht mp3 if other mp3 or same mp3 called simultaneously
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, w.getmAudioResourceId());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(complete_and_release);

                }
            }
        });

        }
    @Override
    protected void onStop() {
        super.onStop();
        //when actvity stopped ,then immedeatly release the resource
        releaseMediaPlayer();
    }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
              //abandon audio focus when media complete so call it here becausse when media complte
            //resources also get released
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }

 }
}
