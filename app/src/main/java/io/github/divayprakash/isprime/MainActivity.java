package io.github.divayprakash.isprime;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The MainActivity class implements all the control logic for the 'Prime or
 * Not' Android application.
 *
 * @author Divay Prakash
 */
public class MainActivity extends AppCompatActivity {

    /**
     * int variable representing the random number currently being
     * shown in the app.
     */
    private int RANDOM_NUMBER = 1000;
    /**
     * Boolean variable representing if the currently displayed number is
     * prime or not.
     */
    private boolean IS_PRIME = isPrime();
    /**
     * TextView variable representing the object to display the random number
     * in.
     */
    private TextView numberDisplay;
    /**
     * Vibrator instance variable to enable vibration of device when needed.
     */
    private Vibrator vibratorInstance;

    private static final int HINT_REQUEST = 1;
    private boolean IS_HINT_TAKEN;
    private Button hintButton;

    private static final int CHEAT_REQUEST = 2;
    private boolean IS_CHEAT_TAKEN;
    private Button cheatButton;

    /**
     * This method is called at the startup of the application. It initializes
     * the random number using parameter savedInstanceState and also assigns
     * the Android VIBRATOR_SERVICE to the Vibrator instance variable using
     * getSystemService().
     * @param savedInstanceState The saved instance state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberDisplay = (TextView)findViewById(R.id.numberDisplay);
        hintButton = (Button)findViewById(R.id.hintButton);
        cheatButton = (Button)findViewById(R.id.cheatButton);
        if (savedInstanceState == null) {
            RANDOM_NUMBER = returnRandom();
            IS_PRIME = isPrime();
            setNumberDisplay();
            enableHintButton();
            enableCheatButton();
        }
        else {
            RANDOM_NUMBER = savedInstanceState.getInt("RandomNumber");
            setNumberDisplay();
            IS_HINT_TAKEN = savedInstanceState.getBoolean("IsHintTaken");
            if (IS_HINT_TAKEN) {
                disableHintButton();
            }
            else {
                enableHintButton();
            }
            IS_CHEAT_TAKEN = savedInstanceState.getBoolean("IsCheatTaken");
            if (IS_CHEAT_TAKEN) {
                disableCheatButton();
            }
            else {
                enableCheatButton();
            }
        }
        vibratorInstance = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**
     * This method is called during application destruction and is used to
     * store the instance state so as to be able to restore it at the next
     * initialization.
     * @param savedInstanceState The saved instance state of the application.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("RandomNumber", RANDOM_NUMBER);
        savedInstanceState.putBoolean("IsHintTaken", IS_HINT_TAKEN);
        savedInstanceState.putBoolean("IsCheatTaken", IS_CHEAT_TAKEN);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * This is the onClick action handler for the "Next" button. It calls the
     * returnRandom() method to assign a new random value to the RANDOM_NUMBER
     * variable. Thereafter, it calls the isPrime() method to determine if
     * RANDOM_NUMBER is prime or not. It then sets the numberDisplay TextView.
     * @param view The View instance passed to this method.
     */
    @SuppressWarnings("unused")
    public void onNext(View view) {
        RANDOM_NUMBER = returnRandom();
        IS_PRIME = isPrime();
        setNumberDisplay();
        IS_HINT_TAKEN = false;
        enableHintButton();
        IS_CHEAT_TAKEN = false;
        enableCheatButton();
    }

    /**
     * This is the onClick action handler for the "True" button. It checks the
     * IS_PRIME variable to determine if the answer given is correct or not.
     * Accordingly, it sets the numberDisplay TextView color using
     * TextView.setTextColor() method. It also displays a Toast with a
     * correct/incorrect message. In case of incorrect answer, it uses
     * Vibrator.vibrate() method to vibrate device for 500ms.
     * @param view The View instance passed to this method.
     */
    @SuppressWarnings("unused")
    public void onTrue(View view) {
        if (IS_PRIME) {
            makeToast(this, "Your answer is correct!");
            setNumberDisplayGreen();
        }
        else {
            makeToast(this, "Your answer is incorrect!");
            setNumberDisplayRed();
            vibratorInstance.vibrate(500);
        }
    }

