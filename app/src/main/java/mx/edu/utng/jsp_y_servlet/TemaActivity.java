package mx.edu.utng.jsp_y_servlet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import mx.edu.utng.jsp_y_servlet.util.DBAdapter;

public class TemaActivity extends AppCompatActivity implements View.OnClickListener{
    DBAdapter helper;
    Button tema1;
    Button tema2;
    Button tema3;
    Button tema4;

    //private Bundle valoresEviadosDBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_opciones_menu);
        helper= new DBAdapter(this);
        helper.open();
      //  valoresEviadosDBundle=new Bundle();

        tema1=(Button)findViewById(R.id.btn_color_rosa);
        tema1.setOnClickListener(this);

        tema2=(Button)findViewById(R.id.btn_color_morado);
        tema2.setOnClickListener(this);

        tema3=(Button)findViewById(R.id.btn_color_gris);
        tema3.setOnClickListener(this);

        tema4=(Button)findViewById(R.id.btn_color_blanco);
        tema4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_color_rosa:
                helper.actualizarTema(1);
                //startActivity(new Intent(this, TabActivity.class).putExtras(valoresEviadosDBundle));
                //System.exit(0);
                break;
            case R.id.btn_color_morado:
                helper.actualizarTema(2);
                //startActivity(new Intent(this, TabActivity.class).putExtras(valoresEviadosDBundle));
                //System.exit(0);
                break;
            case R.id.btn_color_gris:
                helper.actualizarTema(3);
                //startActivity(new Intent(this, TabActivity.class).putExtras(valoresEviadosDBundle));
                //System.exit(0);
                break;
            case R.id.btn_color_blanco:
                helper.actualizarTema(4);
                //startActivity(new Intent(this, TabActivity.class).putExtras(valoresEviadosDBundle));
                //System.exit(0);
                break;
        }
        finish();
    }
}