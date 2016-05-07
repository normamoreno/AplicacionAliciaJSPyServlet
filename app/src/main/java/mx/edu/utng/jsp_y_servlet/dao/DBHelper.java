package mx.edu.utng.jsp_y_servlet.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mx.edu.utng.jsp_y_servlet.Question;
import mx.edu.utng.jsp_y_servlet.util.DBAdapter;

/**
 * Created by NORMA on 23/02/2016.
 */
public class DBHelper extends SQLiteOpenHelper {


    public  static final String DATABASE_NAME = "utng.db";
    public  static final int DATABASE_VERSION = 1;
    //nombre de las tablas
    public static final String TABLE_NAME_1 = "tbl_usuario";
    public static final String TABLE_NAME_2 = "tbl_tema";
    public static final String TABLE_NAME_3 = "tbl_modulo";
    //campos de las tablas
    public static final String NAME = "nombre";
    public static final String PASSWORD = "contrasena";
    public static final String MAIL = "correo";
    public static final String USER_ID = "id_usuario";
    public static final String MOD_ID = "id_modulo";
    public static final String ID = "_id";
    public static final String CALIF = "calificacion";
    public static final String ACTIVO = "activo";

    public static final String COLUMN_TEME_ID = "id";
    public static final String COLUMN_TEME = "teme";

    ///////Estaticos de Quiz
    public static final String KEY_QUES = "question";
    public static final String KEY_ANSWER = "answer"; //correct option
    public static final String KEY_OPTA= "opta"; //option a
    public static final String KEY_OPTB= "optb"; //option b
    public static final String KEY_OPTC= "optc"; //option c
    public static final String TABLE_NAME_QUEST = "quest";

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String TABLE_NAME_TEME = "Tema";
    public static final String ID_TEME = "id";
    public static final String TEME = "tema";
    public static final String SELECT = "SELECT tema FROM Tema WHERE id = 1";


    private SQLiteDatabase dbase;
    private static final String CREATE_TABLE_TEME = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_TEME + " ( "
            + ID_TEME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TEME+ " INTEGER DEFAULT 1)";

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private SQLiteDatabase dbsql;

    /////
    private static final String TABLE_CREATE_QUIZ = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_QUEST + " ( "
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
            + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
            +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";


    //sintaxis para crear la tabla de usuario
    private static final String CREATE_TABLE_1 =
            "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_1
                    +"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +NAME+" TEXT, "
                    +MAIL+" TEXT, "
                    +PASSWORD+" TEXT);";

    //sintaxis para crear la tabla tema
    private static final String CREATE_TABLE_2 =
            "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_2
                    +"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +NAME+" TEXT, "
                    +CALIF+" INTEGER, "
                    +ACTIVO+" INTEGER, "
                    +MOD_ID+" INTEGER, FOREIGN KEY("+MOD_ID+") REFERENCES "+TABLE_NAME_3+" ("+ID+"));";

