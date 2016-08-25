package io.github.divayprakash.isprime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * The HintActivity class implements the control logic for the hint screen
 * displayed when the user presses the "Cheat" button.
 *
 * @author Divay Prakash
 */
public class CheatActivity extends AppCompatActivity {

    /**
     * TextView variable representing the object to display the cheat activity
     * title in.
     */
    private TextView cheatTitle;
    /**
     * TextView variable representing the object to display the cheat answer in.
     */
    private TextView cheatDisplay;
    /**
     * int variable to be initialised to the value passed by MainActivity.
     */
    private int RANDOM_NUMBER;
    /**
     * boolean variable representing whether cheat has been taken or not.
     */
    private boolean IS_CHEAT_TAKEN;

    /**
     * This method is called at the startup of the application. It sets the
     * view to the XML file associated with this Activity. It obtains the
     * RANDOM_NUMBER variable from the Intent passed by MainActivity and
     * sets the cheat state according to the IS_CHEAT_TAKEN variable obtained
     * from the savedInstanceState.
     * @param savedInstanceState The saved instance state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Intent intent = getIntent();
        RANDOM_NUMBER = intent.getIntExtra("RandomNumber", 2);
        cheatTitle = (TextView)findViewById(R.id.cheatTitle);
        cheatDisplay = (TextView)findViewById(R.id.cheatDisplay);
        if (savedInstanceState == null) {
            IS_CHEAT_TAKEN = false;
        }
        else {
            IS_CHEAT_TAKEN = savedInstanceState.getBoolean("IsCheatTaken");
            if (IS_CHEAT_TAKEN) {
                showCheat();
            }
        }
    }

    /**
     * This method checks if the RANDOM_NUMBER is prime or not and accordingly
     * sets visible all data in the Activity.
     */
    public void showCheat() {
        cheatTitle.setVisibility(View.VISIBLE);
        boolean IS_PRIME = isPrime();
        if (IS_PRIME) {
            cheatDisplay.setText(R.string.trueString);
        }
        else {
            cheatDisplay.setText(R.string.falseString);
        }
        cheatDisplay.setVisibility(View.VISIBLE);
    }

    /**
     * This method is called during application destruction and is used to
     * store the instance state so as to be able to restore it at the next
     * initialization.
     * @param savedInstanceState The saved instance state of the application.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("IsCheatTaken", IS_CHEAT_TAKEN);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * This method sets the values in the Intent to be returned to MainActivity
     * and ends the Activity by calling finish().
     */
    private void setIntentValues(){
        Intent intent = getIntent();
        if (IS_CHEAT_TAKEN) {
            setResult(RESULT_OK, intent);
        }
        else {
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }

    /**
     * This is the onClick handler for the "Show Cheat" button. It calls the
     * showCheat() method after setting the cheat state to true.
     * @param view The View instance passed to this method.
     */
    public void onShowCheat(View view) {
        IS_CHEAT_TAKEN = true;
        showCheat();
    }

    /**
     * This method checks if the RANDOM_NUMBER is prime or not and returns a
     * Boolean value.
     * @return Boolean value
     */
    private boolean isPrime() {
        for (int Divisor = 2; Divisor < RANDOM_NUMBER / 2; Divisor++) {
            if (RANDOM_NUMBER % Divisor == 0) return false;
        }
        return true;
    }

    /**
     * This method is called when the back button on the action bar is pressed.
     * It calls finish() to end this activity and return to the MainActivity.
     * @param item The menu item instance passed to this method.
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setIntentValues();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is the onClick handler for the device back button. It sets
     * the intent values by calling setIntentValues().
     */
    @Override
    public void onBackPressed() {
        setIntentValues();
    }
}
