
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static int row = 1;
    static int column = 0;
    public static Stack<cordinateOne> myStack;
    static int rowCordinate[] = new int[] {-1,0,0,1};
    static int columnCordinate[] = new int[] {0,-1,1,0};

    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("src/test_file_part1");
        int oneNumber=0;
        int i;

        while ((i=fr.read()) != -1) {

            if ((char) i == '\n') {
                column = 0;
                row++;
            }
            if ((char) i != '\n' && ((char) i) != ' ')
                column++;

        }
        cordinateOne [][] matrix = new cordinateOne[row][column];

        int column2 = 0;
        int row2 = 0;
        FileReader fr2 = new FileReader("src/test_file_part1");
        while ((i=fr2.read()) != -1) {
            if ((char) i == '\n') {
                column2 = 0;
                row2++;
            }
            if ((char) i != '\n' && ((char) i) != ' ') {

               // System.out.println(i);
                matrix[row2][column2] = new cordinateOne(row2, column2,(char)i);
                column2++;
            }
        }

        System.out.println("\nNumber of White Components : "+ whiteComponent(matrix)+"\n");


    }
    static int whiteComponent(cordinateOne Image[][])
    {

        boolean ControlledOnePoint[][] = new boolean[row][column];
        int whiteAreaNumber = 0;
        for (int i = 0; i < row; ++i)
            for (int j = 0; j < column; ++j)
                if (Image[i][j].getValue()=='1' && !ControlledOnePoint[i][j])
                {
                    myStack = new Stack<cordinateOne>();
                    whitePoint(Image, ControlledOnePoint, i, j);
                    ++whiteAreaNumber;
                }

        return whiteAreaNumber;
    }

    static boolean ControlledAndBound(cordinateOne Image[][],
                                      boolean ControlledOnePoint[][], int tempRow, int tempColumn)
    {
        boolean suitOrNot =  (tempRow >= 0) && (tempRow < row) &&
                (tempColumn >= 0) && (tempColumn < column) && !ControlledOnePoint[tempRow][tempColumn] &&
                (Image[tempRow][tempColumn].getValue()=='1');

        return  suitOrNot;
    }

    static void whitePoint(cordinateOne Image[][], boolean ControlledOnePoint[][], int currentRow, int currentCol)
    {
        for (int k = 0; k < 4; ++k) {
            if (ControlledAndBound(Image, ControlledOnePoint, currentRow + rowCordinate[k], currentCol + columnCordinate[k])
                    &&(!ControlledOnePoint[currentRow + rowCordinate[k]][currentCol + columnCordinate[k]]) ){
                myStack.push(Image[currentRow + rowCordinate[k]][currentCol + columnCordinate[k]]);
            }

            if (k+1 == 4){
                ControlledOnePoint[currentRow][currentCol] = true;
                if (!myStack.empty()){
                    cordinateOne temp = myStack.pop();
                    currentRow = temp.getX();
                    currentCol = temp.getY();
                    k = -1;
                }
            }
        }
    }
}