    //sintaxis para crear la tabla modulo
    private static final String CREATE_TABLE_3 =
            "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_3
                    +"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +NAME+" TEXT, "
                    +USER_ID+" INTEGER, FOREIGN KEY("+USER_ID+") REFERENCES "+TABLE_NAME_1+" ("+ID+"));";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbsql = db;
        db.execSQL(CREATE_TABLE_1);
        db.execSQL(CREATE_TABLE_2);
        db.execSQL(CREATE_TABLE_3);
        db.execSQL(CREATE_TABLE_TEME);
        db.execSQL(TABLE_CREATE_QUIZ);
        db.execSQL("INSERT INTO Tema VALUES (1,1)");
        addQuestions();
        addTemaFondo();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_3);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_TEME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_QUEST);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CREATE_QUIZ);
        onCreate(db);
    }
    public void addTemaFondo(){
        ContentValues values = new ContentValues();
        values.put(DBHelper.TEME, 1);
        dbsql.insert(DBHelper.TABLE_NAME_TEME, null, values);
    }

    public void addQuestions()
    {

        //================Bacico de c++============================================
        Question q1;
        //Se crean las preguntas con sus posibles resultado y su resultado correcto
        q1=new Question("¿Qué es Servlet?", "Paginas de ", "es mas internacional", "ambos", "ambos");
        this.addQuestion(q1);
        q1=new Question("¿Cuantos bytes ocupa el tipo de dato \"int\"", "2bytes", "8bytes", "4bytes", "4bytes");
        this.addQuestion(q1);
        q1=new Question("Cual es la principal caracteristica de una constante", "Tiene un valor fijo", "expresa valores diferentes dentro del codigo", "Ninguna", "Tiene un valor fijo");
        this.addQuestion(q1);
        q1=new Question("para que sirven los operadores en C++", "es mas corto", "es mas internacional", "ambos", "ambos");
        this.addQuestion(q1);
        q1=new Question("Para que nos sirven la entrada y salida de datos", "para realizar operaciones", "extraer caracteres", "Ninguna", "para realizar operaciones");
        this.addQuestion(q1);


        //================Controles de estructura============================================

        q1=new Question("Hay algunos pasos que deben completarse antes de que podamos conseguir a la materia esencial cuantos pasos son. ","5","10","6","6");
        this.addQuestion(q1);
        q1=new Question("Este es un paso opcional que le ayudará con las pruebas de su juegos en muchas versiones de la API y tamaños de pantalla.","Descargar sdk","openGL","Crear un emulador.","Crear un emulador.");
        this.addQuestion(q1);
        q1=new Question("Este es un paso opcional que le ayudará con las pruebas de su juegos en muchas versiones de la API y tamaños de pantalla.","Descargar sdk","openGL","Crear un emulador.","Crear un emulador.");
        this.addQuestion(q1);
        q1=new Question("Este es un paso opcional que le ayudará con las pruebas de su juegos en muchas versiones de la API y tamaños de pantalla.","Descargar sdk","openGL","Crear un emulador.","Crear un emulador.");
        this.addQuestion(q1);
        q1=new Question("Este es un paso opcional que le ayudará con las pruebas de su juegos en muchas versiones de la API y tamaños de pantalla.","Descargar sdk","openGL","Crear un emulador.","Crear un emulador.");
        this.addQuestion(q1);

        //================Componentes de tipos de datos============================================

        q1=new Question("Antes de empezar que se tiene que hacer primero de acuerdo a lo leído.","Abrir el proyecto para empezar a trabajar.","Descargar aplicaciones.","Instalar el SDK","Instalar el SDK");
        this.addQuestion(q1);
        q1=new Question("¿Que es sdk?","Herramienta de cómputo","Programa informático.","Herramienta que sirve para la creación de nuestro proyecto.","Herramienta que sirve para la creación de nuestro proyecto.");
        this.addQuestion(q1);
        q1=new Question("¿Que es sdk?","Herramienta de cómputo","Programa informático.","Herramienta que sirve para la creación de nuestro proyecto.","Herramienta que sirve para la creación de nuestro proyecto.");
        this.addQuestion(q1);
        q1=new Question("¿Que es sdk?","Herramienta de cómputo","Programa informático.","Herramienta que sirve para la creación de nuestro proyecto.","Herramienta que sirve para la creación de nuestro proyecto.");
        this.addQuestion(q1);
        q1=new Question("¿Que es sdk?","Herramienta de cómputo","Programa informático.","Herramienta que sirve para la creación de nuestro proyecto.","Herramienta que sirve para la creación de nuestro proyecto.");
        this.addQuestion(q1);


        //================Programacion orientada a objetos============================================

        q1=new Question("Por qué es importante configurar tu eclipse ","Es esencial para la creación del juego.","Para trabajar más rápido.","Para así poder usarlo para la creación de juegos.","Para así poder usarlo para la creación de juegos.");
        this.addQuestion(q1);
        q1=new Question("En cualquier ide se puede crear un juego.","Si solo basta con tener un SDK","Si en cualquiera que se desee.","Se tiene que tener un configurado el IDE para poder crear un juego.","Se tiene que tener un configurado el IDE para poder crear un juego.");
        this.addQuestion(q1);
        q1=new Question("En cualquier ide se puede crear un juego.","Si solo basta con tener un SDK","Si en cualquiera que se desee.","Se tiene que tener un configurado el IDE para poder crear un juego.","Se tiene que tener un configurado el IDE para poder crear un juego.");
        this.addQuestion(q1);
        q1=new Question("En cualquier ide se puede crear un juego.","Si solo basta con tener un SDK","Si en cualquiera que se desee.","Se tiene que tener un configurado el IDE para poder crear un juego.","Se tiene que tener un configurado el IDE para poder crear un juego.");
        this.addQuestion(q1);
        q1=new Question("En cualquier ide se puede crear un juego.","Si solo basta con tener un SDK","Si en cualquiera que se desee.","Se tiene que tener un configurado el IDE para poder crear un juego.","Se tiene que tener un configurado el IDE para poder crear un juego.");
        this.addQuestion(q1);


        //================Conceptos avanzados============================================

        q1=new Question("Para qué sirve el emulador de Android en el desarrollo de un juego.","Hace más rápido el proceso de la creación del juego.","Es una librería importante en la aplicación.","Para correr nuestra aplicación.","Para correr nuestra aplicación.");
        this.addQuestion(q1);
        q1=new Question("Que parte es importante saber acerca del emulador Android.","Nada todo queda entendido.","Que se debe de instalar siempre después de configurar eclipse.","Que se puede correr la aplicación en un dispositivo real o en emulador.","Que se puede correr la aplicación en un dispositivo real o en emulador.");
        this.addQuestion(q1);
        q1=new Question("Que parte es importante saber acerca del emulador Android.","Nada todo queda entendido.","Que se debe de instalar siempre después de configurar eclipse.","Que se puede correr la aplicación en un dispositivo real o en emulador.","Que se puede correr la aplicación en un dispositivo real o en emulador.");
        this.addQuestion(q1);
        q1=new Question("Que parte es importante saber acerca del emulador Android.","Nada todo queda entendido.","Que se debe de instalar siempre después de configurar eclipse.","Que se puede correr la aplicación en un dispositivo real o en emulador.","Que se puede correr la aplicación en un dispositivo real o en emulador.");
        this.addQuestion(q1);
        q1=new Question("Que parte es importante saber acerca del emulador Android.","Nada todo queda entendido.","Que se debe de instalar siempre después de configurar eclipse.","Que se puede correr la aplicación en un dispositivo real o en emulador.","Que se puede correr la aplicación en un dispositivo real o en emulador.");
        this.addQuestion(q1);

        //================Estandar de librerias============================================

        q1=new Question("¿Que es avd?","Un componente de eclipse.","Una librería.","Conjunto de atributos de configuración aplicado a una imagen emulador.","Conjunto de atributos de configuración aplicado a una imagen emulador.");
        this.addQuestion(q1);
        q1=new Question("Que es lo que ofrece un AVD","Un entorno más cómodo para trabajar.","Se encarga de correr el programa.","Crea dispositivos virtuales","Crea dispositivos virtuales");
        this.addQuestion(q1);
        q1=new Question("Que es lo que ofrece un AVD","Un entorno más cómodo para trabajar.","Se encarga de correr el programa.","Crea dispositivos virtuales","Crea dispositivos virtuales");
        this.addQuestion(q1);
        q1=new Question("Que es lo que ofrece un AVD","Un entorno más cómodo para trabajar.","Se encarga de correr el programa.","Crea dispositivos virtuales","Crea dispositivos virtuales");
        this.addQuestion(q1);
        q1=new Question("Que es lo que ofrece un AVD","Un entorno más cómodo para trabajar.","Se encarga de correr el programa.","Crea dispositivos virtuales","Crea dispositivos virtuales");
        this.addQuestion(q1);
    }
    public void addQuestion(Question quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_QUES, quest.getQUESTION());
        values.put(DBHelper.KEY_ANSWER, quest.getANSWER());
        values.put(DBHelper.KEY_OPTA, quest.getOPTA());
        values.put(DBHelper.KEY_OPTB, quest.getOPTB());
        values.put(DBHelper.KEY_OPTC, quest.getOPTC());
        // Inserting Row
        dbsql.insert(DBHelper.TABLE_NAME_QUEST, null, values);
    }
    public void actualizarTema(int date){
        dbsql=this.getWritableDatabase();
        // Actualizamos el registro en la base de datos
        ContentValues values= new ContentValues();
        dbsql.update(DBHelper.TABLE_NAME_TEME, values, DBHelper.ID_TEME + " = ? ",
                new String[]{"1"});
    }

    public  int  tema (){
        dbsql=this.getReadableDatabase();
        String selectQuery =  "SELECT "+TEME+" FROM "+ TABLE_NAME_TEME+" WHERE "+ID_TEME+" = 1" ;
        Cursor cursor = dbsql.rawQuery(selectQuery, null);
        int score=0;
        if (cursor != null) {
            cursor.moveToFirst();
            score=cursor.getInt(0);
        }
        return score;
    }
}