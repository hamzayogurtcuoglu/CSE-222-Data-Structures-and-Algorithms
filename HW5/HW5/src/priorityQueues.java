import java.util.Comparator;

public class priorityQueues {
    private Pixel [] Heap = null;
    private int size;

    public int getSize() {
        return size;
    }

    private int maxQueue;
    private Comparator<Pixel> comparator;

    public priorityQueues(int queueMax, Comparator<Pixel> comparator)
    {
        this.comparator = comparator;
        this.maxQueue = queueMax;
        Heap = new Pixel[this.maxQueue + 1];
        this.size = 0;
        Heap[0] = null;
    }

    /**
     * calculating right child index
     * @param current
     * @return
     */
    private int rightChild(int current)
    {
        return (2 * current) + 1;
    }
    /**
     * swap function if down node bigger than upper node
     * @param newCurrent
     * @param current
     */
    private void swap(int newCurrent, int current)
    {
        Pixel temp;
        temp = Heap[newCurrent];
        Heap[newCurrent] = Heap[current];
        Heap[current] = temp;
    }


    /**
     * calculating parent index
     * @param current
     * @return
     */
    private int parent(int current)
    {
        return current / 2;
    }

    /**
     * calculating left child index
     * @param current
     * @return
     */
    private int leftChild(int current)
    {
        return (2 * current);
    }

    /**
     * checking node is leaf or not
     * @param current
     * @return
     */
    private boolean leafOrNot(int current)
    {
        if (current >= (size / 2)
                && current <= size) {
            return true;
        }
        return false;
    }


    /**
     * if max node extract,
     * all node will been checked according to find biggest node
     * according to comparator
     * @param current
     */
    private void maxNodeFindingRecursion(int current)
    {
        if (leafOrNot(current))
            return;
        if ( (Heap[leftChild(current)]!=null
                && comparator.compare(Heap[leftChild(current)],Heap[current]) == 1 ||
                (Heap[rightChild(current)]!=null
                        &&comparator.compare(Heap[rightChild(current)],Heap[current])==1)
                )) {

            if ( comparator.compare(Heap[leftChild(current)],Heap[rightChild(current)]) ==1)
            {
                swap(current, leftChild(current));
                maxNodeFindingRecursion(leftChild(current));
            }
            else {
                swap(current, rightChild(current));
                maxNodeFindingRecursion(rightChild(current));
            }
        }
    }

    /**
     * Biggest node is extracted
     *
     * @return
     */
    public Pixel remove()
    {
        Pixel extractPixel = Heap[1];
        Heap[1] = Heap[size];
        size--;
        maxNodeFindingRecursion(1);
        return extractPixel;
    }

    /**
     * inserted a node where it is
     * @param element
     */
    public void insert(Pixel element)
    {
        Heap[++size] = element;
        int current = size;
        while (Heap[parent(current)] !=null
                && comparator.compare(Heap[current], Heap[parent(current)]) > 0)
        {
            swap(current, parent(current));
            current = parent(current);
        }
    }
}


/**
 * Lexical comparator
 * order by order checking for pixel
 */
class LexComparator implements Comparator<Pixel> {

    /** Lexical comparator
     * order by order checking for pixel
     * @param s1
     * @param s2
     * @return
     */
    public int compare(Pixel s1, Pixel s2) {
        if (!(s1.getRed()>s2.getRed()))
            return 0;
        if (!(s1.getGreen()>s2.getGreen()))
            return 0;
        if (!(s1.getBlue()>s2.getBlue()))
            return 0;

        return 1;

    }
}

class EucComparator implements Comparator<Pixel> {
    /**
     * Vector norm comparator
     * Finding to length for a vector and compare with other one
     * @param s1
     * @param s2
     * @return
     */
    public int compare(Pixel s1, Pixel s2) {
        double normS1 = Math.sqrt
                        (s1.getBlue()*s1.getBlue() +
                         s1.getRed()*s1.getRed()+
                         s1.getGreen()*s1.getGreen()
                        );
        double normS2 = Math.sqrt
                (s2.getBlue()*s2.getBlue() +
                        s2.getRed()*s2.getRed()+
                        s2.getGreen()*s2.getGreen()
                );

        if ((normS1>normS2)) {
            return 1;
        }
        else
            return 0;
    }
}

class BmqComparator implements Comparator<Pixel> {

    /**
     * Bitmix comparator
     * each bits mixed then comparate with other mixed result
     * @param s1
     * @param s2
     * @return
     */
    public int compare(Pixel s1, Pixel s2) {

        String s1Red = Integer.toBinaryString(s1.getRed());
        String s1Green = Integer.toBinaryString(s1.getGreen());
        String s1Blue = Integer.toBinaryString(s1.getBlue());


        String s2Red = Integer.toBinaryString(s2.getRed());
        String s2Green = Integer.toBinaryString(s2.getGreen());
        String s2Blue = Integer.toBinaryString(s2.getBlue());

        String bitMixs1 = "";
        String bitMixs2 = "";

        for (int i = 0;i<8;i++){
            try {
                bitMixs1 += s1Red.charAt(i);
            }catch (IndexOutOfBoundsException e){
                bitMixs1 += "0";
            }
            try {
                bitMixs1 += s1Green.charAt(i);

            }catch (Exception e){
                bitMixs1 +="0";
            }
            try {
                bitMixs1 += s1Blue.charAt(i);

            }catch (IndexOutOfBoundsException e){
                bitMixs1 +="0";
            }

            try {
                bitMixs2 += s2Red.charAt(i);
            }catch (IndexOutOfBoundsException e){
                bitMixs2 += "0";
            }

            try {
                bitMixs2 += s2Green.charAt(i);

            }catch (IndexOutOfBoundsException e){
                bitMixs2 +="0";
            }
            try {
                bitMixs2 += s2Blue.charAt(i);

            }catch (IndexOutOfBoundsException e){
                bitMixs2 +="0";
            }
        }

        int bitMixNumber1=Integer.parseInt(bitMixs1,2);
        int bitMixNumber2=Integer.parseInt(bitMixs2,2);

        if (bitMixNumber1 > bitMixNumber2)
            return 1;

        return 0;
    }
}


