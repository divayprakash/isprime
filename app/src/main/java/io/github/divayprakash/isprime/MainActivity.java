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

public class MainActivity extends AppCompatActivity {

    private int RANDOM_NUMBER = 1000;
    private boolean IS_PRIME = isPrime();
    private TextView numberDisplay;
    private Vibrator v;

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
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("RandomNumber", RANDOM_NUMBER);
        super.onSaveInstanceState(savedInstanceState);
    }

    @SuppressWarnings("unused")
    public void onNext(View view) {
        RANDOM_NUMBER = returnRandom();
        IS_PRIME = isPrime();
        numberDisplay.setText(String.format(Locale.US, "%d", RANDOM_NUMBER));
        numberDisplay.setTextColor(Color.parseColor("#FF000000"));
    }

    @SuppressWarnings("unused")
    public void onTrue(View view) {
        if (IS_PRIME) {
            Toast.makeText(this, "Your answer is correct!", Toast.LENGTH_SHORT).show();
            numberDisplay.setTextColor(Color.parseColor("#FF99CC00"));
        }
        else {
            Toast.makeText(this, "Your answer is incorrect!", Toast.LENGTH_SHORT).show();
            numberDisplay.setTextColor(Color.parseColor("#FFD50000"));
            v.vibrate(500);
        }
    }

    @SuppressWarnings("unused")
    public void onFalse(View view) {
        if (!IS_PRIME) {
            Toast.makeText(this, "Your answer is correct!", Toast.LENGTH_SHORT).show();
            numberDisplay.setTextColor(Color.parseColor("#FF99CC00"));
        }
        else {
            Toast.makeText(this, "Your answer is incorrect!", Toast.LENGTH_SHORT).show();
            numberDisplay.setTextColor(Color.parseColor("#FFD50000"));
            v.vibrate(500);
        }
    }

    @SuppressWarnings("unused")
    public void onExit(View view) {
        finish();
        System.exit(0);
    }

    private boolean isPrime() {
        for (int Divisor = 2; Divisor < RANDOM_NUMBER / 2; Divisor++) {
            if (RANDOM_NUMBER % Divisor == 0) return false;
        }
        return true;
    }

    private int returnRandom() {
        int MAX = 1000;
        int MIN = 2;
        return ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
    }
    
}
