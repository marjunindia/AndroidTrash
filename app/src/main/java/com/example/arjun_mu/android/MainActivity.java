package com.example.arjun_mu.android;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Current count.
    private int mCount = 0;
    // Current background color.
    private int mColor;
    // Text view to display both count and color.
    private TextView mShowCountTextView;

    // Key for current count
    private final String COUNT_KEY = "count";
    // Key for current color
    private final String COLOR_KEY = "color";

    // Shared preferences object
    private SharedPreferences mPreferences;
    // Name of shared preferences file
    private static final String mSharedPrefFile = "com.example.android.hellosharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
;   // Initialize views, color, preferences
        mShowCountTextView = (TextView) findViewById(R.id.count_textview);
        mColor = ContextCompat.getColor(this, R.color.default_background);

        mPreferences = getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);

        // Restore preferences
        mCount = mPreferences.getInt(COUNT_KEY, 0);
        mShowCountTextView.setText(String.format("%s", mCount));
        mColor = mPreferences.getInt(COLOR_KEY, mColor);
        mShowCountTextView.setBackgroundColor(mColor);



    }

    /**
     * Callback for activity pause.  Shared preferences are saved in this callback.
     */
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(COUNT_KEY, mCount);
        preferencesEditor.putInt(COLOR_KEY, mColor);
        preferencesEditor.apply();
    }

    /**
     * Handles the onClick for the background color buttons.  Gets background color of the button
     * that was clicked and sets the textview background to that color.
     *
     * @param view The view (Button) that was clicked.
     */
    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowCountTextView.setBackgroundColor(color);
        mColor = color;
    }

    /**
     * Handles the onClick for the Count button.  Increments the value of the mCount global and
     * updates the textview.
     *
     * @param view The view (Button) that was clicked.
     */
    public void countUp(View view) {
        mCount++;
        mShowCountTextView.setText(String.format("%s", mCount));
    }

    /**
     * Handles the onClick for the Reset button.  Resets the global count and background
     * variables to the defaults, resets the views to those values, and clears the shared
     * preferences
     *
     * @param view The view (Button) that was clicked.
     */
    public void reset(View view) {
        // Reset count
        mCount = 0;
        mShowCountTextView.setText(String.format("%s", mCount));

        // Reset color
        mColor = ContextCompat.getColor(this, R.color.default_background);
        mShowCountTextView.setBackgroundColor(mColor);

        // Clear preferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();

    }

}

