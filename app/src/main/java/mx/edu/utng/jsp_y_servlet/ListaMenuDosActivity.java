package mx.edu.utng.jsp_y_servlet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by NORMA on 02/05/2016.
 */
public class ListaMenuDosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private String[] contenidoLista;
    private ListView lsvMenuDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_menu_r_dos_layout);
        initComponents();
    }

    private void initComponents() {
        lsvMenuDos=(ListView)findViewById(R.id.lsv_menu_dos);
        contenidoLista=getResources().getStringArray(R.array.opciones_r2);
        ArrayAdapter arrayAdapter=new ArrayAdapter<String>(getApplication(),R.layout.item_principal_layout,R.id.txv_item, contenidoLista);
        lsvMenuDos.setAdapter(arrayAdapter);
        lsvMenuDos.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(ListaMenuDosActivity.this,CalculadoraActivity.class));
                break;
            case 1://Galerias
                startActivity(new Intent(ListaMenuDosActivity.this,GaleriasActivity.class));
                break;
            case 2://temas
                startActivity(new Intent(ListaMenuDosActivity.this,TemaActivity.class));
                break;
            case 3://cronometro
                startActivity(new Intent(ListaMenuDosActivity.this,ChronometerActivity.class));
                break;
            case 4:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=HJLgFets3Xw")));
                break;
        }
    }
}