package com.example.stevendeweille_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class ShowStory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_story);

        Intent intent = getIntent();
        Story story = (Story) intent.getSerializableExtra("ourStory");

        TextView storyView = (TextView) findViewById(R.id.storyView);
        String storyText = story.toString();
        storyView.setText(Html.fromHtml(storyText));
    }
}
