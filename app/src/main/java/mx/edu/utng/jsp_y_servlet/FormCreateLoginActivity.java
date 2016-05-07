package mx.edu.utng.jsp_y_servlet;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mx.edu.utng.jsp_y_servlet.dao.DBHelper;
import mx.edu.utng.jsp_y_servlet.dao.UsuarioDAOImpl;
import mx.edu.utng.jsp_y_servlet.model.Usuario;
import mx.edu.utng.jsp_y_servlet.util.DBAdapter;

/**
 * Created by NORMA on 07/02/2016.
 */
public class FormCreateLoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtCreateUser,edtCreatePass,edtCreateCorreo;
    private Button btnGuardar;
    private Button btnCancelar;
   // private MediaPlayer mp;

    private SQLiteDatabase db;
    private Usuario usuario;
    private DBHelper dbHelper;
    private UsuarioDAOImpl dao;
    private DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_create_login_layout);
        dbAdapter=new DBAdapter(this);
        dbAdapter.open();

        dbHelper=new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        dao=new UsuarioDAOImpl();

        initComponents();


    }
    private void initComponents(){
        edtCreateUser=(EditText) findViewById(R.id.edt_create_user);
        edtCreatePass=(EditText) findViewById(R.id.edt_create_pass);
        edtCreateCorreo=(EditText) findViewById(R.id.edt_create_mail);
        btnGuardar= (Button) findViewById(R.id.btn_create_login_guardar);
        btnCancelar= (Button) findViewById(R.id.btn_create_login_cancelar);

        dbHelper= new DBHelper(this);
        db= dbHelper.getWritableDatabase();
        dao= new UsuarioDAOImpl();

       // mp=MediaPlayer.create(this,R.raw.button);

        btnGuardar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        //mp.start();
        if(v.getId()==R.id.btn_create_login_guardar){
            usuario = new Usuario(
                    0,
                    edtCreateUser.getText().toString(),
                    edtCreateCorreo.getText().toString(),
                    edtCreatePass.getText().toString()
            );

            if (usuario.getIdUsuario()==0){
                dao.agregar(usuario,db);
            }else{
                dao.modificar(usuario,db);
            }
            int[] datos=dbAdapter.login(edtCreateUser.getText().toString(),edtCreatePass.getText().toString());

            //En este apartado se van agregar los modulos y temas
            dbAdapter.agregarModulos(datos[1]);

            //se muestra el numero de modulos y temas creados en la base de datos

            int numeroModulos=dbAdapter.totalModulos();
            int numeroTemas=dbAdapter.totalTemas();
            Log.e("Tema", "onClick: "+numeroTemas);
            Log.e("Modulos", "onClick: "+numeroModulos);




           /* Toast toast = null;
            Toast.makeText(getApplicationContext(),getString(R.string.datos_guardados_correctamente)+edtCreateUser.getText(),
                    Toast.LENGTH_SHORT).show();*/
        }
        startActivity(new Intent(FormCreateLoginActivity.this, FormLoginActivity.class));
        limpiarCampos();
    }
    private void limpiarCampos(){
        edtCreateUser.setText("");
        edtCreatePass.setText("");
    }
}
