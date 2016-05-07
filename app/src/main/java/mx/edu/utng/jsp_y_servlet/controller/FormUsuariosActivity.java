package mx.edu.utng.jsp_y_servlet.controller;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mx.edu.utng.jsp_y_servlet.R;
import mx.edu.utng.jsp_y_servlet.dao.DBHelper;
import mx.edu.utng.jsp_y_servlet.dao.UsuarioDAOImpl;
import mx.edu.utng.jsp_y_servlet.model.Usuario;


/**
 * Created by NORMA on 23/02/2016.
 */
public class FormUsuariosActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtNombre;
    private EditText edtCorreo;
    private EditText edtContrasena;
    private Button btnGuardar;
    private Button btnListar;

    private SQLiteDatabase db;
    private Usuario usuario;
    private DBHelper dbHelper;
    private UsuarioDAOImpl dao;
    private Bundle usuarioBundel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_usuario_db_layout);
        initComponents();
    }
    private void initComponents(){
        edtNombre=(EditText)findViewById(R.id.edt_nombre_usuario_db);
        edtCorreo=(EditText)findViewById(R.id.edt_correo_usuario_db);
        edtContrasena=(EditText)findViewById(R.id.edt_contrasena_usuario_db);

        btnGuardar=(Button)findViewById(R.id.btn_guardar_db);
        btnGuardar.setOnClickListener(this);

        btnListar=(Button)findViewById(R.id.btn_listar_db);
        btnListar.setOnClickListener(this);

        dbHelper= new DBHelper(this);
        db= dbHelper.getWritableDatabase();
        dao= new UsuarioDAOImpl();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_guardar_db:
                usuario = new Usuario(
                        0,
                        edtNombre.getText().toString(),
                        edtCorreo.getText().toString(),
                        edtContrasena.getText().toString()
                );

                if (usuarioBundel !=null){
                    usuario.setIdUsuario(usuarioBundel.getInt(DBHelper.ID));
                }

                if (usuario.getIdUsuario()==0){
                    dao.agregar(usuario,db);
                }else{
                    dao.modificar(usuario,db);
                }

                break;
            case R.id.btn_listar_db:
                startActivity(new Intent(FormUsuariosActivity.this,ListaUsuariosActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            usuarioBundel =getIntent().getExtras();
            edtNombre.setText(usuarioBundel.getString(DBHelper.NAME));
            edtCorreo.setText(usuarioBundel.getString(DBHelper.MAIL));
            edtContrasena.setText(usuarioBundel.getString(DBHelper.PASSWORD));
        }catch (Exception e){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_db, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_bebidas:
                startActivity(new Intent(this,FormTemasActivity.class));
                    return true;
            default:
            startActivity(new Intent(this,FormUsuariosActivity.class));
                return true;
        }
    }*/
}
