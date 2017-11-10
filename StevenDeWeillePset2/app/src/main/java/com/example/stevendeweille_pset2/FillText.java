package com.example.stevendeweille_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FillText extends AppCompatActivity {

    private Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_text);

        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("ourStory");

        // Fill in words left and word type
        TextView wordsLeft = (TextView) findViewById(R.id.wordsLeft);
        TextView wordType = (TextView) findViewById(R.id.wordType);
        String wordsRemaining = Integer.toString(story.getPlaceholderRemainingCount());
        String placeholder = story.getNextPlaceholder().toLowerCase();
        wordsLeft.setText(wordsRemaining);
        wordType.setText(placeholder);
    }

    public void fillStory(View v) {
        // Add entered word to story
        EditText wordEntered = (EditText) findViewById(R.id.wordEntered);
        String word = wordEntered.getText().toString();
        word = word.toLowerCase().trim();
        word = "<font color=\"#ec407a\">" + word + "<font color=\"#ec407a\">";
        story.fillInPlaceholder(word);

        // Check if story is completely filled in
        if (story.isFilledIn()) {
            Intent intent = new Intent(this, ShowStory.class);
            intent.putExtra("ourStory", story);
            startActivity(intent);
            finish();
        }

        // Fill in words left and word type
        TextView wordsLeft = (TextView) findViewById(R.id.wordsLeft);
        TextView wordType = (TextView) findViewById(R.id.wordType);
        String wordsRemaining = Integer.toString(story.getPlaceholderRemainingCount());
        String placeholder = story.getNextPlaceholder().toLowerCase();

        wordsLeft.setText(wordsRemaining);
        wordType.setText(placeholder);

        // Clear EditText field for next word
        wordEntered.setText("");
    }
}
