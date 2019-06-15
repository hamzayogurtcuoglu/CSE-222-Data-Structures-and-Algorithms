import java.util.Iterator;
import java.lang.Iterable;

/**
 * ExperimentList class to keep track of some machine
 * learning experiments and their results.
 * @param <T> ExperimentsList can be any type.
 */
public class ExperimentList<T> implements Iterable<Experiment>{

    public Iterable<Experiment> iterator;
    public Experiment head = null;
    private int size = 0;

    /**
     * @return size of the linkedlist experiments
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size setter of size of linklist
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     *Class Override Iterator For ExperimentList
     * @return
     */
    @Override
    public Iterator<Experiment> iterator() {

        Iterator<Experiment> iterator = new Iterator<Experiment>() {
            private Experiment temp2 = null; // Reference variable to a temporary node.
            private Experiment current = head;

            /**
             * Controlling current has a next Experiment or not
             * @return true or false according to above condition
             */
            @Override
            public boolean hasNext() { // True if there is a next Object in the SimpleLinkedList.
                //return current != null && current.next != null;
                if(current == null) {
                    return false;
                }
                else {
                    return true;
                }
            }

            /**
             * if current experiment has a next that is changed with next Experiment
             * @return next experiment
             */
            @Override
            public Experiment next() { // Return the object.
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                temp2 = current;
                current = current.next;
                return temp2;
            }
        };
        return iterator; // Return an iterator that has just been called.
    }

    /**
     * insert experiment to the end of the day
     * @param exp Experiment node is adding in the listedlist
     */
    public void addExp(Experiment exp){

        try {

            if (size == 0) {
                head = exp;
                size++;
            } else {
                Experiment pre = head; //previous node according to adding node
                Experiment temp = head; //temp adding node

                for (int i = 0; i < size && pre != null && (pre.getDay() != exp.getDay()); i++) {

                    if (exp.getDay() > pre.getDay()) {
                        if (pre.next != null) {
                            if (exp.getDay() > pre.next.getDay())
                                pre = pre.next;
                        }
                    }
                }

                while(pre.next!=null&&pre.next.getDay()==exp.getDay()) {
                    pre = pre.next;
                }
                if (pre.next != null) {
                    temp = pre.next;
                    pre.next = exp;
                    exp.next = temp;
                } else {
                    pre.next = exp;
                }
                size++;
            }


            Experiment pre2 = head;
            Experiment temp2 = head;
            if (size != 1) {
                for (int i = 0; i < size && pre2.next != null; i++) {
                    // System.out.println(pre2.next);
                    if (pre2.getDay() < pre2.next.getDay()) {
                        temp2.nextDay = pre2.next;
                        temp2 = pre2.next;
                        pre2 = pre2.next;
                    } else {
                        pre2 = pre2.next;
                    }
                }
            }
        }catch (NullPointerException e){
            System.out.println("ERROR! You are wrongly using this function.");
        }
    }

    /**
     * get the experiment with the given day and position
     * Getting according to days and index in linkedlist
     * @param day jumping to days
     * @param index finding index of a day
     * @return experiment that is found then setting of the experiment
     */
    public Experiment getExp(int day,int index){
        try {
            Experiment NEXT = head;
            int flag = 0;

            if (NEXT != null && NEXT.getDay() == day) {
                flag = 1;
            }

            while (flag == 0 && NEXT.nextDay != null) {

                if (NEXT.nextDay.getDay() == day) {
                    flag = 1;
                    NEXT = NEXT.nextDay;
                } else {
                    NEXT = NEXT.nextDay;
                }
            }
            for (int i = 0; i < index ; i++) {
                if (NEXT.next.getDay() == day)
                    NEXT = NEXT.next;
                else
                    throw new IndexOutOfBoundsException();

            }
            return NEXT;
        }catch (IndexOutOfBoundsException e){
            return null;
        }catch (NullPointerException e){
            return null;
        }
    }

