package mx.edu.utng.jsp_y_servlet;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import mx.edu.utng.jsp_y_servlet.util.DBAdapter;

/**
 * Created by NORMA on 07/02/2016.
 */
public class TabActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Intent intent;
    private ListView lsvMenuPrincipalServlet;
    private ListView lsvMenuPrincipalJsp;
    private String [] contenidoMenuServlet;
    private String [] contenidoMenuJsp;
    private Bundle bundle;
    private ProgressDialog _progressDialog;
    private int _progress = 0;
    private Handler _progressHandler;
    private MediaPlayer mp;
    private Bundle valoresRecibidos;
    private DBAdapter dbAdapter;
    //temas
    private LinearLayout lnUno;
    private LinearLayout lnDos;

    private int repetir=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setProgressBarIndeterminateVisibility(true);
        setContentView(R.layout.tab_layout);
        initComponents();
    }
    private void initComponents(){
        dbAdapter=new DBAdapter(this);
        dbAdapter.open();
        int numTema=dbAdapter.tema();

        mp = new MediaPlayer().create(this, R.raw.imagine);
        bundle = getIntent().getExtras();
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        Resources resources = getResources();
            //Para el tab de servlet
        lsvMenuPrincipalServlet= (ListView) findViewById(R.id.lsv_menu_principal_servlet);
        lsvMenuPrincipalJsp= (ListView) findViewById(R.id.lsv_menu_principal_jsp);

        TabHost.TabSpec spec=tabHost.newTabSpec(getString(R.string.tab_servlet));
        spec.setContent(R.id.tab_servlet);
        spec.setIndicator(getString(R.string.tab_servlet), resources.getDrawable(android.R.drawable.ic_input_get));
        tabHost.addTab(spec);

        //Para el tab de jsp
        spec=tabHost.newTabSpec(getString(R.string.tab_jsp));
        spec.setContent(R.id.tab_jsp);
        spec.setIndicator(getString(R.string.tab_jsp), resources.getDrawable(android.R.drawable.presence_video_online));
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);

        lnUno=(LinearLayout)findViewById(R.id.tab_jsp);
        lnDos=(LinearLayout)findViewById(R.id.tab_servlet);
        switch (numTema){
            case 1:
                lnUno.setBackgroundResource(R.drawable.fondoinicio);
                lnDos.setBackgroundResource(R.drawable.fondoinicio);
                break;
            case 2:
                lnUno.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                lnDos.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                break;
            case 3:
                lnUno.setBackgroundColor(getResources().getColor(R.color.colorPurpleFor));
                lnDos.setBackgroundColor(getResources().getColor(R.color.colorPurpleFor));
                break;
            case 4:
                lnUno.setBackgroundColor(getResources().getColor(R.color.colorPinke));
                lnDos.setBackgroundColor(getResources().getColor(R.color.colorPinke));
                break;
        }

        contenidoMenuServlet = getResources().getStringArray(R.array.item_menu_secundario_servlet);
        contenidoMenuJsp = getResources().getStringArray(R.array.item_menu_secundario_jsp);

        ArrayAdapter adapter= new ArrayAdapter(
                getApplication(),R.layout.item_principal_layout,R.id.txv_item, contenidoMenuServlet);
        lsvMenuPrincipalServlet.setAdapter(adapter);

        adapter= new ArrayAdapter(
                getApplication(),R.layout.item_principal_layout,R.id.txv_item, contenidoMenuJsp);
        lsvMenuPrincipalJsp.setAdapter(adapter);

        lsvMenuPrincipalServlet.setOnItemClickListener(this);
        lsvMenuPrincipalJsp.setOnItemClickListener(this);

        valoresRecibidos = getIntent().getExtras();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {
        if (position==0 ){
            if (parent.getId()==R.id.lsv_menu_principal_servlet){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dYK2MZIOAm4")));
            }else {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=1aYIxS8m8qw")));
            }
//            startActivity(new Intent(TabActivity.this,VideoIntroduccionActivity.class));
        }else{
            bundle = new Bundle();
            bundle.putInt("lenguaje", parent.getId());
            Log.i("Ids", "Parent: " + parent.getId() + ", Servlet:" + R.id.lsv_menu_principal_servlet);
            if(parent.getId() == R.id.lsv_menu_principal_servlet){//para comparar si fue oprimida la lista de servlet
                bundle.putString("temaElegido", contenidoMenuServlet[position]);//Para enviar el nombre del tema oprimido
                bundle.putInt("posicionTema", position);//Para enviar la posicion del tema seleccionado


            }else{//para comparar si fue oprimida la lista de jsp
                bundle.putString("temaElegido", contenidoMenuJsp[position]);//Para traer el nombre del tema oprimido
                bundle.putInt("posicionTema", position);;//Para enviar la posicion del tema seleccionado


            }
            boolean activo;
            String idUsu=String.valueOf(FormLoginActivity.ID_U);
            int idMos=dbAdapter.idPrimerModuloIns(idUsu,"Modulo_1");
            if (parent.getId()==R.id.lsv_menu_principal_servlet){
                switch (position){
                    case 1:
                        activo=dbAdapter.temaActivo(1,1,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        activo=dbAdapter.temaActivo(1,2,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        activo=dbAdapter.temaActivo(1,3,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 4:
                        activo=dbAdapter.temaActivo(1,4,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 5:
                        activo=dbAdapter.temaActivo(1,5,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 6:
                        activo=dbAdapter.temaActivo(1,6,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 7:
                        activo=dbAdapter.temaActivo(1,7,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                  case 8:
                        activo=dbAdapter.temaActivo(1,8,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }else {
                switch (position){
                    case 1:
                        activo=dbAdapter.temaActivo(2,1,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case 2:
                        activo=dbAdapter.temaActivo(2,2,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        activo=dbAdapter.temaActivo(2,3,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 4:
                        activo=dbAdapter.temaActivo(2,4,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 5:
                        activo=dbAdapter.temaActivo(2,5,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 6:
                        activo=dbAdapter.temaActivo(2,6,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 7:
                        activo=dbAdapter.temaActivo(2,7,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 8:
                        activo=dbAdapter.temaActivo(2,8,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;
                   /* case 9:
                        activo=dbAdapter.temaActivo(2,9,idMos);
                        if (activo){
                            startActivity(new Intent(TabActivity.this, SeleccionPrincipalActivity.class).putExtras(bundle));
                        }else {
                            Toast.makeText(TabActivity.this, "Tema bloqueado", Toast.LENGTH_SHORT).show();
                        }
                        break;*/
                }
            }
//            intent = new Intent(TabActivity.this, SeleccionPrincipalActivity.class);
 //           intent.putExtras(bundle);
 //           startActivity(intent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        if (valoresRecibidos.getBoolean("islogeo")){
            inflater.inflate(R.menu.menu_uno, menu);
        }else {
            inflater.inflate(R.menu.menu_sin_privilegios, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (valoresRecibidos.getBoolean("islogeo")){
            switch (item.getItemId()){
                case R.id.itm_progreso:
                    String idU=String.valueOf(FormLoginActivity.ID_U);
                    int prog=
                    dbAdapter.getProgresoTotal(idU);
                    showDialog(1);
                    _progress = 0;
                    _progressDialog.setProgress(prog);
                    //_progressHandler.sendEmptyMessage(0);

                    _progressHandler = new Handler() {
                        public void handleMessage(Message msg)
                        {
                            super.handleMessage(msg);
                            if (_progress >= 100) {
                                _progressDialog.dismiss();
                            } else {
                                _progress++;
                                //_progressDialog.incrementProgressBy(1);
                                _progressHandler.sendEmptyMessageDelayed(50, 100);
                            }
                        }
                    };
                    break;
                case R.id.itm_video:
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=KocrgtaJOMA&list=PLF3B8F51AC18455EA")));
                    break;
                case R.id.itm_ayuda_sugerencias:
                    startActivity(new Intent(this, MenuAyudaActivity.class));
                    break;
                case R.id.itm_desarrolladora:
                    startActivity(new Intent(this, MenuDesarrolladoraActivity.class));
                    break;
                case R.id.itm_configuracion:
                    startActivity(new Intent(this, SettingsActivity.class));
                    break;
                case R.id.itm_grafica:
                    startActivity(new Intent(this, GraficaMenuActivity.class));
                    break;
                case R.id.itm_google_maps:
                    startActivity(new Intent(this, MapsActivity.class));
                    break;
                case R.id.itm_sonido:
                    if (repetir==0){
                        mp.start();
                        repetir = 1;
                    }else if (repetir == 1){
                        mp.pause();
                        repetir = 0;
                    }

                    break;
                case R.id.itm_salir:
                    itmSalir();
                    break;
                case R.id.itm_evio_correo:
                    ////////////////////////////////////////////////////////////////////////////////////////////////
                    String para = FormLoginActivity.MAIL;
                    String idUSU=String.valueOf(FormLoginActivity.ID_U);
                    int idMos= dbAdapter.idPrimerModuloIns(idUSU, "Modulo_1");
                    int cal1Mod1=dbAdapter.mostrarCalificacion(idMos, 1, 1);
                    int cal2Mod1=dbAdapter.mostrarCalificacion(idMos, 1, 2);
                    int cal3Mod1=dbAdapter.mostrarCalificacion(idMos, 1, 3);
                    int cal4Mod1=dbAdapter.mostrarCalificacion(idMos, 1, 4);
                    int cal5Mod1=dbAdapter.mostrarCalificacion(idMos, 1, 5);
                    int cal6Mod1=dbAdapter.mostrarCalificacion(idMos, 1, 6);
                    int cal7Mod1=dbAdapter.mostrarCalificacion(idMos, 1, 7);
                    int cal8Mod1=dbAdapter.mostrarCalificacion(idMos, 1, 8);
                    //------------------
                    int cal1Mod2=dbAdapter.mostrarCalificacion(idMos,2,1);
                    int cal2Mod2=dbAdapter.mostrarCalificacion(idMos,2,2);
                    int cal3Mod2=dbAdapter.mostrarCalificacion(idMos,2,3);
                    int cal4Mod2=dbAdapter.mostrarCalificacion(idMos,2,4);
                    int cal5Mod2=dbAdapter.mostrarCalificacion(idMos,2,5);
                    int cal6Mod2=dbAdapter.mostrarCalificacion(idMos,2,6);
                    int cal7Mod2=dbAdapter.mostrarCalificacion(idMos,2,7);
                    int cal8Mod2=dbAdapter.mostrarCalificacion(idMos,2,8);
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.setType("plain/text");
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{para});
                    //email.putExtra(Intent.EXTRA_STREAM,Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.certificado));
                    email.putExtra(Intent.EXTRA_SUBJECT,"Estatus del curso ");
                    email.putExtra(Intent.EXTRA_TEXT, "Modulo JSP \n" +
                            " \n\tTema 1:-->"+cal1Mod1+
                            " \n\tTema 2:-->"+cal2Mod1+
                            " \n\tTema 3:-->"+cal3Mod1+
                            " \n\tTema 4:-->"+cal4Mod1+
                            " \n\tTema 5:-->"+cal5Mod1+
                            " \n\tTema 6:-->"+cal6Mod1+
                            " \n\tTema 7:-->"+cal7Mod1+
                            " \n\tTema 8:-->"+cal8Mod1+
                            "\n\nModulo Servlet"+
                            " \n\tTema 1:-->"+cal1Mod2+
                            " \n\tTema 2:-->"+cal2Mod2+
                            " \n\tTema 3:-->"+cal3Mod2+
                            " \n\tTema 4:-->"+cal4Mod2+
                            " \n\tTema 5:-->"+cal5Mod2+
                            " \n\tTema 6:-->"+cal6Mod2+
                            " \n\tTema 7:-->"+cal7Mod2+
                            " \n\tTema 8:-->"+cal8Mod2
                    );

                    //email.setType("image/png");
                    email.setType("message/rfc822");

                    startActivity(Intent.createChooser(email,"Elige alguno"));
                    ////////////////////////////////////////////////////////////////////////////////////////////////
                    break;
                case R.id.itm_juego:
                    startActivity(new Intent(TabActivity.this,ListaJuegoActivity.class));
                    break;
                case R.id.itm_menu_r2:
                    startActivity(new Intent(TabActivity.this, ListaMenuDosActivity.class));
                    break;
                default:
                    break;
            }
        }else {
            switch (item.getItemId()){
                case R.id.itm_video:
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=KocrgtaJOMA&list=PLF3B8F51AC18455EA")));
                    break;
                case R.id.itm_desarrolladora:
                    startActivity(new Intent(this, MenuDesarrolladoraActivity.class));
                    break;
                case R.id.itm_salir:
                    itmSalir();
                    break;
                case R.id.itm_video_promocional:
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=HJLgFets3Xw")));
                    break;
            }

        }
        return super.onOptionsItemSelected(item);
    }
    private void itmSalir(){
        finish();
        Intent intent1=new Intent(Intent.ACTION_MAIN);
        intent1.addCategory(Intent.CATEGORY_HOME);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent1);
    }

    @Override
    protected Dialog onCreateDialog(int i)
    {
        _progressDialog = new ProgressDialog(this);
        _progressDialog.setTitle(getString(R.string.title_progres));
        _progressDialog.setIcon(R.mipmap.ic_launcher);
        _progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        _progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.btn_aceptar), new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(getBaseContext(),
                                R.string.btn_Aceptar, Toast.LENGTH_SHORT).show();

                    }
                });
                    return _progressDialog;

                }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp.isPlaying()){
            mp.pause();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        int numTema=dbAdapter.tema();
        switch (numTema){
            case 1:
                lnUno.setBackgroundResource(R.drawable.fondoinicio);
                lnDos.setBackgroundResource(R.drawable.fondoinicio);
                break;
            case 2:
                lnUno.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                lnDos.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                break;
            case 3:
                lnUno.setBackgroundColor(getResources().getColor(R.color.colorPurpleFor));
                lnDos.setBackgroundColor(getResources().getColor(R.color.colorPurpleFor));
                break;
            case 4:
                lnUno.setBackgroundColor(getResources().getColor(R.color.colorPinke));
                lnDos.setBackgroundColor(getResources().getColor(R.color.colorPinke));
                break;
        }
    }
}
