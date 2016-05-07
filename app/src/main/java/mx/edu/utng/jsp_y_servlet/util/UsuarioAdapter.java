package mx.edu.utng.jsp_y_servlet.util;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import mx.edu.utng.jsp_y_servlet.R;
import mx.edu.utng.jsp_y_servlet.dao.DBHelper;


/**
 * Created by NORMA on 23/02/2016.
 */
public class UsuarioAdapter extends CursorAdapter {

    public UsuarioAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_usuario_db_layout,null,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txvNombre= (TextView)view.findViewById(R.id.txv_nombre_db);

        txvNombre.setText(
                cursor.getString(cursor.getColumnIndex(DBHelper.NAME))+" "+
                cursor.getString(cursor.getColumnIndex(DBHelper.MAIL))+" "+
                cursor.getString(cursor.getColumnIndex(DBHelper.PASSWORD)));
    }
}
