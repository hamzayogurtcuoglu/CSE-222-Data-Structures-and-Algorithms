

/**
 * Thread class there are 3 queues
 *
 */

public class threadClass extends Thread {

    static Pixel [][] pixelArray;
    private Thread t;
    private String threadName;

    private static priorityQueues PQLEX = new priorityQueues(2000,new LexComparator());
    private static priorityQueues PQEUC = new priorityQueues(2000,new EucComparator());
    private static priorityQueues PQBMX = new priorityQueues(2000,new BmqComparator());
    private static boolean flag = false ;
    private static int indexStart = 0;

    threadClass( String name) {
        threadName = name;
        System.out.println("Created " +  threadName );
    }


    /**
     * 4 threads are running in this function
     *
     */
    public void run() {

        try {
                if (threadName.compareTo("Thread 1")==0) {
                    for(int i = 0;i <pixelArray.length ; i++) {
                        for (int j = 0; j < pixelArray[0].length; j++) {
                            try {
                                synchronized (PQEUC) {
                                    if (PQEUC.getSize() == 2000){
                                        PQEUC.notify();
                                        PQEUC.wait();
                                    }

                                    PQEUC.insert(pixelArray[i][j]);
                                     if (indexStart>100&& (PQEUC.getSize() == 1||PQEUC.getSize() > 100))
                                        PQEUC.notify();
                                }
                                synchronized (PQLEX) {

                                    if (PQLEX.getSize()==2000) {
                                        PQLEX.notify();
                                        PQLEX.wait();
                                    }

                                    PQLEX.insert(pixelArray[i][j]);

                                    if (indexStart>100&&(PQLEX.getSize() == 1||PQLEX.getSize() > 100))
                                        PQLEX.notify();
                                }
                                synchronized (PQBMX) {
                                    if (PQBMX.getSize()==2000) {
                                        PQBMX.notify();
                                        PQBMX.wait();
                                    }

                                    PQBMX.insert(pixelArray[i][j]);

                                    if (indexStart>100&&(PQBMX.getSize() == 1||PQBMX.getSize() > 100))
                                        PQBMX.notify();
                                }

                                if (indexStart == 100) {
                                    System.out.println("inserting all the way to at least the first 100 pixels..");
                                }
                                indexStart++;
                                System.out.println(threadName + " : " + pixelArray[i][j]);

                            }catch (Exception e){
                                j--;
                            }
                        }
                    }
                }
                if (threadName.compareTo("Thread2-PQLEX")==0) {
                    int index = 0;
                    while (index != (pixelArray.length*pixelArray[0].length)) {
                        synchronized (PQLEX) {
                            try {

                                if (PQLEX.getSize() != 0 && indexStart > 100) {
                                    index++;

                                    System.out.println("Thread2-PQLEX : " + PQLEX.remove());
                                    if (PQLEX.getSize() == 1999)
                                        PQLEX.notify();

                                }
                                if (flag &&PQLEX.getSize() == 0)
                                    break;

                                if (!flag&&PQLEX.getSize() == 0)
                                    PQLEX.wait();

                            }catch (Exception e){
                                System.out.println("Interrupt " + threadName);
                            }
                        }
                    }
                }
                if (threadName.compareTo("Thread3-PQEUC")==0) {
                    int index = 0;
                    while (index != (pixelArray.length*pixelArray[0].length)) {
                         synchronized (PQEUC) {
                              try {
                                  if (PQEUC.getSize() != 0 && indexStart > 100) {
                                      index++;

                                      System.out.println("Thread3-PQEUC : " + PQEUC.remove());
                                      if (PQEUC.getSize() == 1999)
                                          PQEUC.notify();
                                  }

                                  if (flag && PQEUC.getSize() == 0)
                                      break;
                                  if (!flag&&PQEUC.getSize() == 0)
                                      PQEUC.wait();

                              }catch (Exception e){

                                  System.out.println("Interrupt " + threadName);

                              }

                         }
                    }
                }
                if (threadName.compareTo("Thread4-PQBMX")==0) {
                    int index = 0;
                    while (index != (pixelArray.length*pixelArray[0].length)) {
                         synchronized (PQBMX) {

                             try {
                                 if (PQBMX.getSize() != 0 && indexStart > 100) {
                                     index++;

                                     System.out.println("Thread4-PQBMX : " + PQBMX.remove());
                                     if (PQBMX.getSize() == 1999)
                                         PQBMX.notify();
                                 }
                                 if (flag && PQBMX.getSize() == 0)
                                     break;
                                 if (!flag&&PQBMX.getSize() == 0)
                                     PQBMX.wait();

                             }catch (Exception e){

                                 System.out.println("Interrupt " + threadName);

                             }

                         }
                    }
                }
        } catch (Exception e) {
            System.out.println(threadName+ " " + e  + " interrupted.");
            flag = true;
        }
        if (threadName.compareTo("Thread 1")==0){
            flag = true;
            synchronized (PQBMX) {
                PQBMX.notify();
            }
            synchronized (PQEUC) {
                PQEUC.notify();
            }
            synchronized (PQLEX) {
                PQLEX.notify();
            }
        }
        System.out.println("Thread " +  threadName + " is exiting.");
    }

    /**
     * firstly thread is starting
     */
    synchronized public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}


