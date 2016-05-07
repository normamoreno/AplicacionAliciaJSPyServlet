package mx.edu.utng.jsp_y_servlet;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by NORMA on 21/02/2016.
 */
public class HomeActivity extends Activity {

    private static final long SPLASH_SCREEN_DELAY=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.home_layout);

        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent=new Intent().setClass(
                        HomeActivity.this,FormLoginActivity.class);
                startActivity(mainIntent);
                finish();
            }
        };

        Timer timer=new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}
