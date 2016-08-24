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

    private TextView cheatTitle;
    private TextView cheatDisplay;
    private int RANDOM_NUMBER;
    private boolean IS_CHEAT_TAKEN = false;

    /**
     * This method is called at the startup of the application. It sets the
     * view to the XML file associated with this Activity. The method also
     * enables the back button on the action bar.
     * @param savedInstanceState The saved instance state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Intent intent = getIntent();
        RANDOM_NUMBER = intent.getIntExtra("RandomNumber", 2);
        TextView cheatTitle = (TextView)findViewById(R.id.cheatTitle);
        TextView cheatDisplay = (TextView)findViewById(R.id.cheatDisplay);
    }

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

    public void onShowCheat(View view) {
        IS_CHEAT_TAKEN = true;
        cheatTitle.setVisibility(View.VISIBLE);
        boolean IS_PRIME = isPrime();
        if (IS_PRIME) {
            cheatDisplay.setText("TRUE");
        }
        else {
            cheatDisplay.setText("FALSE");
        }
        cheatDisplay.setVisibility(View.VISIBLE);
    }

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

    @Override
    public void onBackPressed() {
        setIntentValues();
    }
}