    /**
     * set the experiment with the given day and position
     * Setting according to days and index in linkedlist
     * @param day jumping to days
     * @param index finding index of a day
     * @param exp experiment that is found then setting of the experiment
     */
    public void setExp(int day,int index,Experiment exp){
        try {
            Experiment pre = head;
            int flag = 0;

            if (pre != null && pre.getDay() == day) {
                flag = 1;
            }

            while (flag == 0 && pre.nextDay != null) {

                if (pre.nextDay.getDay() == day) {
                    flag = 1;
                    pre = pre.nextDay;
                } else {
                    pre = pre.nextDay;
                }
            }
            for (int i = 0; i < index; i++) {
                if (pre.next.getDay() == day)
                    pre = pre.next;
                else
                    throw new IndexOutOfBoundsException();
            }
            if (pre.getDay() == exp.getDay()) {
                pre.setCompleted(exp.isCompleted());
                pre.setAccuracy(exp.getAccuracy());
                pre.setTime(exp.getTime());
                pre.setSetup(exp.getSetup());
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("NO DAYS OR INDEX");
        }catch (NullPointerException e){
            System.out.println("NO DAYS OR INDEX");
        }
    }


    /**
     * remove the experiment specified as index from given day
     *
     * All situation is checked according to
     * 1)Head
     * 2)Head of Days
     * 3)Last Experiment of days
     * 4)Middles of experiments of a day
     *
     * @param day
     * @param index
     * @return
     */
    public boolean removeExp(int day,int index){
        try {


            Experiment temp = head;

            if (head.getDay() == day) {
                //sadece head için yapılan if
                if (index == 0) {
                    if (head.next.getDay() == day) {
                        temp = head.next;
                        temp.nextDay = head.nextDay;
                        head = temp;
                    } else {
                        head = head.next;
                    }
                } else {
                    //headin içindeki exp kaldırmak için gerekli else
                    Experiment temp3 = head;
                    for (int i = 0; i < index - 1; i++) {
                        temp3 = temp3.next;
                    }
                    if (temp3.next.next != null) {
                        temp.next = temp3.next.next;
                    } else {
                        temp.next = null;
                    }
                }

            } else {
                Experiment preNextDay = head;
                Experiment pre = head;

                int flag = 0;
                //bir sonraki günü
                while (flag == 0 && pre.nextDay != null) {
                    if (pre.nextDay.getDay() == day) {
                        flag = 1;
                        if (pre.nextDay != head)
                            preNextDay = pre;
                        pre = pre.nextDay;
                    } else {
                        preNextDay = pre;
                        pre = pre.nextDay;
                    }
                }
                //preNextDayin en son experimentini bulan while
                Experiment preNextDayTemp = preNextDay;
                while (preNextDayTemp.getDay() == preNextDayTemp.next.getDay()) {
                    preNextDayTemp = preNextDayTemp.next;
                }

                if (index == 0) {
                    preNextDayTemp.next = pre.next;
                    preNextDay.nextDay = pre.next;
                    size--;
                    return true;
                } else {
                    Experiment nextTemp = pre;
                    for (int i = 0; i < index - 1; i++) {
                        nextTemp = nextTemp.next;
                    }
                    //eğer nextin nexti dolu veya boş ise
                    if (nextTemp.next.next != null) {
                        size--;
                        nextTemp.next = nextTemp.next.next;
                        return true;
                    } else {
                        size--;
                        nextTemp.next = null;
                        return true;
                    }
                }
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("NO DAYS OR INDEX");
            return false;
        }catch (NullPointerException e){
            System.out.println("NO DAYS OR INDEX");
            return false;
        }
        return false;
    }

    /**
     * list all completed experiments in a given day
     * Completed Experiments Listed and returned
     *  @param day according to day that has completed attributed is checked
     * @return Completed experiment linkedlist
     */

    public ExperimentList<Experiment> listExp(int day){

        ExperimentList<Experiment> list = new ExperimentList<Experiment>();
        Experiment myDay = head;
        try {


            while (myDay.getDay() != day) {
                myDay = myDay.nextDay;
            }

            while (myDay != null && myDay.getDay() == day) {
                if (myDay.isCompleted() == true) {

                    if (list.size == 0) {
                        list.head = new Experiment(myDay.getSetup(), myDay.getDay(), myDay.getTime(), myDay.isCompleted(), myDay.getAccuracy());
                        list.size++;
                    } else {

                        Experiment temp = list.head;
                        while (temp.next != null && myDay.getDay() == day) {
                            temp = temp.next;
                        }
                        //System.out.println("hamza "+new Experiment(myDay.getSetup(),myDay.getDay(),myDay.getTime(),myDay.isCompleted(),myDay.getAccuracy()));
                        temp.next = new Experiment(myDay.getSetup(), myDay.getDay(), myDay.getTime(), myDay.isCompleted(), myDay.getAccuracy());
                    }
                }
                myDay = myDay.next;
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("NO DAYS OR INDEX");
            return null;
        }catch (NullPointerException e){
            System.out.println("NO DAYS OR INDEX");
            return null;
        }
        return list;
    }

    /**
     * -remove all experiments in a given day-
     * According to head of linkedlist or other situation are handled
     * then all days removed through nextday pointers.
     * @param day
     * @return
     */

    public boolean removeDay(int day){
     try {

         Experiment removedDays = head;
         Experiment TempDays = head;
         boolean headFlag = false;
         while (removedDays.getDay() != day) {
             TempDays = removedDays;
             removedDays = removedDays.nextDay;
             headFlag = true;
         }
         int index = 0;
         Experiment indexOrderedDay = removedDays; //ayrıca bu next dayide tutar
         while (indexOrderedDay != null && day == indexOrderedDay.getDay()) {
             index++;
             indexOrderedDay = indexOrderedDay.next;
         }

         size = size - index;

         Experiment lastTempDay = TempDays;
         boolean lastFlag = false;
         while (headFlag == true && headFlag && lastTempDay.next.getDay() == lastTempDay.getDay()) {
             lastTempDay = lastTempDay.next;
             lastFlag = true;
         }
         if (lastFlag) {
             if (removedDays.nextDay != null) {
                 lastTempDay.next = removedDays.nextDay;
                 TempDays.nextDay = removedDays.nextDay;
                 return true;
             } else
                 lastTempDay.next = null;
             TempDays.nextDay = null;
             return true;
         } else {
             head = head.nextDay;
             return true;
         }
     }catch (IndexOutOfBoundsException e){
         System.out.println("NO DAYS OR INDEX");
         return false;
     }catch (NullPointerException e){
         System.out.println("NO DAYS OR INDEX");
         return false;
     }
    }

    /**
     * sorts the experiments in a given day according to the accuracy, the
     * changes will be done on the list
     *
     * ALL Experiments of indicated days is put a Experiments array
     * then sorted and putting
     * orginal linkedlist
     * @param day
     */

    public void orderDay(int day){
        try {

            Experiment orderedDay = head;
            Experiment TempDays = head;
            boolean headFlag = false ;
            while(orderedDay.getDay() != day){
                TempDays = orderedDay;
                orderedDay = orderedDay.nextDay;
                headFlag = true;
            }
            Experiment lastTempDay = TempDays;
            boolean lastFlag = false;
            while (headFlag==true&&headFlag&&lastTempDay.next.getDay()==lastTempDay.getDay()){
                lastTempDay = lastTempDay.next;
                lastFlag = true;
            }

            int index = 0;
            Experiment indexOrderedDay = orderedDay; //ayrıca bu next dayide tutar
            while(indexOrderedDay !=null && day == indexOrderedDay.getDay()){
                index++;
                indexOrderedDay = indexOrderedDay.next;
            }

            Experiment bigExperiment = orderedDay;
            Experiment tempOrder = orderedDay;

            Experiment[] orderDayExperiments = new Experiment[index];
            for (int i = 0;i<index;i++){
                orderDayExperiments[i] = new Experiment(tempOrder.getSetup(),tempOrder.getDay(),tempOrder.getTime(),tempOrder.isCompleted(),tempOrder.getAccuracy());
                tempOrder = tempOrder.next;
            }
            Experiment biggest;
            Experiment temp;

            boolean swapped = true;
            int j = 0;
            Experiment tmp;
            while (swapped) {
                swapped = false;
                j++;
                for (int i = 0; i < index - j; i++) {
                    if (orderDayExperiments[i].getAccuracy() > orderDayExperiments[i + 1].getAccuracy()) {
                        tmp = orderDayExperiments[i];
                        orderDayExperiments[i] = orderDayExperiments[i + 1];
                        orderDayExperiments[i + 1] = tmp;
                        swapped = true;
                    }
                }
            }
            TempDays.nextDay = orderDayExperiments[index-1];
            lastTempDay.next = orderDayExperiments[index-1];
            if (lastTempDay.getDay() == day){
                head = orderDayExperiments[index-1];
                head.nextDay = indexOrderedDay;
                orderDayExperiments[0].next = indexOrderedDay;
            }
            else if (indexOrderedDay != null) {
                 orderDayExperiments[0].next = indexOrderedDay;
                 orderDayExperiments[index-1].nextDay = indexOrderedDay;
             }
            for (int i = index-1;i>0;i--){
                orderDayExperiments[i].next = orderDayExperiments[i-1];
            }

        }catch (IndexOutOfBoundsException e){
            System.out.println("NO DAY ");
        }catch (NullPointerException e){
            System.out.println("NO DAY");
        }
    }

    /**
     * sorts all the experiments in the list according to the accuracy, the
     * original list should not be changed since the day list may be damage
     *
     * ALL Experiments is put a Experiments array then sorted and created a linkedlist
     * @return According to accuracy of Experiments is sorted and returned
     */
    public ExperimentList<Experiment> orderExperiments(){

        ExperimentList<Experiment> list = new ExperimentList<Experiment>();
        Experiment[] orderDayExperiments = new Experiment[size];
        Experiment header = head;
        try {


            for (int i = 0; i < size; i++) {
                orderDayExperiments[i] = new Experiment(header.getSetup(), header.getDay(), header.getTime(), header.isCompleted(), header.getAccuracy());
                header = header.next;
            }

            boolean swapped = true;
            int j = 0;
            Experiment tmp;
            while (swapped) {
                swapped = false;
                j++;
                for (int i = 0; i < size - j; i++) {
                    if (orderDayExperiments[i].getAccuracy() > orderDayExperiments[i + 1].getAccuracy()) {
                        tmp = orderDayExperiments[i];
                        orderDayExperiments[i] = orderDayExperiments[i + 1];
                        orderDayExperiments[i + 1] = tmp;
                        swapped = true;
                    }
                }
            }
            for (int i = size - 1; 0 <= i; i--) {
                if (i == size - 1) {
                    list.head = orderDayExperiments[i];
                } else {
                    Experiment temp = list.head; //temp adding node
                    while (temp.next != null) {
                        temp = temp.next;
                    }
                    temp.next = orderDayExperiments[i];
                }
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("NO DAY ");
        }catch (NullPointerException e){
            System.out.println("NO DAY");
        }
        return list;
    }
    public void listAll()
    {
        System.out.println("List experiment view:");
        Experiment last = head;
        while( last != null) {
            System.out.println(last.toString());
            last = last.next;
        }
        System.out.println("List day view:");
        last = head;
        while( last != null) {
            System.out.println(last.toString());
            last = last.nextDay;
        }
    }
}
