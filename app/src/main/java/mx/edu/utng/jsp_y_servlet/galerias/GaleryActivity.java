package mx.edu.utng.jsp_y_servlet.galerias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;
import java.util.Timer;
import java.util.TimerTask;
import mx.edu.utng.jsp_y_servlet.R;

public class GaleryActivity extends AppCompatActivity {
    private ImageSwitcher imageSwitcher;
    private int[] gallery = {
            R.drawable.imag1,
            R.drawable.imag2,
            R.drawable.imag3,
            R.drawable.imag4,
            R.drawable.imag5,
            R.drawable.imag6,
            R.drawable.imag7,
            R.drawable.imag8,
            R.drawable.imag9 };

    private int position;
    private static final Integer DURATION = 2500;
    private Timer timer = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                return new ImageView(GaleryActivity.this);
            }
        });
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.left_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.left_out);
        imageSwitcher.setInAnimation(fadeIn);
        imageSwitcher.setOutAnimation(fadeOut);
    }
    public void start(View button) {
        if (timer != null) {
            timer.cancel();
        }
        position = 0;
        startSlider();
    }
    public void stop(View button) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
    public void startSlider() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                // avoid exception:
                // "Only the original thread that created a view hierarchy can touch its views"
                runOnUiThread(new Runnable() {
                    public void run() {
                        imageSwitcher.setImageResource(gallery[position]);
                        position++;
                        if (position == gallery.length) {
                            position = 0;
                        }
                    }
                });
            }

        }, 0, DURATION);
    }
    // Stops the slider when the Activity is going into the background
    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (timer != null) {
            startSlider();
        }
    }
}