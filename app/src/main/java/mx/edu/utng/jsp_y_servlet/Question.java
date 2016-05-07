package mx.edu.utng.jsp_y_servlet;

/**
 * Created by NORMA on 16/03/2016.
 */
public class Question {
    private int ID;
    private String QUESTION;
    private String OPTA;
    private String OPTB;
    private String OPTC;
    private String ANSWER;
    public Question()
    {
        ID=0;
        QUESTION="";
        OPTA="";
        OPTB="";
        OPTC="";
        ANSWER="";
    }
    public Question(String qUESTION, String oPTA, String oPTB, String oPTC,
                    String aNSWER) {

        QUESTION = qUESTION;//Pregunta
        OPTA = oPTA;
        OPTB = oPTB;
        OPTC = oPTC;
        ANSWER = aNSWER;//resultado correct
    }

    public int getID() {
        return ID;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public String getOPTA() {
        return OPTA;
    }

    public String getOPTB() {
        return OPTB;
    }

    public String getOPTC() {
        return OPTC;
    }

    public String getANSWER() {
        return ANSWER;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }

    public void setOPTA(String OPTA) {
        this.OPTA = OPTA;
    }

    public void setOPTB(String OPTB) {
        this.OPTB = OPTB;
    }

    public void setOPTC(String OPTC) {
        this.OPTC = OPTC;
    }

    public void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }
}
