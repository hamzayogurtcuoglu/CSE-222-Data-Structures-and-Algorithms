

// 171044086
// PART-5
// My Recursively Iterator Class

public class Main  {

    public static void main(String[] args) {


       //-------------------1 - 16 Clock ---------------
        Integer[][] arrInt = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};

        IteratorClass<Integer> iter = new IteratorClass<Integer>(arrInt);
        while (iter.hasNext())
            System.out.print (iter.next() + " ");

        System.out.println("\n");
        // -------------------  1 - 100 Clock-------------
        Integer[][] arrInt2 =
                {{1, 2,   3,  4, 5, 6, 7, 8, 9,10},
                {11, 12, 13, 14,15,16,17,18,19,20},
                {21, 22, 23, 24,25,26,27,28,29,30},
                {31, 32, 33, 34,35,36,37,38,39,40},
                {41, 42, 43, 44,45,46,47,48,49,50},
                {51, 52, 53, 54,55,56,57,58,59,60},
                {61, 62, 63, 64,65,66,67,68,69,70},
                {71, 72, 73, 74,75,76,77,78,79,80},
                {81, 82, 83, 84,85,86,87,88,89,90},
                {91, 92, 93, 94,95,96,97,98,99,100}

        };
        IteratorClass<Integer> iter2 = new IteratorClass<Integer>(arrInt2);
        while (iter2.hasNext())
            System.out.print (iter2.next() + " ");

        System.out.println("\n");

    }

    // My Iterator Class

    public static class IteratorClass<Integer> {

        private int temp2 = -1;   //temp for returning next function
        private int current;      // current iterator data
        int [] iteratorArray ;    // iterator keeping int data in  iteratorArray
        int iteratorArrayIndex = 0;
        int currentIndex = 0;   //current index of iterator

        //getting a 2D array call recursiveSpiralClock function for converting to 1D array
        public IteratorClass(Integer[][] arrInt){
            iteratorArray = new int[arrInt.length*arrInt[0].length+1];
            int startRow = 0;
            int startColumn = 0;
            recursiveSpiralClock(arrInt.length,arrInt[0].length,arrInt, startRow, startColumn);
            current = iteratorArray[0];
            iteratorArray[arrInt.length*arrInt[0].length]  = -1;
        }

        // Recursively iterator function
        public void recursiveSpiralClock(int row,int column,Integer[][] arrInt,int startRow,int startColumn){

            int i;

            if (startRow < row && startColumn < column)
            {
                //right to left
                for (i = startColumn; i < column; ++i)
                {
                    iteratorArray[iteratorArrayIndex] = (int) arrInt[startRow][i];
                    iteratorArrayIndex++;
                }
                startRow++;

                //up to down
                for (i = startRow; i < row; ++i)
                {
                    iteratorArray[iteratorArrayIndex] = (int)arrInt[i][column-1];
                    iteratorArrayIndex++;
                }
                column--;

                // left to right
                if ( startRow < row)
                {
                    for (i = column-1; i >= startColumn; --i)
                    {
                        iteratorArray[iteratorArrayIndex] = (int) arrInt[row-1][i];
                        iteratorArrayIndex++;
                    }
                    row--;
                }

                //down to up
                if (startColumn < column)
                {
                    for (i = row-1; i >= startRow; --i)
                    {
                        iteratorArray[iteratorArrayIndex] = (int)arrInt[i][startColumn];
                        iteratorArrayIndex++;
                    }
                    startColumn++;
                }
                recursiveSpiralClock(row,column,arrInt, startRow, startColumn);
            }
        }

        public boolean hasNext() { // True if there is a next Object in the Array.
            if (current == -1) {
                return false;
            } else {
                return true;
            }

        }

        public int next() { // Return the next object.
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            currentIndex++;
            temp2 = current;
            current = iteratorArray[currentIndex];
            return temp2;
        }
    }
}