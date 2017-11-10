package com.example.stevendeweille_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.inflate(R.menu.popup_menu);
        popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.random) {
                    randomStory();
                }
                else {
                    String story = getResources().getResourceEntryName(item.getItemId());
                    useStory(story);
                }
                return true;
            }
        });
        popup.show();
    }

    public void randomStory() {
        String[] stories = {"simple", "tarzan", "university", "clothes", "dance"};
        Random random = new Random();
        String story = stories[random.nextInt(stories.length)];
        useStory(story);
    }

    public void useStory(String storyName) {
        // Read story name
        String storyFile = "";
        switch (storyName) {
            case "simple":
                storyFile = "madlib0_simple.txt";
                break;
            case "tarzan":
                storyFile = "madlib1_tarzan.txt";
                break;
            case "university":
                storyFile = "madlib2_university.txt";
                break;
            case "clothes":
                storyFile = "madlib3_simple.txt";
                break;
            case "dance":
                storyFile = "madlib4_dance.txt";
                break;
        }
        // Load story
        try {
            DataInputStream textFileStream = new DataInputStream(getAssets().open(storyFile));
            Story story = new Story(textFileStream);
            Intent intent = new Intent(this, FillText.class);
            intent.putExtra("ourStory", story);
            startActivity(intent);
            // We don't finish this activity so the back button will navigate to this first activity
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
