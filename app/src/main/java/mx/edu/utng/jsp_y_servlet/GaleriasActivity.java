package mx.edu.utng.jsp_y_servlet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mx.edu.utng.jsp_y_servlet.galerias.GaleriaTresActivity;
import mx.edu.utng.jsp_y_servlet.galerias.GaleryActivity;
import mx.edu.utng.jsp_y_servlet.galerias.GaleryUTNGActivity;

public class GaleriasActivity extends AppCompatActivity implements View.OnClickListener{

    private Button a;
    private Button b;
    private Button c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerias);

        a=(Button)findViewById(R.id.calculadora);
        b=(Button)findViewById(R.id.cronometro);
        c=(Button)findViewById(R.id.btn_galeria_tres);

        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.calculadora:
                startActivity(new Intent(this,GaleryActivity.class));
                break;
            case R.id.cronometro:
                startActivity(new Intent(this,GaleryUTNGActivity.class));
                break;
            case R.id.btn_galeria_tres:
                startActivity(new Intent(this,GaleriaTresActivity.class));
                break;
        }
    }
}
