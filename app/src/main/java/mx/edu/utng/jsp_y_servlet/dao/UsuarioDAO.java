package mx.edu.utng.jsp_y_servlet.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mx.edu.utng.jsp_y_servlet.model.Usuario;


/**
 * Created by NORMA on 23/02/2016.
 */
public interface UsuarioDAO {
    void agregar(Usuario usuario, SQLiteDatabase db);
    void modificar(Usuario usuario, SQLiteDatabase db);
    void eliminar(Usuario usuario, SQLiteDatabase db);
    Cursor listar(SQLiteDatabase db);
}
