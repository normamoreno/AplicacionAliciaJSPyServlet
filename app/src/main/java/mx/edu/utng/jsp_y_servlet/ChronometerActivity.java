package mx.edu.utng.jsp_y_servlet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;

public class ChronometerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);
    }
    public void startChronometer(View view) {
        ((Chronometer) findViewById(R.id.chronometer1)).start();
    }

    public void stopChronometer(View view) {
        ((Chronometer) findViewById(R.id.chronometer1)).stop();
    }
}
