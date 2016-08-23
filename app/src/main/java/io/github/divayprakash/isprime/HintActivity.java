package io.github.divayprakash.isprime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * The HintActivity class implements the control logic for the hint screen
 * displayed when the user presses the "Hint" button.
 *
 * @author Divay Prakash
 * @version 1.0
 */
public class HintActivity extends AppCompatActivity {

    /**
     * This method is called at the startup of the application. It sets the
     * view to the XML file associated with this Activity. The method also
     * enables the back button on the action bar.
     * @param savedInstanceState The saved instance state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * This method is called when the back button on the action bar is pressed.
     * It creates an Intent instance to return to the MainActivity.
     * @param item The menu item instance passed to this method.
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
        return true;
    }
}
