package mx.edu.utng.jsp_y_servlet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.annotation.TargetApi;
import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.os.Build;
import java.util.concurrent.TimeUnit;
import java.util.List;

import mx.edu.utng.jsp_y_servlet.util.DBAdapter;


public class QuizActivity extends AppCompatActivity {
    List<Question> quesList;
    int score=0;
    int qid=0;
    int position;
    Question currentQ;
    TextView txtQuestion, times, scored;
    RadioButton rda, rdb, rdc;
    Button butNext;
    private DBAdapter dbAdapter;
    CounterClass timer;

    private Bundle valoresRecibidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        dbAdapter=new DBAdapter(this);
        dbAdapter.open();
        DBHelperQuiz db=new DBHelperQuiz(this);


        //Se va a hacer una comparacion para que traiga solo 5 x temarios

        valoresRecibidos=getIntent().getExtras();

        switch (valoresRecibidos.getInt("lenguaje")) {
            case R.id.lsv_menu_principal_servlet:
                switch (valoresRecibidos.getInt("posicionTema")){
                    case 1:
                        quesList=db.getAllQuestions(0);//Me trae las preguntas
                        break;
                    case 2:
                        quesList=db.getAllQuestions(1);//Me trae las preguntas
                        break;
                    case 3:
                        quesList=db.getAllQuestions(2);//Me trae las preguntas
                        break;
                    case 4:
                        quesList=db.getAllQuestions(3);//Me trae las preguntas
                        break;
                    case 5:
                        quesList=db.getAllQuestions(4);//Me trae las preguntas
                        break;
                    case 6:
                        quesList=db.getAllQuestions(5);//Me trae las preguntas
                        break;
                    case 7:
                        quesList=db.getAllQuestions(6);//Me trae las preguntas
                        break;
                    case 8:
                        quesList=db.getAllQuestions(7);//Me trae las preguntas
                        break;
                }
                break;
            case R.id.lsv_menu_principal_jsp:
                switch (valoresRecibidos.getInt("posicionTema")){
                    case 1:
                        quesList=db.getAllQuestions(8);//Me trae las preguntas
                        break;
                    case 2:
                        quesList=db.getAllQuestions(9);//Me trae las preguntas
                        break;
                    case 3:
                        quesList=db.getAllQuestions(10);//Me trae las preguntas
                        break;
                    case 4:
                        quesList=db.getAllQuestions(11);//Me trae las preguntas
                        break;
                    case 5:
                        quesList=db.getAllQuestions(12);//Me trae las preguntas
                        break;
                    case 6:
                        quesList=db.getAllQuestions(13);//Me trae las preguntas
                        break;
                    case 7:
                        quesList=db.getAllQuestions(14);//Me trae las preguntas
                        break;
                    case 8:
                        quesList=db.getAllQuestions(15);//Me trae las preguntas
                        break;
                    case 9:
                        quesList=db.getAllQuestions(16);//Me trae las preguntas
                        break;
                    case 10:
                        quesList=db.getAllQuestions(17);//Me trae las preguntas
                        break;
                    case 11:
                        quesList=db.getAllQuestions(18);//Me trae las preguntas
                        break;
                    default:
                        break;
                }
                break;
        }
                currentQ=quesList.get(qid);
        txtQuestion=(TextView)findViewById(R.id.textView1);
        rda=(RadioButton)findViewById(R.id.radio0);
        rdb=(RadioButton)findViewById(R.id.radio1);
        rdc=(RadioButton)findViewById(R.id.radio2);
        butNext=(Button)findViewById(R.id.button1);

        scored = (TextView) findViewById(R.id.score);
        // the timer
        times = (TextView) findViewById(R.id.timers);
        // method which will set the things up for our game
        setQuestionView();
        times.setText("00:02:00");
        // A timer of 60 seconds to play for, with an interval of 1 second (1000 milliseconds)
        timer = new CounterClass(130000, 1000);
        timer.start();

