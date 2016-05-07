package mx.edu.utng.jsp_y_servlet;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by NORMA on 02/05/2016.
 */
public class JuegoActivity extends AppCompatActivity {

    private Bundle valoresResividos;
    LinearLayout ly_contenedor;
    private String palabra ;
    private EditText controles[];
    private Button btnComprobar;
    private String[] respuesta;
    private String[] pregunta;
    private TextView txvPreguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego_layout);
        txvPreguntas=(TextView)findViewById(R.id.txv_pregunta);
        valoresResividos=getIntent().getExtras();
        respuesta=getResources().getStringArray(R.array.respuesta);
        pregunta=getResources().getStringArray(R.array.pregunta);
        palabra=respuesta[valoresResividos.getInt("posicion")];
        txvPreguntas.setText(pregunta[valoresResividos.getInt("posicion")]);
        ly_contenedor = (LinearLayout)findViewById(R.id.ly_contenedor);
        btnComprobar = (Button)findViewById(R.id.btn_comprobar);
        controles  = new EditText[palabra.length()];
        for (int fila = 0;  fila<controles.length; fila++){
            controles[fila] = new EditText(getApplicationContext());
            controles[fila].setTextColor(Color.BLACK);
            controles[fila].setHint(fila+"");
            ly_contenedor.addView(controles[fila]);
        }
        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( notBlank()){
                    if (getAllText().equals(palabra)){
                        Toast.makeText(getApplicationContext(), "Tu respuesta es correcta", Toast.LENGTH_LONG).show();
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),"Tu respuesta es incorrecta",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Llena todo los campos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private String getAllText() {

        String respuesta = "";
        for(int i = 0; i<controles.length; i++) {
            respuesta = respuesta + controles[i].getText().toString();
        }
        return respuesta;
    }

    private boolean notBlank(){
        boolean valor = true;

        for(int i = 0; i<controles.length; i++){
            if(controles[i].getText().toString().isEmpty()){
                valor = false;
                break;
            }
        }
        return valor;
    }
}