package io.github.divayprakash.isprime;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <h1>MainActivity</h1>
 * The MainActivity class implements all the control logic for the 'Prime or
 * Not' Android application.
 *
 * @author Divay Prakash
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The {@link int} instance representing the random number currently being
     * shown in the app.
     */
    private int RANDOM_NUMBER = 1000;
    /**
     * The {@link Boolean} instance representing if the currently displayed
     * number is prime or not.
     */
    private boolean IS_PRIME = isPrime();
    /**
     * The {@link TextView} instance representing the object to display the
     * random number in.
     */
    private TextView numberDisplay;
    /**
     * The {@link Vibrator} instance to enable vibration of device when needed.
     */
    private Vibrator vibratorInstance;

    /**
     * <h3>OnCreate()</h3>
     * This method is called at the startup of the application. It initializes
     * the random number using parameter savedInstanceState and also assigns
     * the Android VIBRATOR_SERVICE to the {@link Vibrator} instance variable using
     * getSystemService().
     * @param savedInstanceState The saved instance state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberDisplay = (TextView)findViewById(R.id.numberDisplay);
        if (savedInstanceState == null) {
            RANDOM_NUMBER = returnRandom();
            IS_PRIME = isPrime();
            numberDisplay.setText(String.format(Locale.US, "%d", RANDOM_NUMBER));
            numberDisplay.setTextColor(Color.parseColor("#FF000000"));
        }
        else {
            RANDOM_NUMBER = savedInstanceState.getInt("RandomNumber");
            numberDisplay.setText(String.format(Locale.US, "%d", RANDOM_NUMBER));
            numberDisplay.setTextColor(Color.parseColor("#FF000000"));
        }
        vibratorInstance = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**
     * <h3>OnSaveInstanceState()</h3>
     * This method is called during application destruction and is used to
     * store the instance state so as to be able to restore it at the next
     * initialization.
     * @param savedInstanceState The saved instance state of the application.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("RandomNumber", RANDOM_NUMBER);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * <h3>onNext()</h3>
     * This is the onClick action handler for the "Next" button. It calls the
     * {@link #returnRandom()} method to assign a new random value to the
     * {@link #RANDOM_NUMBER} variable. Thereafter, it calls the {@link #isPrime()}
     * method to determine if {@link #RANDOM_NUMBER} is prime or not. It then sets
     * the {@link #numberDisplay} {@link TextView}.
     * @param view The View instance passed to this method.
     */
    @SuppressWarnings("unused")
    public void onNext(View view) {
        RANDOM_NUMBER = returnRandom();
        IS_PRIME = isPrime();
        numberDisplay.setText(String.format(Locale.US, "%d", RANDOM_NUMBER));
        numberDisplay.setTextColor(Color.parseColor("#FF000000"));
    }

    /**
     * <h3>onTrue()</h3>
     * This is the onClick action handler for the "True" button. It checks the
     * {@link #IS_PRIME} variable to determine if the answer given is correct or not.
     * Accordingly, it sets the {@link #numberDisplay} {@link TextView} color using
     * TextView.setTextColor() method. It also displays a {@link Toast} with a
     * correct/incorrect message. In case of incorrect answer, it uses Vibrator.vibrate()
     * method to vibrate device for 500ms.
     * @param view The View instance passed to this method.
     */
    @SuppressWarnings("unused")
    public void onTrue(View view) {
        if (IS_PRIME) {
            Toast.makeText(this, "Your answer is correct!", Toast.LENGTH_SHORT).show();
            numberDisplay.setTextColor(Color.parseColor("#FF99CC00"));
        }
        else {
            Toast.makeText(this, "Your answer is incorrect!", Toast.LENGTH_SHORT).show();
            numberDisplay.setTextColor(Color.parseColor("#FFD50000"));
            vibratorInstance.vibrate(500);
        }
    }

    /**
     * <h3>onFalse()</h3>
     * This is the {@Link onClick} action handler for the "False" button. It checks the
     * {@Link #IS_PRIME} variable to determine if the answer given is correct or not.
     * Accordingly, it sets the {@Link #numberDisplay} {@Link TextView} color using
     * {@Link TextView.setTextColor()} method. It also displays a {@Link Toast} with a
     * correct/incorrect message. In case of incorrect answer, it uses {@Link Vibrator.vibrate()}
     * method to vibrate device for 500ms.
     * @param view The View instance passed to this method.
     */
    @SuppressWarnings("unused")
    public void onFalse(View view) {
        if (!IS_PRIME) {
            Toast.makeText(this, "Your answer is correct!", Toast.LENGTH_SHORT).show();
            numberDisplay.setTextColor(Color.parseColor("#FF99CC00"));
        }
        else {
            Toast.makeText(this, "Your answer is incorrect!", Toast.LENGTH_SHORT).show();
            numberDisplay.setTextColor(Color.parseColor("#FFD50000"));
            vibratorInstance.vibrate(500);
        }
    }

    /**
     * <h3>onExit()</h3>
     * This is the {@Link onClick} action handler for the "Exit" FAB button. It calls
     * the Android {@Link finish()} method to safely end application execution and
     * thereafter calls {@Link System.exit()} to clean up variables etc.
     * @param view The View instance passed to this method.
     */
    @SuppressWarnings("unused")
    public void onExit(View view) {
        finish();
        System.exit(0);
    }

    /**
     * <h3>isprime()</h3>
     * This method checks if the {@Link #RANDOM_NUMBER} is prime or not and returns
     * a {@Link Boolean} value.
     * @return {@Link Boolean} value
     */
    private boolean isPrime() {
        for (int Divisor = 2; Divisor < RANDOM_NUMBER / 2; Divisor++) {
            if (RANDOM_NUMBER % Divisor == 0) return false;
        }
        return true;
    }

    /**
     * <h3>returnRandom()</h3>
     * This method returns a Random intger in the range 2 to 1000 using
     * {@Link ThreadLocalRandom.current().nextInt()}.
     * @return {@Link int} value
     */
    private int returnRandom() {
        int MAX = 1000;
        int MIN = 2;
        return ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
    }
    
}
