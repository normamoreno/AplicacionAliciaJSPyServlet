package mx.edu.utng.jsp_y_servlet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import mx.edu.utng.jsp_y_servlet.util.DBAdapter;

/**
 * Created by NORMA on 06/02/2016.
 */
public class SeleccionPrincipalActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txvTituloContenido;
    private TextView txvInformacionContenido;
    private Bundle valoresRecibidosP;
    protected String contenido = "";
    private Bundle bundle;
    private Button btn_pregunta;
    private DBAdapter dbadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido);
        initComponents();
        ;
    }

    private void initComponents() {
        dbadapter = new DBAdapter(this);
        dbadapter.open();
        txvTituloContenido = (TextView) findViewById(R.id.txv_titulo_contenido);
        txvInformacionContenido = (TextView) findViewById(R.id.txv_informacion_contenido);
        btn_pregunta = (Button) findViewById(R.id.btn_pregunta);
        btn_pregunta.setOnClickListener(this);
        valoresRecibidosP = getIntent().getExtras();
        txvTituloContenido.setText(valoresRecibidosP.getString("temaElegido"));
        bundle = new Bundle();
        bundle.putInt("lenguaje", valoresRecibidosP.getInt("lenguaje"));
        bundle.putInt("posicionTema", valoresRecibidosP.getInt("posicionTema"));
        String id=String.valueOf(FormLoginActivity.ID_U);
       // Log.e("Calificación", "initComponents: "+cal);
        switch (valoresRecibidosP.getInt("lenguaje")) {// para saber de que lista es
            case R.id.lsv_menu_principal_servlet:
                switch (valoresRecibidosP.getInt("posicionTema")) {//Para saber que tema de la lista fue oprimido e insertar informacion
                    case 1:
                        contenido = getResources().getString(R.string.contenido_servlet_q_es_servlet);
                        break;
                    case 2:
                        contenido = getResources().getString(R.string.caracteristicas_servlets);
                        break;
                    case 3:
                         contenido = getResources().getString(R.string.estructura_basica);
                         break;
                    case 4:
                        contenido = getResources().getString(R.string.ciclo_servlet);
                        break;
                    case 5:
                        contenido = getResources().getString(R.string.software_necesario);
                        break;
                    case 6:
                        contenido = getResources().getString(R.string.tipo_peticion);
                        break;
                    case 7:
                        contenido = getResources().getString(R.string.datos_formulario);
                        break;
                    case 8:
                        contenido = getResources().getString(R.string.objeto_cookie);
                        break;
                  /*  case 9:
                        contenido = getResources().getString(R.string.interfaz_http);
                        break;*/
                    default:
                        break;

                }
                break;
            case R.id.lsv_menu_principal_jsp:
                switch (valoresRecibidosP.getInt("posicionTema")) {
                    case 1:
                        contenido = getResources().getString(R.string.contenido_jsp);
                        break;
                    case 2:
                        contenido = getResources().getString(R.string.ciclo_jsp);
                        break;
                    case 3:
                        contenido = getResources().getString(R.string.objetos_implicitos);
                        break;
                    case 4:
                        contenido = getResources().getString(R.string.ambitos_atributos);
                        break;
                    case 5:
                        contenido = getResources().getString(R.string.lenguaje_jsp);
                        break;
                    case 6:
                        contenido = getResources().getString(R.string.comentarios);
                        break;
                    case 7:
                        contenido = getResources().getString(R.string.directrices);
                        break;
                    case 8:
                        contenido = getResources().getString(R.string.integracion_servlets_jsp);
                        break;

                    default:
                        break;

                }
                break;
            default:
                contenido = "No entro";
                break;
        }
        //contenido="Hola";
        txvInformacionContenido.setText(contenido);

    }

    @Override
    public void onClick(View v) {
        int cal=1;
        Log.e("Calificación", "initComponents: "+cal);
        startActivity(new Intent(SeleccionPrincipalActivity.this, QuizActivity.class).putExtras(bundle));

    }
}