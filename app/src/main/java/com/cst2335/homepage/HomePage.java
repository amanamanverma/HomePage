/**
 * This package contains the HomePage class for the final project of CST2335.
 */
package com.cst2335.homepage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;
import java.util.Random;

/**

 This class represents the main activity of the Aman feature of the final project.

 It displays an image and allows the user to create a username.

 It also shows a toast message when the user clicks on certain images and

 displays an alert dialog when the user clicks on the info icon.

 It receives broadcasts to detect changes in the system's language and updates the application accordingly.

 Author: Aman

 Date: April 3, 2023
 */

/**/
public class HomePage extends AppCompatActivity {
    // Instance variables
    private ImageView image;
    private ImageView day;

    private ImageView search;
    private ImageView save;
    private Button button;
    private Button next;
    private EditText usernameField;
    private SharedPreferences sharedPreferences;
    private TextView active_user;
    private TextView word;


    private static final String USERNAME_KEY = "username";
    /**
     * Shows an alert dialog with instructions when the user clicks on the info icon.
     */
    private void showAlertDialog() {



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alert_guide);
        builder.setMessage((R.string.alert_i)
        );
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * BroadcastReceiver to detect changes in the system's language and update the application accordingly.
     */
    public class LanguageChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_LOCALE_CHANGED)) {
                // update the locale of the application context to the new language
                Locale newLocale = Resources.getSystem().getConfiguration().locale;
                context.getResources().updateConfiguration(context.getResources().getConfiguration(), context.getResources().getDisplayMetrics());
// Get strings for the alert message in the new language
                String alertMessage1 = context.getString(R.string.alert_guide);
                String alertMessage3 = context.getString(R.string.alert_yes);
                String alertMessage4 = context.getString(R.string.alert_yuvraj);
                String alertMessage5 = context.getString(R.string.alert_agam);
                String alertMessage6 = context.getString(R.string.alert_muskan);
                String alertMessage7 = context.getString(R.string.alert_user);
                String alertMessage8 = context.getString(R.string.alert_empty);


// Show alert message
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(alertMessage1);
                AlertDialog alert = builder.create();
                alert.show();
            }}
    }



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeagelayout);


        // Initialize instance variables
        image = findViewById(R.id.img);
        day = findViewById(R.id.yuvra);
        search = findViewById(R.id.aga);
        save = findViewById(R.id.muska);
        button = findViewById(R.id.create);
        usernameField = findViewById(R.id.username);
        word = findViewById(R.id.textView2);


        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString(USERNAME_KEY, "");
        usernameField.setText(savedUsername);
        // Set up click listeners
// Assuming you have a TextView called 'word' in your layout file

// Get a reference to the button
        Button changeButton = findViewById(R.id.next);

// Set a click listener on the button
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate a new random word
                String[] words = {"cat", "dog", "house", "apple", "tree", "book", "pen", "computer", "phone", "chair",
                        "table", "car", "banana", "cookie", "water", "fire", "sky", "sun", "moon", "star",
                        "earth", "ocean", "beach", "mountain", "river", "wind", "rain", "snow", "ice", "grass",
                        "flower", "bird", "fish", "butterfly", "bee", "ant", "spider", "snake", "lion", "tiger",
                        "elephant", "monkey", "giraffe", "zebra", "cow", "sheep", "horse", "pig", "chicken", "duck",
                        "fish", "whale", "dolphin", "shark", "octopus", "crab", "lobster", "turtle", "frog", "lizard",
                        "piano", "guitar", "violin", "drum", "trumpet", "flute", "saxophone", "accordion", "harp", "bass",
                        "rock", "pop", "jazz", "blues", "classical", "country", "rap", "hip-hop", "reggae", "folk",
                        "math", "science", "history", "geography", "literature", "music", "art", "sports", "food", "travel"};
                Random random = new Random();
                String randomWord = words[random.nextInt(words.length)];

                // Set the random word in the TextView
                word.setText(randomWord);
            }
        });



        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.alert_yuvraj, Toast.LENGTH_SHORT).show();
           //     Intent gotoSearch = new Intent(homepage.this, WordOfTheDayActivity.class);
          //      startActivity(gotoSearch);

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.alert_agam, Toast.LENGTH_SHORT).show();
             //   Intent gotoSearch = new Intent(homepage.this, searchword.class);
              //  startActivity(gotoSearch);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.alert_muskan, Toast.LENGTH_SHORT).show();
                Intent gotoSearch = new Intent(HomePage.this, MainActivity.class);
                startActivity(gotoSearch);
            }
        });




        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Button createButton = findViewById(R.id.create);
                EditText usernameField = findViewById(R.id.username);
                String username = usernameField.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    Snackbar.make(createButton, R.string.alert_empty, Snackbar.LENGTH_SHORT).show();
                } else {

                    Snackbar.make(createButton, R.string.alert_user , Snackbar.LENGTH_LONG)
                            .setAction(R.string.alert_yes, new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View view) {
                                    // Add code to create the username here
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(USERNAME_KEY, username);
                                    editor.apply();




                                }
                            })
                            .show();
                    active_user = findViewById(R.id.name);
                    active_user.setText(username);


                }

            }

});
    }



}
