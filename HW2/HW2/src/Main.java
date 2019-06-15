import javax.sound.midi.SysexMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;


public class Main {
    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("hh:mm:ss");
        Date date = new Date();
        String time = df.format(date);
        ExperimentList<Experiment> ExpList = new ExperimentList<Experiment>();

        /*Parameters : 1-setup(String)
                       2-day(integer)
                       3-time(String)
                       4-completed(boolean)
                       5-accuracy(float)
         */

        System.out.println("\n------>ADD EXPERIMENTS<------");
        ExpList.addExp(new Experiment("setup1-a",1,time,true,55.55f));
        ExpList.addExp(new Experiment("setup4-a",4,time,false,92.55f));
        ExpList.addExp(new Experiment("setup3-a",3,time,true,78.56f));
        ExpList.addExp(new Experiment("setup4-b",4,time,true,95.55f));
        ExpList.addExp(new Experiment("setup5-a",5,time,false,20.55f));
        ExpList.addExp(new Experiment("setup2-a",2,time,false,58.55f));
        ExpList.addExp(new Experiment("setup2-b",2,time,true,78.55f));
        ExpList.addExp(new Experiment("setup1-b",1,time,true,13.28f));
        ExpList.addExp(new Experiment("setup4-c",4,time,true,94.55f));
        ExpList.addExp(new Experiment("setup3-b",3,time,false,90.55f));
        ExpList.addExp(new Experiment("setup4-d",4,time,true,91.55f));
        ExpList.addExp(new Experiment("setup5-b",5,time,true,49.14f));
        ExpList.addExp(new Experiment("setup2-c",2,time,false,48.55f));
        ExpList.addExp(new Experiment("setup2-d",2,time,true,10.55f));

        Iterator iter = ExpList.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
        System.out.println("------>ADD EXPERIMENTS<------\n");
        System.out.println("\n");

        //-------------- Your listAll Function--------//
        ExpList.listAll();
        //-------------- Your listAll Function--------//

        System.out.println("\n");


        System.out.println("\n------>REMOVED EXPERIMENT<------");
        System.out.println(ExpList.removeExp(5,0));
        Iterator iter2 = ExpList.iterator();
        while (iter2.hasNext()){
            System.out.println(iter2.next());
        }
        System.out.println("------>REMOVED EXPERIMENT<------\n");


        System.out.println("\n------>GET EXPERIMENT<------");
        System.out.println(ExpList.getExp(1,0));
        System.out.println("------>GET EXPERIMENT<------\n");

        System.out.println("\n------>SET EXPERIMENT<------");
        Experiment setEX=new Experiment("setup4-k",4,time,true,1.55f);
        System.out.println(setEX);
        ExpList.setExp(4,1,setEX);
        System.out.println("------>SET EXPERIMENT<------\n");

        System.out.println("\n------>GET EXPERIMENT<------");
        System.out.println(ExpList.getExp(4,1));
        System.out.println("------>GET EXPERIMENT<------\n");

        System.out.println("\n------>LIST EXPERIMENTS<------");
        ExperimentList<Experiment> dayCompleted= ExpList.listExp(2);
        Iterator iter9 = dayCompleted.iterator();
        while (iter9.hasNext()){
            System.out.println(iter9.next());
        }
        System.out.println("\n------>LIST EXPERIMENTS<------");

        System.out.println("\n------>REMOVEDAY<------");
        ExpList.removeDay(1);
        Iterator iter100 = ExpList.iterator();
        while (iter100.hasNext()){
            System.out.println(iter100.next());
        }
        System.out.println("\n------>REMOVEDAY<------");


        System.out.println("\n------>ORDER DAY<------");
        ExpList.orderDay(4);
        Iterator iter10 = ExpList.iterator();
        while (iter10.hasNext()){
            System.out.println(iter10.next());

        }
        System.out.println("\n------>ORDER DAY<------");


        System.out.println("\n------>ORDER EXPERIMENTS<------");
        ExperimentList orderExperiment=ExpList.orderExperiments();
        Iterator iter101 = orderExperiment.iterator();
        while (iter101.hasNext()){
            System.out.println(iter101.next());
        }
        System.out.println("\n------>ORDER EXPERIMENTS<------");

        System.out.println("\n");

        //-------------- Your listAll Function--------//
        ExpList.listAll();
        //-------------- Your listAll Function--------//

        System.out.println("\n");

    }

}