    /**
     * This is the onClick action handler for the "False" button. It checks the
     * IS_PRIME variable to determine if the answer given is correct or not.
     * Accordingly, it sets the numberDisplay TextView color using
     * TextView.setTextColor() method. It also displays a Toast with a
     * correct/incorrect message. In case of incorrect answer, it uses
     * Vibrator.vibrate() method to vibrate device for 500ms.
     * @param view The View instance passed to this method.
     */
    @SuppressWarnings("unused")
    public void onFalse(View view) {
        if (!IS_PRIME) {
            makeToast(this, "Your answer is correct!");
            setNumberDisplayGreen();
        }
        else {
            makeToast(this, "Your answer is incorrect!");
            setNumberDisplayRed();
            vibratorInstance.vibrate(500);
        }
    }

    /**
     * This is the onClick action handler for the "Cheat" button. It creates an
     * instance of the Intent class and uses it to launch the CheatActivity.
     * @param view The view instance passed to this method.
     */
    @SuppressWarnings("unused")
    public void onCheat(View view) {
        Intent intent = new Intent(this, CheatActivity.class);
        intent.putExtra("RandomNumber", RANDOM_NUMBER);
        startActivityForResult(intent, CHEAT_REQUEST);
    }

    /**
     * This is the onClick action handler for the "Hint" button. It creates an
     * instance of the Intent class and uses it to launch the HintActivity.
     * @param view The view instance passed to this method.
     */
    @SuppressWarnings("unused")
    public void onHint(View view) {
        Intent intent = new Intent(this, HintActivity.class);
        startActivityForResult(intent, HINT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HINT_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    makeToast(this, "Hint Taken!");
                    IS_HINT_TAKEN = true;
                    disableHintButton();
                }
            }
        }
        else if (requestCode == CHEAT_REQUEST){
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    makeToast(this, "Cheat taken!");
                    IS_CHEAT_TAKEN = true;
                    disableCheatButton();
                }
            }
            else if (resultCode == RESULT_CANCELED) {
                if (data != null) {
                    makeToast(this, "Cheat not taken!");
                    IS_CHEAT_TAKEN = false;
                    enableCheatButton();
                }
            }
        }
    }

    private void enableCheatButton() {
        cheatButton.setEnabled(true);
        cheatButton.setBackgroundColor(Color.parseColor("#FFAA66CC"));
    }

    private void disableCheatButton() {
        cheatButton.setEnabled(false);
        cheatButton.setBackgroundColor(Color.parseColor("#FF616161"));
    }

    private void enableHintButton() {
        hintButton.setEnabled(true);
        hintButton.setBackgroundColor(Color.parseColor("#FF00DDFF"));
    }

    private void disableHintButton() {
        hintButton.setEnabled(false);
        hintButton.setBackgroundColor(Color.parseColor("#FF616161"));
    }

    private void setNumberDisplay() {
        numberDisplay.setText(String.format(Locale.US, "%d", RANDOM_NUMBER));
        numberDisplay.setTextColor(Color.parseColor("#FF000000"));
    }

    private void setNumberDisplayGreen() {
        numberDisplay.setTextColor(Color.parseColor("#FF99CC00"));
    }

    private void setNumberDisplayRed() {
        numberDisplay.setTextColor(Color.parseColor("#FFD50000"));
    }

    private void makeToast(Context context, String toastMsg) {
        Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show();
    }

    /**
     * This is the onClick action handler for the "Exit" FAB button. It calls
     * the Android finish() method to safely end application execution and
     * thereafter calls System.exit() to clean up variables etc.
     * @param view The View instance passed to this method.
     */
    @SuppressWarnings("unused")
    public void onExit(View view) {
        finish();
        System.exit(0);
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
     * This method returns a Random integer in the range 2 to 1000 using
     * ThreadLocalRandom.current().nextInt().
     * @return int value
     */
    private int returnRandom() {
        int MAX = 1000;
        int MIN = 2;
        return ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
    }

}
