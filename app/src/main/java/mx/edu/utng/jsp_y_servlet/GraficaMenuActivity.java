package mx.edu.utng.jsp_y_servlet;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.androidplot.Plot;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.StepFormatter;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import mx.edu.utng.jsp_y_servlet.util.DBAdapter;

/**
 * Created by usuario1 on 28/03/2016.
 */
public class GraficaMenuActivity extends ActionBarActivity {
    private XYPlot myxyPlotJsp;
    private XYPlot myxyPlotServlet;
    private DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafica_menu_layout);
        initComponents();
    }

    private void initComponents() {
        dbAdapter=new DBAdapter(this);
        dbAdapter.open();
        String idU=String.valueOf(FormLoginActivity.ID_U);
        int idM=dbAdapter.idPrimerModuloIns(idU, "Modulo_1");

        int modUnoTema1=dbAdapter.mostrarCalificacion(idM,1,1);
        int modUnoTema2=dbAdapter.mostrarCalificacion(idM,1,2);
        int modUnoTema3=dbAdapter.mostrarCalificacion(idM,1,3);
        int modUnoTema4=dbAdapter.mostrarCalificacion(idM,1,4);
        int modUnoTema5=dbAdapter.mostrarCalificacion(idM,1,5);
        int modUnoTema6=dbAdapter.mostrarCalificacion(idM,1,6);
        int modUnoTema7=dbAdapter.mostrarCalificacion(idM,1,7);
        int modUnoTema8=dbAdapter.mostrarCalificacion(idM,1,8);

        myxyPlotJsp =(XYPlot)findViewById(R.id.xyp_grafica_jsp);

        Number[] series1Numbers = {0,modUnoTema1,modUnoTema2,modUnoTema3,modUnoTema4,modUnoTema5,modUnoTema6,modUnoTema7,modUnoTema8,0};

        XYSeries series2 = new SimpleXYSeries(
                Arrays.asList(series1Numbers),
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
                "Calificacion");

        myxyPlotJsp.getGraphWidget().getDomainOriginLinePaint().setColor(Color.BLACK);//Borde interior
        myxyPlotJsp.getGraphWidget().getRangeOriginLinePaint().setColor(Color.BLACK);//Borde interior

        myxyPlotJsp.setBorderStyle(Plot.BorderStyle.SQUARE, null, null);//grafiac de barras
        myxyPlotJsp.getBorderPaint().setStrokeWidth(1);
        myxyPlotJsp.getBorderPaint().setAntiAlias(false);
        myxyPlotJsp.getBorderPaint().setColor(Color.BLACK);//Borde de toda la grafica

        Paint lineFill = new Paint();
        lineFill.setAlpha(200);
        lineFill.setShader(new LinearGradient(0, 0, 0, 250, Color.WHITE, Color.rgb(159,39,176), Shader.TileMode.MIRROR));

        StepFormatter stepFormatter  = new StepFormatter(Color.rgb(0, 0,0), Color.RED);
        stepFormatter.getLinePaint().setStrokeWidth(1);//Grosor del borde de las lineas de las barras

        stepFormatter.getLinePaint().setAntiAlias(false);
        stepFormatter.setFillPaint(lineFill);
        myxyPlotJsp.addSeries(series2, stepFormatter);
        //Numeraciones
        myxyPlotJsp.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 1);
        myxyPlotJsp.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);
        myxyPlotJsp.setTicksPerRangeLabel(1);
        myxyPlotJsp.setTicksPerDomainLabel(1);

        myxyPlotJsp.setDomainLabel("Temas");
        myxyPlotJsp.setRangeLabel("Calificaciónes");
        myxyPlotJsp.setDomainValueFormat(new DecimalFormat("0"));//Para que solo aparescan numeros enteros
        myxyPlotJsp.setTitle("Grafica de JSP");

        //------------------------------------------------Serv

        modUnoTema1=dbAdapter.mostrarCalificacion(idM,2,1);
        modUnoTema2=dbAdapter.mostrarCalificacion(idM,2,2);
        modUnoTema3=dbAdapter.mostrarCalificacion(idM,2,3);
        modUnoTema4=dbAdapter.mostrarCalificacion(idM,2,4);
        modUnoTema5=dbAdapter.mostrarCalificacion(idM,2,5);
        modUnoTema6=dbAdapter.mostrarCalificacion(idM,2,6);
        modUnoTema7=dbAdapter.mostrarCalificacion(idM,2,7);
        modUnoTema8=dbAdapter.mostrarCalificacion(idM,2,8);
        myxyPlotServlet =(XYPlot)findViewById(R.id.xyp_grafica_servlet);

        Number[] series1NumbersDos = {0,modUnoTema1,modUnoTema2,modUnoTema3,modUnoTema4,modUnoTema5,modUnoTema6,modUnoTema7,modUnoTema8,0};

        XYSeries series4 = new SimpleXYSeries(
                Arrays.asList(series1NumbersDos),
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
                "Calificacion");

        myxyPlotServlet.getGraphWidget().getDomainOriginLinePaint().setColor(Color.BLACK);//Borde interior
        myxyPlotServlet.getGraphWidget().getRangeOriginLinePaint().setColor(Color.BLACK);//Borde interior

        myxyPlotServlet.setBorderStyle(Plot.BorderStyle.SQUARE, null, null);//grafiac de barras
        myxyPlotServlet.getBorderPaint().setStrokeWidth(1);
        myxyPlotServlet.getBorderPaint().setAntiAlias(false);
        myxyPlotServlet.getBorderPaint().setColor(Color.BLACK);//Borde de toda la grafica

        Paint lineFill2 = new Paint();
        lineFill2.setAlpha(200);
        lineFill2.setShader(new LinearGradient(0, 0, 0, 250, Color.WHITE, Color.rgb(159, 39, 176), Shader.TileMode.MIRROR));

        stepFormatter  = new StepFormatter(Color.rgb(0, 0,0), Color.RED);
        stepFormatter.getLinePaint().setStrokeWidth(1);//Grosor del borde de las lineas de las barras

        stepFormatter.getLinePaint().setAntiAlias(false);
        stepFormatter.setFillPaint(lineFill2);
        myxyPlotServlet.addSeries(series4, stepFormatter);
        //Numeraciones
        myxyPlotServlet.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 1);
        myxyPlotServlet.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);
        myxyPlotServlet.setTicksPerRangeLabel(1);
        myxyPlotServlet.setTicksPerDomainLabel(1);

        myxyPlotServlet.setDomainLabel("Temas");
        myxyPlotServlet.setRangeLabel("Calificaciónes");
        myxyPlotServlet.setDomainValueFormat(new DecimalFormat("0"));//Para que solo aparescan numeros enteros
        myxyPlotServlet.setTitle("Grafica de JSP");


    }
}
