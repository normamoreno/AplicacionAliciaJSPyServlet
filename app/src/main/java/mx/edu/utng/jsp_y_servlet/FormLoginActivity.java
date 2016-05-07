package mx.edu.utng.jsp_y_servlet;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import mx.edu.utng.jsp_y_servlet.dao.DBHelper;
import mx.edu.utng.jsp_y_servlet.dao.UsuarioDAOImpl;
import mx.edu.utng.jsp_y_servlet.model.Usuario;
import mx.edu.utng.jsp_y_servlet.util.DBAdapter;

/**
 * Created by NORMA on 07/02/2016.
 */
public class FormLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUser;
    private EditText edtPass;
    private Button btnIngresar;
    private Button btnMasTarde;
    private Button btnCrearCuenta;
    public static int ID_U=1;
    public static String MAIL="";
    //private MediaPlayer mp;

    private SQLiteDatabase db;
    private Usuario usuario;
    private DBHelper dbHelper;
    private UsuarioDAOImpl dao;
    private Bundle bundle;
    //temas
    private ScrollView lnUno;

    DBAdapter dbAdapter=new DBAdapter(this);
    DBHelperQuiz helper= new DBHelperQuiz(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_login_layout);
        lnUno=(ScrollView)findViewById(R.id.scrollView);

        int numTema=helper.tema();
        switch (numTema){
            case 1:
                lnUno.setBackgroundResource(R.drawable.fondoinicio);
                break;
            case 2:
                lnUno.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                break;
            case 3:
                lnUno.setBackgroundColor(getResources().getColor(R.color.colorPurpleFor));
                break;
            case 4:
                lnUno.setBackgroundColor(getResources().getColor(R.color.colorPinke));
                break;
        }
        initComponent();
    }
    private void initComponent(){
        bundle = new Bundle ();
        edtUser= (EditText) findViewById(R.id.edt_user);
        edtPass = (EditText) findViewById(R.id.edt_pass);

        btnIngresar= (Button) findViewById(R.id.btn_ingresar);
        btnMasTarde= (Button) findViewById(R.id.btn_mas_tarde);
        btnCrearCuenta= (Button) findViewById(R.id.btn_crear_cuenta);

       // mp=MediaPlayer.create(this,R.raw.button);
        dbHelper= new DBHelper(this);
        db= dbHelper.getWritableDatabase();
        dao= new UsuarioDAOImpl();
       dbAdapter=new DBAdapter(this);
        dbAdapter.open();


        btnIngresar.setOnClickListener(this);
        btnMasTarde.setOnClickListener(this);
        btnCrearCuenta.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
       // mp.start();
        if (v.getId()==R.id.btn_ingresar){
            Toast toast = null;
            String usuario,contrasena;
            usuario=edtUser.getText().toString();
            contrasena=edtPass.getText().toString();
            int[] bandera =dbAdapter.login(usuario,contrasena);
            if (bandera[0]==1) {
                //seseseses
                ID_U=bandera[1];
                String idUss=String.valueOf(ID_U);
                String[] datosUsuS=dbAdapter.informacionUsuario(idUss);
                MAIL=datosUsuS[1];

                bundle.putBoolean("islogeo", true);
                Toast.makeText(getApplicationContext(),
                        getString(R.string.txv_bienvenido)+edtUser.getText(),
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FormLoginActivity.this, TabActivity.class).putExtras(bundle));
            } else {
                Toast.makeText(getApplicationContext(),
                        R.string.error_autenticar,
                        Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId()==R.id.btn_mas_tarde){
            //startActivity(new Intent(FormLoginActivity.this, ListViewActivity.class));
            bundle.putBoolean("islogeo", false);
            startActivity(new Intent(FormLoginActivity.this, TabActivity.class).putExtras(bundle));
        }else if (v.getId()==R.id.btn_crear_cuenta){
            startActivity(new Intent(FormLoginActivity.this, FormCreateLoginActivity.class));

    }
        limpiarCampos();
    }

    private void limpiarCampos(){
        edtUser.setText("");
        edtPass.setText("");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        int numTema=helper.tema();
        switch (numTema){
            case 1:
                lnUno.setBackgroundResource(R.drawable.fondoinicio);
                break;
            case 2:
                lnUno.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                break;
            case 3:
                lnUno.setBackgroundColor(getResources().getColor(R.color.colorPurpleFor));
                break;
            case 4:
                lnUno.setBackgroundColor(getResources().getColor(R.color.colorPinke));
                break;
        }
    }
}