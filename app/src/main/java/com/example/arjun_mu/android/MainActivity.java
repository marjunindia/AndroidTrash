package com.example.arjun_mu.android;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textview);



    }

    public void onClickDisplayEntries(View view) {

        // URI That identifies the content provider and the table.
        String queryUri = Contract.CONTENT_URI.toString();

        // The columns to return for each row. Setting this to null returns all of them.
        // When there is only one column, as in the case of this example, setting this
        // explicitly is optional, but can be helpful for documentation purposes.
        String[] projection = new String[] {Contract.CONTENT_PATH}; // Only get words.

        // Argument clause for the selection criteria for which rows to return.
        // Formatted as an SQL WHERE clause (excluding the WHERE itself).
        // Passing null returns all rows for the given URI.
        String selectionClause;

        // Argument values for the selection criteria.
        // If you include ?s in selection, they are replaced by values from selectionArgs,
        // in the order that they appear.
        // IMPORTANT: It is a best security practice to always separate selection and selectionArgs.
        String selectionArgs[];

        // The order in which to sort the results.
        // Formatted as an SQL ORDER BY clause (excluding the ORDER BY keyword).
        // Usually ASC or DESC; null requests the default sort order, which could be unordered.
        String sortOrder = null; // For this example, we accept the order returned by the response.

        // Set selection criteria depending on which button was pressed.
        switch (view.getId()) {
            case R.id.button_display_all:
                selectionClause = null;
                selectionArgs = null;
                break;
            case R.id.button_display_first:
                selectionClause = Contract.WORD_ID + " = ?";
                selectionArgs = new String[] {"0"};
                break;
            default:
                selectionClause = null;
                selectionArgs = null;
        }

        // Let the content resolver parse the query and do the right things with it.
        // If you provide a well-formed query, the results should always be civilized.
        // This is magic that is explained in the next practical.
        // We don't need to create a custom content resolver,
        // ...we just use the one already there for our app context.
        Cursor cursor =
                getContentResolver().query(Uri.parse(queryUri), projection, selectionClause,
                        selectionArgs, sortOrder);

        // If we got data back, display it, otherwise report the error.
        // See WordList app and database chapter for more on cursors.
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(projection[0]);
                do {
                    String word = cursor.getString(columnIndex);
                    mTextView.append(word + "\n");
                } while (cursor.moveToNext());
            } else {
                Log.d(TAG, "onClickDisplayEntries " + "No data returned.");
                mTextView.append("No data returned." + "\n");
            }
            cursor.close();
        } else {
            Log.d(TAG, "onClickDisplayEntries " + "Cursor is null.");
            mTextView.append("Cursor is null." + "\n");
        }
    }

}

