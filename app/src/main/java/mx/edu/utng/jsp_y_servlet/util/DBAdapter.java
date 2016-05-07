package mx.edu.utng.jsp_y_servlet.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.jsp_y_servlet.Question;
import mx.edu.utng.jsp_y_servlet.dao.DBHelper;
import mx.edu.utng.jsp_y_servlet.model.Modulo;
import mx.edu.utng.jsp_y_servlet.model.Tema;


/**
 * Created by NORMA on 07/03/2016.
 */
public class DBAdapter {
    SQLiteDatabase db;
    DBHelper dbHelper;
    Context context;


    public DBAdapter(Context c){
        this.context=c;
    }
    public DBAdapter open() throws SQLException{
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }



    public int[] login(String nomUsu,String contrUsu){
        Cursor cursor=db.rawQuery(
                "SELECT * FROM "+DBHelper.TABLE_NAME_1+" WHERE nombre=? AND contrasena=?",new String[]{nomUsu,contrUsu});
        int[] datosLogeo=new int[2];
        datosLogeo[0]=0;
        datosLogeo[1]=0;
        if (cursor!=null){
            if (cursor.getCount()>0){
                datosLogeo[0]=1;
                cursor.moveToFirst();
                datosLogeo[1]=cursor.getInt(cursor.getColumnIndex(DBHelper.ID));
                return datosLogeo;
            }

        }
        return datosLogeo;
    }

    //se crean los modulos con sus valores y despues se mandan al metodo agregarModulo(); para insertarlos a la base de datos 1 por 1
    public void agregarModulos(int idUsuario){
        Modulo modulo;

        modulo = new Modulo(idUsuario,"Modulo_1");//Modulo Servlet
        this.agregarModulo(modulo);
        modulo = new Modulo(idUsuario,"Modulo_2");//Modulo JSP
        this.agregarModulo(modulo);

        String idUsu = String.valueOf(idUsuario);
        int idModulo = idPrimerModuloIns(idUsu, "Modulo_1");

        //se agregan los temas con su respectivo Id de cada modulo
        agregarTemas(idModulo, 1);
        idModulo++;
        agregarTemas(idModulo, 2);


    }

    private void agregarModulo (Modulo modulo){
        ContentValues values = new ContentValues();
        values.put(DBHelper.NAME, modulo.getNombre());
        values.put(DBHelper.USER_ID, modulo.getIdUsuario());
        db.insert(DBHelper.TABLE_NAME_3, null, values);
    }

    private void agregarTemas (int idModulo, int numeroModulo){
        Tema tema;
        switch (numeroModulo){
            case 1: //Servlet
                tema = new Tema("Mod_1_Tem_1",idModulo,0,true);
                this.agregarTema(tema);
                tema = new Tema("Mod_1_Tem_2",idModulo,0,false);
                this.agregarTema(tema);
                tema = new Tema("Mod_1_Tem_3",idModulo,0,false);
                this.agregarTema(tema);
                tema = new Tema("Mod_1_Tem_4",idModulo,0,false);
                this.agregarTema(tema);
                tema = new Tema("Mod_1_Tem_5",idModulo,0,false);
                this.agregarTema(tema);
                tema = new Tema("Mod_1_Tem_6",idModulo,0,false);
                this.agregarTema(tema);
                tema = new Tema("Mod_1_Tem_7",idModulo,0,false);
                this.agregarTema(tema);
                tema = new Tema("Mod_1_Tem_8",idModulo,0,false);
                this.agregarTema(tema);
                /*tema = new Tema("Mod_1_Tem_9",idModulo,0,false);
                this.agregarTema(tema);*/
                break;
            case 2:
                tema = new Tema("Mod_2_Tem_1",idModulo,0,true);
                this.agregarTema(tema);
                tema = new Tema("Mod_2_Tem_2",idModulo,0,false);
                this.agregarTema(tema);
                tema = new Tema("Mod_2_Tem_3",idModulo,0,false);
                this.agregarTema(tema);
                tema = new Tema("Mod_2_Tem_4",idModulo,0,false);
                this.agregarTema(tema);
                tema = new Tema("Mod_2_Tem_5",idModulo,0,false);
                this.agregarTema(tema);
                tema = new Tema("Mod_2_Tem_6",idModulo,0,false);
                this.agregarTema(tema);
                tema = new Tema("Mod_2_Tem_7",idModulo,0,false);
                this.agregarTema(tema);
                tema = new Tema("Mod_2_Tem_8",idModulo,0,false);
                this.agregarTema(tema);
               /* tema = new Tema("Mod_2_Tem_9",idModulo,0,false);
                this.agregarTema(tema);
               tema = new Tema("Mod_2_Tem_10",idModulo,0,false);
                this.agregarTema(tema);*/
                break;
            default:
                Log.e("Nose insertaron temas", "agregarTemas: " );
                break;
        }

    }

