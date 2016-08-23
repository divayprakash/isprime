package io.github.divayprakash.isprime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * The SplashActivity class implements the control logic for the splash screen
 * displayed at the beginning of the 'Prime or Not' application.
 *
 * @author Divay Prakash
 */
public class SplashActivity extends Activity {

    /**
     * int variable representing the time in milliseconds for which the splash
     * screen is shown in the application.
     */
    private static final int SPLASH_TIME = 3000;

    /**
     * This method is called at the startup of the application. It sets the
     * view to the XML file associated with this Activity. Also, an Intent
     * instance is created to start the MainActivity after a set time duration
     * SPLASH_TIME. Thereafter the finish() method is called to end execution
     * of the SplashActivity.
     * @param savedInstanceState The saved instance state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME);
    }

}