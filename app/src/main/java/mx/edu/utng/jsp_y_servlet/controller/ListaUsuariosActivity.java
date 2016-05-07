package mx.edu.utng.jsp_y_servlet.controller;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import mx.edu.utng.jsp_y_servlet.R;
import mx.edu.utng.jsp_y_servlet.dao.DBHelper;
import mx.edu.utng.jsp_y_servlet.dao.UsuarioDAOImpl;
import mx.edu.utng.jsp_y_servlet.model.Usuario;
import mx.edu.utng.jsp_y_servlet.util.UsuarioAdapter;

/**
 * Created by NORMA on 23/02/2016.
 */
public class ListaUsuariosActivity extends AppCompatActivity {

    private ListView lsvUsuarios;
    private DBHelper dbHelper;
    private UsuarioDAOImpl dao;
    private Usuario usuario;
    private Cursor cursor;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_usuario_db_layout);
        initComponents();
    }
    private void initComponents(){
        lsvUsuarios = (ListView)findViewById(R.id.lsv_usuarios_db);
        dbHelper= new DBHelper(this);
        db= dbHelper.getWritableDatabase();
        dao=new UsuarioDAOImpl() ;
       listar();
        registerForContextMenu(lsvUsuarios);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo  info= (AdapterView.AdapterContextMenuInfo) menuInfo;
        cursor.moveToPosition(info.position);

        usuario =new Usuario(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );

        menu.setHeaderTitle("Opciones para: " + usuario.getNombre());
        menu.add(0, 1, 1,  "Modificar");
        menu.add(0, 2, 1, "Eliminar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId()==1){
        Bundle usuarioBundle=new Bundle();

            usuarioBundle.putInt(DBHelper.ID, usuario.getIdUsuario());
            usuarioBundle.putString(DBHelper.NAME, usuario.getNombre());
            usuarioBundle.putString(DBHelper.MAIL, usuario.getCorreo());
            usuarioBundle.putString(DBHelper.PASSWORD, usuario.getContrasena());
            startActivity(new Intent(ListaUsuariosActivity.this, FormUsuariosActivity.class).putExtras(usuarioBundle));
        }else if (item.getItemId()==2){
            dao.eliminar(usuario,db);
            listar();
        }
        return super.onContextItemSelected(item);
    }
    private void listar(){
        cursor= dao.listar(db);
        UsuarioAdapter adapter= new UsuarioAdapter(this,cursor);
        lsvUsuarios.setAdapter(adapter);

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
                startActivity(new Intent(this,ListaTemasActivity.class));
                return true;
            default:
                startActivity(new Intent(this,ListaUsuariosActivity.class));
                return true;
        }
    }*/

}