    private void agregarTema (Tema tema){

        ContentValues values = new ContentValues();
        values.put(DBHelper.NAME, tema.getNombre());
        values.put(DBHelper.CALIF, tema.getCalificacion());
        values.put(DBHelper.ACTIVO, tema.isActivo() == true ? 1 : 0);
        values.put(DBHelper.MOD_ID, tema.getIdModulo());
        db.insert(DBHelper.TABLE_NAME_2, null, values);
    }

    public int idPrimerModuloIns(String idUsuario,String nombreModulo){
        Cursor cursor=db.rawQuery(
                "SELECT * FROM "+DBHelper.TABLE_NAME_3+" WHERE nombre LIKE ? AND  "+DBHelper.USER_ID+" = ?",new String[]{nombreModulo,idUsuario});
        int idMod=0;
        if (cursor!=null){
            cursor.moveToFirst();
            idMod=cursor.getInt(cursor.getColumnIndex(DBHelper.ID));
            return idMod;
        }
        return idMod;
    }

    //Hace una consulta para saber cun atos Modulos existen
    //sirve para comprobar si cuando se elimina el usuario se eliminan tambien sus modulos correspondientes
    public int totalModulos(){
        int row=0;
        Cursor cursor = db.rawQuery("SELECT  * FROM " + DBHelper.TABLE_NAME_3, null);
        row=cursor.getCount();
        return row;
    }
    //Hace una consulta para saber cunatos Temas existen
    //sirve para comprobar si cuando se elimina el usuario se eliminan tambien sus temas correspondientes
    public int totalTemas(){
        int row=0;
        Cursor cursor = db.rawQuery("SELECT  * FROM " + DBHelper.TABLE_NAME_2, null);
        row=cursor.getCount();
        return row;
    }

    //quiz
    /////Quizz

    //////////
    public List<Question> getAllQuestions( int dd) {

        List<Question> quesList = new ArrayList<Question>();
        String selectQuery="";
        Cursor cursor;
        switch (dd){
            case 0:
                selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_QUEST+" where "+DBHelper.ID+" <6 ";// se crea la query 1,2
                cursor = db.rawQuery(selectQuery, null);//Se hace la consulta
                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Question quest = new Question();
                        quest.setID(cursor.getInt(0));
                        quest.setQUESTION(cursor.getString(1));
                        quest.setANSWER(cursor.getString(2));
                        quest.setOPTA(cursor.getString(3));
                        quest.setOPTB(cursor.getString(4));
                        quest.setOPTC(cursor.getString(5));
                        quesList.add(quest);
                    } while (cursor.moveToNext());
                }

                break;
            case 1:

                selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_QUEST+" where "+DBHelper.ID+" >5 OR "+DBHelper.ID+" <11 ";// se crea la query 3, 4
                cursor = db.rawQuery(selectQuery, null);//Se hace la consulta
                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Question quest = new Question();
                        quest.setID(cursor.getInt(0));
                        quest.setQUESTION(cursor.getString(1));
                        quest.setANSWER(cursor.getString(2));
                        quest.setOPTA(cursor.getString(3));
                        quest.setOPTB(cursor.getString(4));
                        quest.setOPTC(cursor.getString(5));
                        quesList.add(quest);
                    } while (cursor.moveToNext());
                }

                break;
            case 2:

                selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_QUEST+" where "+DBHelper.ID+" >9 OR "+DBHelper.ID+" <16 ";// se crea la query 5,6
                cursor = db.rawQuery(selectQuery, null);//Se hace la consulta
                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Question quest = new Question();
                        quest.setID(cursor.getInt(0));
                        quest.setQUESTION(cursor.getString(1));
                        quest.setANSWER(cursor.getString(2));
                        quest.setOPTA(cursor.getString(3));
                        quest.setOPTB(cursor.getString(4));
                        quest.setOPTC(cursor.getString(5));
                        quesList.add(quest);
                    } while (cursor.moveToNext());
                }

                break;
            case 3:

                selectQuery = "SELECT  * FROM " +DBHelper.TABLE_NAME_QUEST+" where "+DBHelper.ID+" >15 OR "+DBHelper.ID+" <21 ";// se crea la query 7,8
                cursor = db.rawQuery(selectQuery, null);//Se hace la consulta
                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Question quest = new Question();
                        quest.setID(cursor.getInt(0));
                        quest.setQUESTION(cursor.getString(1));
                        quest.setANSWER(cursor.getString(2));
                        quest.setOPTA(cursor.getString(3));
                        quest.setOPTB(cursor.getString(4));
                        quest.setOPTC(cursor.getString(5));
                        quesList.add(quest);
                    } while (cursor.moveToNext());
                }

                break;
            case 4:

                selectQuery = "SELECT  * FROM " +DBHelper.TABLE_NAME_QUEST+" where "+DBHelper.ID+" >20 OR "+DBHelper.ID+" <26 ";// se crea la query 9,10
                cursor = db.rawQuery(selectQuery, null);//Se hace la consulta
                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Question quest = new Question();
                        quest.setID(cursor.getInt(0));
                        quest.setQUESTION(cursor.getString(1));
                        quest.setANSWER(cursor.getString(2));
                        quest.setOPTA(cursor.getString(3));
                        quest.setOPTB(cursor.getString(4));
                        quest.setOPTC(cursor.getString(5));
                        quesList.add(quest);
                    } while (cursor.moveToNext());
                }

                break;
            case 5:

                selectQuery = "SELECT  * FROM " +DBHelper.ID+" where "+DBHelper.ID+" >25 OR "+DBHelper.ID+" <31 ";// se crea la query 11,12
                cursor = db.rawQuery(selectQuery, null);//Se hace la consulta
                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Question quest = new Question();
                        quest.setID(cursor.getInt(0));
                        quest.setQUESTION(cursor.getString(1));
                        quest.setANSWER(cursor.getString(2));
                        quest.setOPTA(cursor.getString(3));
                        quest.setOPTB(cursor.getString(4));
                        quest.setOPTC(cursor.getString(5));
                        quesList.add(quest);
                    } while (cursor.moveToNext());
                }

                break;
            default:
                break;
        }
        // return quest list
        return quesList;
    }

    public int mostrarCalificacion(int idModulo,int numeroMod,int numeroTema){
        String nombreTema="";

        switch (numeroMod){
            case 1://Servlets
                switch (numeroTema){
                    case 1:
                        nombreTema="Mod_1_Tem_1";
                        break;
                    case 2:
                        nombreTema="Mod_1_Tem_2";
                        break;

                    case 3:
                        nombreTema="Mod_1_Tem_3";
                        break;

                    case 4:
                        nombreTema="Mod_1_Tem_4";
                        break;

                    case 5:
                        nombreTema="Mod_1_Tem_5";
                        break;

                    case 6:
                        nombreTema="Mod_1_Tem_6";
                        break;

                    case 7:
                        nombreTema="Mod_1_Tem_7";
                        break;

                    case 8:
                        nombreTema="Mod_1_Tem_8";
                        break;
                }
                break;
            case 2:
                idModulo++;
                Log.e("Modulo 2", "mostrarCalificacion: " );
                switch (numeroTema){
                    case 1:
                        nombreTema="Mod_2_Tem_1";
                        break;
                    case 2:
                        nombreTema="Mod_2_Tem_2";
                        break;
                    case 3:
                        nombreTema="Mod_2_Tem_3";
                        break;
                    case 4:
                        nombreTema="Mod_2_Tem_4";
                        break;
                    case 5:
                        nombreTema="Mod_2_Tem_5";
                        break;
                    case 6:
                        nombreTema="Mod_2_Tem_6";
                        break;
                    case 7:
                        nombreTema="Mod_2_Tem_7";
                        break;
                    case 8:
                        nombreTema="Mod_2_Tem_8";
                        break;
                }
                break;
        }

        String idMod=String.valueOf(idModulo);
        Cursor cursor =db.rawQuery(
                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE "+DBHelper.NAME+" LIKE ? AND "+DBHelper.MOD_ID+" = ?",
                new String[]{nombreTema, idMod});
        cursor.moveToFirst();
        int cali=cursor.getInt(cursor.getColumnIndex(DBHelper.CALIF));
        Log.e("Calificacion", "mostrarCalificacion: " + cali);
        return cali;
     }


    public void ingresarCalificacion(int score, int numeroTema, int idModul,int numeroModulo){
        String score2 = "";
        String nombreTema="";
        if (score==4){score2="10";}
        else if (score==3){score2="8";}

        switch (numeroModulo){
            case 1://servlet
                switch (numeroTema){
                    case 1:
                    nombreTema="Mod_1_Tem_1";
                     break;
                    case 2:
                        nombreTema="Mod_1_Tem_2";
                        break;
                    case 3:
                        nombreTema="Mod_1_Tem_3";
                        break;
                    case 4:
                        nombreTema="Mod_1_Tem_4";
                        break;
                    case 5:
                        nombreTema="Mod_1_Tem_5";
                        break;
                    case 6:
                        nombreTema="Mod_1_Tem_6";
                        break;
                    case 7:
                        nombreTema="Mod_1_Tem_7";
                        break;
                    case 8:
                        nombreTema="Mod_1_Tem_8";
                        break;
                }
                break;
            case 2://jsp
                idModul++;
                switch (numeroTema){
                    case 1:
                        nombreTema="Mod_2_Tem_1";
                        break;
                    case 2:
                        nombreTema="Mod_2_Tem_2";
                        break;
                    case 3:
                        nombreTema="Mod_2_Tem_3";
                        break;
                    case 4:
                        nombreTema="Mod_2_Tem_4";
                        break;
                    case 5:
                        nombreTema="Mod_2_Tem_5";
                        break;
                    case 6:
                        nombreTema="Mod_2_Tem_6";
                        break;
                    case 7:
                        nombreTema="Mod_2_Tem_7";
                        break;
                    case 8:
                        nombreTema="Mod_2_Tem_8";
                        break;
                }
                break;
        }

        String id = String.valueOf(idModul);

        ContentValues values= new ContentValues();
        values.put(DBHelper.CALIF,score2 );
        db.update(DBHelper.TABLE_NAME_2, values, DBHelper.MOD_ID + " = ? AND " + DBHelper.NAME + " LIKE ?",
                new String[]{id, nombreTema});

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean temaActivo(int numMod,int numeroCap,int idModulo){
        String nomTema;
        String idMod=String.valueOf(idModulo);//Solo para compara
        boolean activo=false;

        switch (numMod) {
            case 1:
                switch (numeroCap) {
                    case 1:
                        nomTema = "Mod_1_Tem_1";
                        Cursor cursor = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor.moveToFirst();
                        activo = cursor.getInt(cursor.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                    case 2:
                        nomTema = "Mod_1_Tem_2";
                        Cursor cursor1 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor1.moveToFirst();
                        activo = cursor1.getInt(cursor1.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                    case 3:
                        nomTema = "Mod_1_Tem_3";
                        Cursor cursor2 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor2.moveToFirst();
                        activo = cursor2.getInt(cursor2.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                    case 4:
                        nomTema = "Mod_1_Tem_4";
                        Cursor cursor3 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor3.moveToFirst();
                        activo = cursor3.getInt(cursor3.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                    case 5:
                        nomTema = "Mod_1_Tem_5";
                        Cursor cursor4 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor4.moveToFirst();
                        activo = cursor4.getInt(cursor4.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                    case 6:
                        nomTema = "Mod_1_Tem_6";
                        Cursor cursor5 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor5.moveToFirst();
                        activo = cursor5.getInt(cursor5.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                    case 7:
                        nomTema = "Mod_1_Tem_7";
                        Cursor cursor6 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor6.moveToFirst();
                        activo = cursor6.getInt(cursor6.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                    case 8:
                        nomTema = "Mod_1_Tem_8";
                        Cursor cursor7 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor7.moveToFirst();
                        activo = cursor7.getInt(cursor7.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                   /* case 9:
                        nomTema = "Mod_1_Tem_9";
                        Cursor cursor8 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor8.moveToFirst();
                        activo = cursor8.getInt(cursor8.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;*/
                }
                break;//Temina mis subtemas
            case 2://Inicia mi tema principal
                idModulo++;//Se incrementa el id del modulo
                idMod = String.valueOf(idModulo);//se buelve a hacignar al la variable de cadenas
                switch (numeroCap) {//inicias mis subtemas
                    case 1:
                        nomTema = "Mod_2_Tem_1";
                        Cursor cursor7 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor7.moveToFirst();
                        activo = cursor7.getInt(cursor7.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                    case 2:
                        nomTema = "Mod_2_Tem_2";
                        Cursor cursor8 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor8.moveToFirst();
                        activo = cursor8.getInt(cursor8.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                    case 3:
                        nomTema = "Mod_2_Tem_3";
                        Cursor cursor9 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor9.moveToFirst();
                        activo = cursor9.getInt(cursor9.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                    case 4:
                        nomTema = "Mod_2_Tem_4";
                        Cursor cursor10 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor10.moveToFirst();
                        activo = cursor10.getInt(cursor10.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                    case 5:
                        nomTema = "Mod_2_Tem_5";
                        Cursor cursor11 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor11.moveToFirst();
                        activo = cursor11.getInt(cursor11.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                    case 6:
                        nomTema = "Mod_2_Tem_6";
                        Cursor cursor12 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor12.moveToFirst();
                        activo = cursor12.getInt(cursor12.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                    case 7:
                        nomTema = "Mod_2_Tem_7";
                        Cursor cursor13 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor13.moveToFirst();
                        activo = cursor13.getInt(cursor13.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                    case 8:
                        nomTema = "Mod_2_Tem_8";
                        Cursor cursor14 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor14.moveToFirst();
                        activo = cursor14.getInt(cursor14.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;
                   /* case 9:
                        nomTema = "Mod_2_Tem_9";
                        Cursor cursor15 = db.rawQuery(
                                "SELECT * FROM " + DBHelper.TABLE_NAME_2 + " WHERE nombre LIKE ? AND  " + DBHelper.MOD_ID + " = ?", new String[]{nomTema, idMod});
                        cursor15.moveToFirst();
                        activo = cursor15.getInt(cursor15.getColumnIndex(DBHelper.ACTIVO)) == 1 ? true : false;
                        return activo;*/
                }
                break;
        }

        return activo;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public void activarTema(int idModulo, int numMod, int numeroCap){
        String nomTema="";
        switch (numMod) {
            case 1:
                switch (numeroCap) {
                    case 1:
                        nomTema = "Mod_1_Tem_2";
                        break;
                    case 2:
                        nomTema = "Mod_1_Tem_3";
                        break;
                    case 3:
                        nomTema = "Mod_1_Tem_4";
                        break;
                    case 4:
                        nomTema = "Mod_1_Tem_5";
                        break;
                    case 5:
                        nomTema = "Mod_1_Tem_6";
                        break;
                    case 6:
                        nomTema = "Mod_1_Tem_7";
                        break;
                    case 7:
                        nomTema = "Mod_1_Tem_8";
                        break;
                    case 8:
                        break;
                }
                break;
            case 2:
                idModulo++;
                Log.e("Entro mod 2", "activarTema: ");
                switch (numeroCap) {//inicias mis subtemas
                    case 1:
                        nomTema = "Mod_2_Tem_2";
                        Log.e("Ento 1", "activarTema: ");
                        break;
                    case 2:
                        nomTema = "Mod_2_Tem_3";
                        Log.e("Ento 2", "activarTema: ");
                        break;
                    case 3:
                        nomTema = "Mod_2_Tem_4";
                        break;
                    case 4:
                        nomTema = "Mod_2_Tem_5";
                        break;
                    case 5:
                        nomTema = "Mod_2_Tem_6";
                        break;
                    case 6:
                        nomTema = "Mod_2_Tem_7";
                        break;
                    case 7 :
                        nomTema = "Mod_2_Tem_8";
                        break;
                    case 8:
                        break;
                }
                break;
        }
            String idMod = String.valueOf(idModulo);

            ContentValues values = new ContentValues();

            values.put(DBHelper.ACTIVO, 1);
            db.update(DBHelper.TABLE_NAME_2, values, DBHelper.MOD_ID + " = ? AND " + DBHelper.NAME + " LIKE ?",
                    new String[]{idMod, nomTema});
    }
    /**
     * Progreso
     * @param idUsuario
     * @return
     */
    public int getProgresoTotal (String idUsuario){
        int row=0;
        int idMod=idPrimerModuloIns(idUsuario,"Modulo_1");
        int idUsuar=Integer.valueOf(idUsuario);

        int row2=getProgreso(idUsuar,idMod, 1);
        int row3=getProgreso(idUsuar, idMod, 2);

        int total=row2+row3;
        if (total<=14) {
            total = total - 2;
        }
        if (total==16){total=100;}else
        if (total==15){total=94;}else
        if (total==14){total=88;}else
        if (total==13){total=82;}else
        if (total==12){total=76;}else
        if (total==11){total=70;}else
        if (total==10){total=64;}else
        if (total==9){total=58;}else
        if (total==8){total=52;}else
        if (total==7){total=46;}else
        if (total==6){total=40;}else
        if (total==5){total=34;}else
        if (total==4){total=28;}else
        if (total==3){total=22;}else
        if (total==2){total=16;}else
        if (total==1){total=10;}else
        if (total==0){total=0;}

        return total;//+row2+row3+row4+row5;
    }

    public int getProgreso (int idUsuario,int idModulo,int numeroMod){
        int row40=0;
        Cursor cursor;
        String idMod="";
        switch (numeroMod){
            case 1:
                idMod=String.valueOf(idModulo);
                cursor = db.rawQuery("SELECT  * FROM " + DBHelper.TABLE_NAME_2 + " where " + DBHelper.ACTIVO + " = ? and " + DBHelper.MOD_ID + " = ?",
                        new String[]{"1", idMod});
                row40=cursor.getCount();

                //if (traerCalificacion(idModulo, FormLoginActivity.ID_U)>=8){
                    return row40;
                //}else {
                  //  return row40-1;
                //}
            case 2:
                idModulo++;
                idMod=String.valueOf(idModulo);
                cursor = db.rawQuery("SELECT  * FROM " + DBHelper.TABLE_NAME_2 + " where " + DBHelper.ACTIVO + " = ? and " + DBHelper.MOD_ID + " = ?",
                        new String[]{"1", idMod});
                row40=cursor.getCount();
                //if (traerCalificacion(idModulo,FormLoginActivity.ID_USU_LOGEADO)>=8){
                    return row40;
                //}else {
                  //  return row40-1;
                //}
            default:
                break;
        }
        return row40;
    }

    public String[] informacionUsuario(String idUsu){

        Cursor cursor=db.rawQuery(
                "SELECT * FROM " + DBHelper.TABLE_NAME_1 + " WHERE _id=? ", new String[]{idUsu});

        String[] infoUsu=new String[2];
        cursor.moveToFirst();
        infoUsu[0]=cursor.getString(cursor.getColumnIndex(DBHelper.NAME));
        infoUsu[1]=cursor.getString(cursor.getColumnIndex(DBHelper.MAIL));
        return infoUsu;
    }

    public void actualizarTema(int date){
       // Actualizamos el registro en la base de datos
        ContentValues values= new ContentValues();
        values.put(DBHelper.TEME,date );
        db.update(DBHelper.TABLE_NAME_TEME, values, DBHelper.ID_TEME + " = ? ",
                new String[]{"1"});
    }

    public  int  tema (){

        String selectQuery =  "SELECT "+ DBHelper.TEME+" FROM "+ DBHelper.TABLE_NAME_TEME+" WHERE "+DBHelper.ID_TEME+" = 1" ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        int score=0;
        if (cursor != null) {
            cursor.moveToFirst();
            score=cursor.getInt(0);
        }
        return score;
    }
}