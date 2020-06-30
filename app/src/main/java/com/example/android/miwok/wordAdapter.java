package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
//it inherits Array adapter class


public class wordAdapter extends ArrayAdapter<word> {
    private int colorResId;
   public wordAdapter(Activity context, ArrayList<word> word_list,int colorResId){
       super(context,0,word_list);
       this.colorResId=colorResId;
   }

    @NonNull
    @Override
   //getView:- that displays the data at the specified position in the data set.
    //You can either create a View manually or inflate it from an XML layout file.
    // When the View is inflated, the parent View (GridView, ListView...) will apply default layout parameters unless you use
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       /** @param position The position in the list of data that should be displayed in the
        *                 list item view.
        * @param convertView The recycled view to populate.
        * @param parent The parent ViewGroup that is used for inflation.
        * @return The View for the position in the AdapterView.
        */
        View listItemView = convertView;
        // Check if the existing view is being reused, otherwise inflate the view
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Get the {word and translation} object located at this position in the list
        word currntWord=getItem(position);


        ImageView icn_image= (ImageView) listItemView.findViewById(R.id.img);
        if(currntWord.hasimage()){
        icn_image.setImageResource(currntWord.getmImageResourceId());
        icn_image.setVisibility(View.VISIBLE);}
        else{
            //if no image then make image gone
            icn_image.setVisibility(View.GONE);
        }

        // Find the TextView in the list_item.xml layout with the ID eng
        TextView wordTextView =(TextView) listItemView.findViewById(R.id.eng);
        // Get the word from the currntWord object and
        // set this text on the word TextView
        wordTextView.setText(currntWord.getWord());
//Sets the text to be displayed using a string resource identifier.

        TextView translationTextView=(TextView) listItemView.findViewById(R.id.miwok);
        translationTextView.setText(currntWord.getTranlation());

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), colorResId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);
        return listItemView;
    }

}
