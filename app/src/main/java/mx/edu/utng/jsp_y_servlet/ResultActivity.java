package mx.edu.utng.jsp_y_servlet;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends Activity {
	private TextView txvNumeroPreCorrec;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
//get rating bar object
		RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
		txvNumeroPreCorrec=(TextView)findViewById(R.id.txv_numero_preguntas_correctas);
//get text view
//get score
		Bundle b = getIntent().getExtras();////////-------------------------Resive la info de la base de datos
		int score= b.getInt("score");
		txvNumeroPreCorrec.setText("Obtuviste \""+score+"\" preguntas bien de 4");
//display score
		bar.setRating(score);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}
}
