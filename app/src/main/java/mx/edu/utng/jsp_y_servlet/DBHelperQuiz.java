package mx.edu.utng.jsp_y_servlet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NORMA on 26/03/2016.
 */
public class DBHelperQuiz extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "triviaQuiz";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; //correct option
    private static final String KEY_OPTA= "opta"; //option a
    private static final String KEY_OPTB= "optb"; //option b
    private static final String KEY_OPTC= "optc"; //option c

    private static final String TABLE_NAME_TEME = "Tema";
    private static final String ID_TEME = "id";
    private static final String TEME = "tema";
    private static final String SELECT = "SELECT tema FROM Tema WHERE id = 1";

    private SQLiteDatabase dbase;
    private static final String CREATE_TABLE_TEME = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_TEME + " ( "
            + ID_TEME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TEME+ " INTEGER)";
    public DBHelperQuiz(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        db.execSQL(CREATE_TABLE_TEME);
        db.execSQL("INSERT INTO "+TABLE_NAME_TEME+" VALUES (1,1)");

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }
    private void addQuestions()
    {

        //================¿Qué es Servlet?============================================
        Question q1;
        //PAK Cero  -->¿Qué es Servlet? --1(Intro)
        //Se crean las preguntas con sus posibles resultado y su resultado correcto
        q1=new Question("¿Qué es Servlet","Paginas de Servidor de Java", "Editor HTML/XML", "Programa que se ejecuta en el contenedor Web de un servidor de aplicaciones", "Programa que se ejecuta en el contenedor Web de un servidor de aplicaciones");
        this.addQuestion(q1);
        q1=new Question("Definen algunas caracteristicas de Servlet:", "Puede invocarse utilizando el protocolo HTTP", "Tiene Scriptlets", "Ninguna", "Puede invocarse utilizando el protocolo HTTP");
        this.addQuestion(q1);
        q1=new Question("En que se carga y se ejecuta un servlet","Contenedor Web", "Editor HTML/XML", "Programa", "Contenedor Web");
        this.addQuestion(q1);
        q1=new Question("¿Con qué se le compara a un servlet?", "Applet", "Scriptlet", "Ninguna", "Applet");
        this.addQuestion(q1);
        //================¿Qué es Servlet?============================================

        //////////================Caracteristicas Servlet=============================
        //PAK uno -->Caracteristicas Servlet
        q1=new Question("En qué estan escritos los servlets: ","Java","HTML","PHP","Java");
        this.addQuestion(q1);
        q1=new Question("¿Por qué los servlets son seguros y portables?","Estan escritos en HTML","Se ejecutan bajo la máquina virtual de Java","Es un objeto de la clase API","Se ejecutan bajo la máquina virtual de Java");
        this.addQuestion(q1);
        q1=new Question("Los servlets son más rápidos que: ","ninguno","JSP","CGI, scripts","CGI, scripts");
        this.addQuestion(q1);
        q1=new Question("En qué dominio operan los servlets: ","Explorador","Dominio del servidor","En un objeto de la clase API","Dominio del servidor");
        this.addQuestion(q1);

        //PAK dos  -->Estructura Básica
        q1=new Question("Completa la siguiente linea de código: public void _____(HttpServletRequest request, HttpServletResponse response)","doPost","doSet","doGet","doGet");
        this.addQuestion(q1);
        q1=new Question("Completa las siguientes lineas de código: PrintWriter ___ = response._________();","void, data","out, getWriter","out, type","out, getWriter");
        this.addQuestion(q1);
        q1=new Question("¿Cuál es la clase idónea para servicios específicos HTTP?","HttpRequest","HttpServlet","doGet","HttpServlet");
        this.addQuestion(q1);
        q1=new Question("Completa el siguiente codigo de la estructura de servlet: throws ______Esception","Servlet","out","Jsp","Servlet");
        this.addQuestion(q1);

        //PAK tres -->Ciclo de un Servlet
        q1=new Question("Para que es invocado el método init ","Iniciar la ejecución del servlet","Recibir una petición del servlet","Destruir el servlet","Iniciar la ejecución del servlet");
        this.addQuestion(q1);
        q1=new Question("¿Cuáles son los dos parametros que utiliza el método service?","doGet, doPost","HttpServletRequest, HttpServletResponse","put, delete","HttpServletRequest, HttpServletResponse");
        this.addQuestion(q1);
        q1=new Question("¿Cuántos métodos tiene el ciclo de vida de un Servlet?","3","4","2","3");
        this.addQuestion(q1);
        q1=new Question("¿Cuál es el último método invocado? ","init","destroy","service","destroy");
        this.addQuestion(q1);



        //PAK cuatro-->Software para ejecutar un servlet
        q1=new Question("¿Cuáles son los pasos para ver el resultado que produce un servlet?","Compilarlo, instalarlo en un servidor e invocarlo.","Instalarlo y ejecutarlo.","Instalarlo y compilarlo.","Compilarlo, instalarlo en un servidor e invocarlo.");
        this.addQuestion(q1);
        q1=new Question("Si no se tiene instalado ningún software, ¿qué paquete se debe instalar?","JDK","J2EE SDK","Apache Tomcat","J2EE SDK");
        this.addQuestion(q1);
        q1=new Question("¿Cuántos pasos se pueden llevar a cabo para ejecutar un servlet?","3","1","2","2");
        this.addQuestion(q1);
        q1=new Question("Si ya tenemos instalado el J2SE ¿Qué se tiene que instalar posteriormente?","JDK","J2EE","Tomcat 5","Tomcat 5");
        this.addQuestion(q1);



        //PAK cinco-->Tipos de peticiones
        q1=new Question("¿Cuantos tipos de peticiones hay en Servlet?","1","2","ninguna","2");
        this.addQuestion(q1);
        q1=new Question("¿Cuáles son los tipos de peticiones?","Request, Response","HTTP GET, HTTP POST","doGest, doPost","HTTP GET, HTTP POST");
        this.addQuestion(q1);
        q1=new Question("Completa el enunciado: Si los datos son largos, privados o importantes, utilizaremos____","GET","POST","Request","POST");
        this.addQuestion(q1);
        q1=new Question("Completa el enunciado: Si los datos son pocos y no importa la confidencialidad o manipulación, utilizaremos____","GET","Request","POST","GET");
        this.addQuestion(q1);




        //PAK seis--> Datos enviados por el cliente
        q1=new Question("Mediante qué método un servlet lee los datos enviados en la petición:","getValues", "getPost", "getParameter", "getParameter");
        this.addQuestion(q1);
        q1=new Question("Completa el siguiente método que permite iterar: ___more________", "has, Elements", "get, Names", "get, Parameter", "has, Elements");
        this.addQuestion(q1);
        q1=new Question("¿Qué método permite obtener los nombres de los parámetros?","getValues", "getPost", "getParameterNames", "getParameterNames");
        this.addQuestion(q1);
        q1=new Question("¿Cuál es el método que devuelve un objeto de tipo String el valor del parámetro especificado?", "hasMoreElements", "getParameter", "doPost", "getParameter");
        this.addQuestion(q1);
        //////////=============================================


        //==========================================================0

        //PAK cuatro--> Método del objeto cookie
        q1=new Question("¿Qué es una cookie?","Pasa la información", "Pequeña cantidad de información que un servlet puede crear y almacenar en la máquina del cliente.", "Pasa los argumentos que se requieren añadir", "Pequeña cantidad de información que un servlet puede crear y almacenar en la máquina del cliente.");
        this.addQuestion(q1);
        q1=new Question("Completa con el método correcto: response._________(miCookie);", "addCookie", "miCookie", "new.Cookie", "addCookie");
        this.addQuestion(q1);
        q1=new Question("¿Cuáles son las dos partes de las que se compone una cookie?","tamaño, tipo", "nombre, valor", "constructor, argumento", "nombre, valor");
        this.addQuestion(q1);
        q1=new Question("Completa la sintaxis para crear una cookie: Cookie _______ = new Cookie(nombre-cookie, valor-asociado);", "addCookie", "miCookie", "new.Cookie", "miCookie");
        this.addQuestion(q1);

        //PAK cuatro-->Interfaz HttpSession
        /*q1=new Question("Método que finaliza la sesión:","public void invalidate()", "public long getCreationTime()", "public boolean isNew()", "public void invalidate()");
        this.addQuestion(q1);
        q1=new Question("¿Qué método se invoca para obtener la sesión correspondiente a un usuario?", "getResponse", "getRequest", "getSession", "getSession");
        this.addQuestion(q1);*/

/*=============================================MODULOS DE PREGUNTAS DE JSP===================================================================*/


        //PAK cuatro-->¿Qué es JSP?
        q1=new Question("¿Que quiere decir JSP?","Java Server Pages", "Java Server Public", "Java Server Private", "Java Server Pages");
        this.addQuestion(q1);
        q1=new Question("¿A que tecnologia estan orientados los JSP? ", "Crear páginas Web con programación en Java", "Java Server Private", "Crear código", "Crear páginas Web con programación en Java");
        this.addQuestion(q1);
        q1=new Question("¿Con qué editor se pueden escribir los JSP?","HTML/XML", "Java", "Servlet", "HTML/XML");
        this.addQuestion(q1);
        q1=new Question("¿De qué código están compuestos los JSP?", "PHP", "HTML/XML, etiquetas especiales, Java", "Java", "HTML/XML, etiquetas especiales, Java");
        this.addQuestion(q1);


        //PAK cuatro--> Ciclo de vida de un JSP
        q1=new Question("Cuántas fases tiene un JSP","3", "4", "no tiene fases", "3");
        this.addQuestion(q1);
        q1=new Question("Cuáles son las fases del ciclo de vida de un JSP:","Instalación, Compilación, Depuración", "Traducción, Compilación, Ejecución", "Prueba, Ejecución, Compilación", "Traducción, Compilación, Ejecución");
        this.addQuestion(q1);
        q1=new Question("¿Cuáles son las fases qué ocurren juntas?","ejecución y compilación", "traducción y ejecución", "traducción y compilación", "traducción y compilación");
        this.addQuestion(q1);
        q1=new Question("¿En la fase de ejecución que se crea?","Instancia", "Traducción", "Prueba", "Instancia");
        this.addQuestion(q1);

        //PAK cuatro-->Objetos implicitos
        q1=new Question("Completa el código; out es un objeto de la clase_________, se obtiene a tráves del método______","getSession, getWriter", "JspWriter, getOut", "Object, JspFactory", "JspWriter, getOut");
        this.addQuestion(q1);
        q1=new Question("¿Cuantos objetos son utilizados en la técnologia JSP?","8", "9", "ninguno", "9");
        this.addQuestion(q1);
        q1=new Question("Completa el código; application es un objeto de la clase_________, se obtiene a tráves del método______","getSession, getWriter", "ServletContext, getOut", "ServletContext, getServletContext", "ServletContext, getServletContext");
        this.addQuestion(q1);
        q1=new Question("Completa el código; page es un objeto de la clase_________, se obtiene a tráves del método______","JspWriter, getOut", "Object, getPage", "ninguno", "Object, getPage");
        this.addQuestion(q1);


        //PAK cuatro--> Ámbitos de los atributos
        q1=new Question("¿Cuales son los métodos para fijar y leer atributos?","ServletContext, setAttribute", "HttpSession, getAttribute", "setAttribute, getAttribute", "setAttribute, getAttribute");
        this.addQuestion(q1);
        q1=new Question("¿Cuantos ámbitos son mencionados anteriormente?","8", "4", "6", "4");
        this.addQuestion(q1);
        q1=new Question("¿Qué determina el ámbito de un atributo?","duración y visibilidad", "peticiones y  duración", "contexto y visibilidad", "duración y visibilidad");
        this.addQuestion(q1);
        q1=new Question("¿Qué interfaz utilizan los atributos qué tiene ámbito de página?","PageRequest", "PageResponse", "PageContext", "PageContext");
        this.addQuestion(q1);


        //PAK cuatro--> Lenguaje de expresión en JSP
        q1=new Question("¿Qué es el lenguaje de expresión JSP?","Lenguaje de expresión", "Lenguaje de programación", "Lenguaje de acceso a datos", "Lenguaje de acceso a datos");
        this.addQuestion(q1);
        q1=new Question("¿Cuantos objetos implicitos soporta el lenguaje de expresion de JSP 2.0?","7", "8", "6", "7");
        this.addQuestion(q1);
        q1=new Question("¿De qué tipo es el objeto cookie","java.util.Map", "java.util.Context", "java.Servlet.Jsp", "java.util.Map");
        this.addQuestion(q1);
        q1=new Question("¿Cuál es el único objeto que pertenece a javax.servlet.jsp.PageContext?","paramValues", "pageContext", "headerValues", "pageContext");
        this.addQuestion(q1);


        //PAK cuatro--> Comentarios
        q1=new Question("¿Para qué son utiles los comentarios?","Insertar respuestas", "Identificar el documento y comentar el código", "Darle un buen formato al código", "identificar el documento y comentar el código");
        this.addQuestion(q1);
        q1=new Question("¿Cuales son los simbolos utilizados en un comentario JSP?","<!-- y -->", "<%- y ->", "<%-- y --%>", "<%-- y --%>");
        this.addQuestion(q1);
        q1=new Question("¿Cuales son los simbolos utilizados en un comentario HTML?","<!-- y -->", "<!- y ->", "<-- y -->", "<!-- y -->");
        this.addQuestion(q1);
        q1=new Question("¿Cuáles son los comentarios qué no se visualizan en el código HTML?","HTML", "JSP", "ninguno", "JSP");
        this.addQuestion(q1);



        //PAK cuatro--> Directrices
        q1=new Question("¿Cuales son las directrices que sirven como mensajes para el motor JSP?","page, xml, jsp", "page, include, taglib", "jsp, include, servlet", "page, include, taglib");
        this.addQuestion(q1);
        q1=new Question("¿Cual es la directriz que se localiza al principio de la página JSP?","page", "include", "taglib", "page");
        this.addQuestion(q1);
        q1=new Question("¿Qué fichero incluye la directriz include?","(.html, .jsp)", "(.jsp, .xml)", "(.html, .jsp, .xml)", "(.html, .jsp, .xml)");
        this.addQuestion(q1);
        q1=new Question("¿Cual es la directriz que debe colocarse antes de las acciones personalizadas?","page", "include", "taglib", "taglib");
        this.addQuestion(q1);



        //PAK cuatro--> Integración Servlet, JSP
        q1=new Question("¿Cual es el proceso para que un Servlet pueda pasar los resultados a un conjunto de páginas JSP?","manejar la petición, procesar los datos, iniciar los javabeans", "compilar, depurar y ejecutar", "depurar, mandar peticiones", "manejar la petición, procesar los datos, iniciar los javabeans");
        this.addQuestion(q1);
        q1=new Question("¿Cual es el principal servidor gratuito de servlet y jsp?","JDK", "IBM", "Tomcat", "Tomcat");
        this.addQuestion(q1);
        q1=new Question("Completa la siguiente linea de código que sirve para pasar peticiones: __________ dispatcher = request.getRequestDispatcher(direccion);","dispatcher.forward", "RequestDispatcher", "URL", "RequestDispatcher");
        this.addQuestion(q1);
        q1=new Question("¿Para qué se suele utilizar JSP?","Desarrollar y mantener la presentación", "Procesar los datos", "Iniciar los javaBeans", "Desarrollar y mantener la presentación");
        this.addQuestion(q1);


        //===========================00Trucos para movil===============================0

        //===========================

        //oPN GL Y JN===================================================================

        //OpenGL y JNI -->7(La energía de los dispositivos móviles)





        //===========================00Trucos para movil===============================0




    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(Question quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }
    public List<Question> getAllQuestions( int dd) {

        List<Question> quesList = new ArrayList<Question>();
        String selectQuery="";
        dbase=this.getReadableDatabase();//se abre la base a lectura
        Cursor cursor;
        switch (dd){
            case 0:
                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" <5 ";// se crea la query 1,2
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >4 OR "+KEY_ID+" <9 ";// se crea la query 3, 4
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >8 OR "+KEY_ID+" <13 ";// se crea la query 5,6
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >12 OR "+KEY_ID+" <17 ";// se crea la query 7,8
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >16 OR "+KEY_ID+" <21 ";// se crea la query 9,10
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >20 OR "+KEY_ID+" <25 ";// se crea la query 11,12
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 6:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >24 OR "+KEY_ID+" <29 ";// se crea la query 13,14
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 7:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >28 OR "+KEY_ID+" <33 "; //15, 16 1
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 8:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >32 OR "+KEY_ID+" <37 "; //17, 18 2
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 9:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >36 OR "+KEY_ID+" <41 "; //19, 20 empiezan 3
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 10:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >40 OR "+KEY_ID+" <45 "; //21, 22 empiezan 4
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 11:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >44 OR "+KEY_ID+" <49 "; //23, 24 empiezan 5
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 12:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >48 OR "+KEY_ID+" <53 "; //25, 26 empiezan 6
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 13:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >52 OR "+KEY_ID+" <57 "; //27, 28 empiezan 7
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 14:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >56 OR "+KEY_ID+" <61 "; //29, 30 empiezan 8
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 15:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >60 OR "+KEY_ID+" <65 "; //31, 32 empiezan 9
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 16:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >64 OR "+KEY_ID+" <69 "; //33, 34 tema 4INICIA 1
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 17:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >34 OR "+KEY_ID+" <37 "; //35, 36 tema 4INICIA 2
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 18:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >36 OR "+KEY_ID+" <39 "; //37, 38 tema 4INICIA 3
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 19:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >38 OR "+KEY_ID+" <41 "; //39, 40 tema 4INICIA 4
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 20:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >40 OR "+KEY_ID+" <43 "; //41, 42 tema 4INICIA 5
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 21:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >42 OR "+KEY_ID+" <45 "; //43, 44 tema 4INICIA 6
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
           case 22:

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >44 OR "+KEY_ID+" <47 "; //45, 46 tema 4INICIA 7 ter4
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 23: //Empieza el ultimo tema son 9 subtemas este es el uno de 9 ...

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >46 OR "+KEY_ID+" <49 "; //47, 48 empieza el mod5
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 24: //Empieza el ultimo tema son 9 subtemas este es el uno de 9 ...

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >48 OR "+KEY_ID+" <51 "; //49, 50 empieza el mod5
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 25: //Empieza el ultimo tema son 9 subtemas este es el uno de 9 ...

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >50 OR "+KEY_ID+" <53 "; //51, 52 empieza el mod5
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 26: //Empieza el ultimo tema son 9 subtemas este es el uno de 9 ...

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >52 OR "+KEY_ID+" <55 "; //53, 54 empieza el mod5
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 27: //Empieza el ultimo tema son 9 subtemas este es el uno de 9 ...

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >54 OR "+KEY_ID+" <57 "; //55, 56 empieza el mod5
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 28: //Empieza el ultimo tema son 9 subtemas este es el uno de 9 ...

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >56 OR "+KEY_ID+" <59 "; //57, 58 empieza el mod5
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 29: //Empieza el ultimo tema son 9 subtemas este es el uno de 9 ...

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >58 OR "+KEY_ID+" <61 "; //59, 60 empieza el mod5
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 30: //Empieza el ultimo tema son 9 subtemas este es el uno de 9 ...

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >60 OR "+KEY_ID+" <63 "; //61, 62 empieza el mod5
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
            case 31: //Empieza el ultimo tema son 9 subtemas este es el uno de 9 ...

                selectQuery = "SELECT  * FROM " + TABLE_QUEST+" where "+KEY_ID+" >59 OR "+KEY_ID+" <64 "; //63, 64 empieza el mod5
                cursor = dbase.rawQuery(selectQuery, null);//Se hace la consulta
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
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }

    public int tema(){
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(SELECT, null);//Se hace la consulta
        // looping through all rows and adding to list
        int teme=0;
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                do {
                    teme=cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        }
        return teme;
    }

    public void actualizarTema(int tem){
        dbase=this.getWritableDatabase();
        dbase.rawQuery("UPDATE "+TABLE_NAME_TEME+" SET "+TEME
                +" = "+tem+" WHERE id = 1",null);

    }
}
