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
    }

    private void setIntentValues(){
        Intent intent = getIntent();
        setResult(RESULT_OK, intent);
        finish();
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