        //setQuestionView();//Rellena la informacion de cada pregunta

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);

                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
                Log.d("yourans", currentQ.getANSWER()+" "+answer.getText());
                if(currentQ.getANSWER().equals(answer.getText()))
                {
                    score++;
                    Log.d("score", "Your score"+score);
                }
                if(qid<4){//Numero de preguntas
                    currentQ=quesList.get(qid);
                    setQuestionView();
                }else{//Si las preguntas superan el numero
                 ///////////////////////////////////////////////////////////////////////////////////
                    String idU=String.valueOf(FormLoginActivity.ID_U);
                   int idM=dbAdapter.idPrimerModuloIns(idU,"Modulo_1");
                    Log.e("Id modulo", "onClick: "+idM );
                    switch (valoresRecibidos.getInt("lenguaje")) {
                        case R.id.lsv_menu_principal_servlet:
                            Log.e("switch", "onClick: Servlet" );
                            switch (valoresRecibidos.getInt("posicionTema")){
                                case 1:
                                    Log.e("switch", "onClick: Servlet tema 1" );
                                    if (score>=3){
                                        dbAdapter.ingresarCalificacion(score,1,idM,1);
                                        dbAdapter.activarTema(idM,1,1);
                                    }
                                    break;
                                case 2:
                                    if (score>=3){dbAdapter.ingresarCalificacion(score,2,idM,1);
                                        dbAdapter.activarTema(idM,1,2);
                                    }
                                    break;
                                case 3:
                                    dbAdapter.activarTema(idM,1,3);
                                    if (score>=3){dbAdapter.ingresarCalificacion(score,3,idM,1);
                                        dbAdapter.activarTema(idM,1,3);
                                    }
                                    break;
                                case 4:
                                    if (score>=3){dbAdapter.ingresarCalificacion(score,4,idM,1);
                                        dbAdapter.activarTema(idM,1,4);
                                    }
                                    break;
                                case 5:
                                    if (score>=3){dbAdapter.ingresarCalificacion(score,5,idM,1);
                                        dbAdapter.activarTema(idM,1,5);
                                    }
                                    break;
                                case 6:
                                    if (score>=3) {
                                        dbAdapter.ingresarCalificacion(score, 6, idM, 1);
                                        dbAdapter.activarTema(idM, 1, 6);
                                    }
                                    break;
                                case 7:
                                    if (score>=3){dbAdapter.ingresarCalificacion(score,7,idM,1);
                                        dbAdapter.activarTema(idM, 1, 7);
                                    }
                                    break;
                                case 8:
                                    if (score>=3){dbAdapter.ingresarCalificacion(score,8,idM,1);
                                        dbAdapter.activarTema(idM, 1, 8);
                                    }
                                    break;
                            }
                            break;
                        case R.id.lsv_menu_principal_jsp:
                            switch (valoresRecibidos.getInt("posicionTema")){
                                case 1:
                                    if (score>=3){dbAdapter.ingresarCalificacion(score,1,idM,2);
                                        dbAdapter.activarTema(idM,2,1);
                                    }
                                    break;
                                case 2:
                                    if (score>=3){dbAdapter.ingresarCalificacion(score,2,idM,2);
                                        dbAdapter.activarTema(idM, 2, 2);
                                    }
                                    break;
                                case 3:
                                    if (score>=3){dbAdapter.ingresarCalificacion(score,3,idM,2);
                                        dbAdapter.activarTema(idM, 2, 3);
                                    }
                                    break;
                                case 4:
                                    if (score>=3){dbAdapter.ingresarCalificacion(score,4,idM,2);
                                        dbAdapter.activarTema(idM, 2, 4);
                                    }
                                    break;
                                case 5:
                                    if (score>=3){dbAdapter.ingresarCalificacion(score,5,idM,2);
                                        dbAdapter.activarTema(idM, 2, 5);
                                    }
                                    break;
                                case 6:
                                    if (score>=3){dbAdapter.ingresarCalificacion(score,6,idM,2);
                                        dbAdapter.activarTema(idM, 2, 6);
                                    }
                                    break;
                                case 7:
                                    if (score>=3){dbAdapter.ingresarCalificacion(score,7,idM,2);
                                        dbAdapter.activarTema(idM, 2, 7);
                                    }
                                    break;
                                case 8:
                                    if (score>=3){dbAdapter.ingresarCalificacion(score,8,idM,2);
                                        dbAdapter.activarTema(idM, 2, 8);
                                    }
                                    break;
                                default:
                                    break;
                            }
                            break;
                    }
                    ///////////////////////////////////////////////////////////////////////////////////

                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    termino();
                }
            }
        });
    }
    private void termino(){
        timer.cancel();
        finish();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onFinish() {
            times.setText("El tiempo acabo");
            termino();
        }
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            long millis = millisUntilFinished;
            String hms = String.format(
                    "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            System.out.println(hms);
            times.setText(hms);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }
    private void setQuestionView()
    {

        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        qid++;
    }
}
