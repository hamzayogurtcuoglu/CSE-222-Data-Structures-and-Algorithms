import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Experiment class has a atrributes
 * It is used a like node of ExperimentList(LinkedList)
 * Each Experiment has next but next null or not
 */
public class Experiment {
    private String setup ;
    private int day;
    private String time;
    private boolean completed;
    private float accuracy;


    public Experiment next=null;
    public Experiment nextDay=null;

    /**
     * Default contsructor of Experiment Class
     */
    public Experiment(){
        setup=null ;
        day = 0;
        time=null;
        completed = false;
        accuracy = 0.0f;
    }

    /**
     * Created Experiment object with this constructor
     *
     * @param setup explains the experimental setup
     * @param day represents the day of start
     * @param time represents the time of start
     * @param completed indicates whether it is completed or not
     * @param accuracy (not a valid value if the experiment is not
     * completed)
     */
    public Experiment(String setup,int day,String time,boolean completed,float accuracy){
        this.setup=setup ;
        this.day = day;
        DateFormat df = new SimpleDateFormat("hh:mm:ss");
        Date date = new Date();
        this.time = df.format(date);
//        this.time=time;
        this.completed = completed;
        this.accuracy = accuracy;
    }

    /**
     * getter of Setup
     * @return setup
     */
    public String getSetup() {
        return setup;
    }

    /**
     * Getter of day
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * getter of the experiment time
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * Getter of the completed experiment or false
     * @return completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Getter of accuracy
     * @return accuracy
     */
    public float getAccuracy() {
        return accuracy;
    }

    /**
     * setter of setup
     * @param setup
     */
    public void setSetup(String setup) {
        this.setup = setup;
    }

    /**
     * setter of day
     * @param day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * setter of time
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * setter of completed
     * @param completed
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * setter of accuracy
     * @param accuracy
     */
    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * Experiment object toString
     * @return String Attributes of Experiment
     */
    @Override
    public String toString() {
        return "Experiment{" +
                "setup='" + setup + '\'' +
                ", day=" + day +
                ", time='" + time + '\'' +
                ", completed=" + completed +
                ", accuracy=" + accuracy +
                '}';
    }
}
