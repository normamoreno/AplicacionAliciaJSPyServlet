package mx.edu.utng.jsp_y_servlet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CalculadoraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
    }

    Double numero1,numero2,resultado;
    String operador;

    public void onClickIgual(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.txtNumero) ;
        numero2 = Double.parseDouble(tv.getText().toString());

        if(operador.equals("+"))
        {
            resultado= numero1+numero2;
        }
        else if(operador.equals("-"))
        {
            resultado= numero1-numero2;
        }
        else if(operador.equals("*"))
        {
            resultado= numero1*numero2;
        }
        else if(operador.equals("/"))
        {
            resultado= numero1/numero2;
        }else{
            resultado= numero1;
        }
        tv.setText(resultado.toString());
    }

    public void onClickSuma(View miView)
    {
        operador="+";
        onClickOperacionCapturaNumero1(miView);
    }
    public void onClickResta(View miView)
    {
        operador="-";
        onClickOperacionCapturaNumero1(miView);
    }
    public void onClickMultiplicacion(View miView)
    {
        operador="*";
        onClickOperacionCapturaNumero1(miView);
    }
    public void onClickDivision(View miView)
    {
        operador="/";
        onClickOperacionCapturaNumero1(miView);
    }

    public void onClickOperacionCapturaNumero1(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.txtNumero) ;
        numero1 = Double.parseDouble(tv.getText().toString());
        tv.setText("");
    }

    public void onClickLimpia(View miView)
    {
        numero1=0.0;
        numero2=0.0;
        TextView tv = (TextView) findViewById(R.id.txtNumero) ;
        tv.setText("");
    }

    public void onClickBtn1(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.txtNumero) ;
        tv.setText(tv.getText() + "1");
    }
    public void onClickBtn2(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.txtNumero) ;
        tv.setText(tv.getText() + "2");
    }
    public void onClickBtn3(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.txtNumero) ;
        tv.setText(tv.getText() + "3");
    }
    public void onClickBtn4(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.txtNumero) ;
        tv.setText(tv.getText() + "4");
    }
    public void onClickBtn5(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.txtNumero) ;
        tv.setText(tv.getText() + "5");
    }
    public void onClickBtn6(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.txtNumero) ;
        tv.setText(tv.getText() + "6");
    }
    public void onClickBtn7(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.txtNumero) ;
        tv.setText(tv.getText() + "7");
    }
    public void onClickBtn8(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.txtNumero) ;
        tv.setText(tv.getText() + "8");
    }
    public void onClickBtn9(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.txtNumero) ;
        tv.setText(tv.getText() + "9");
    }
    public void onClickBtn0(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.txtNumero) ;
        tv.setText(tv.getText() + "0");
    }
    public void onClickBtnPunto(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.txtNumero) ;
        tv.setText(tv.getText() + ".");
    }
}