import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    /**
     * Loading picture to 2D Pixel array, it will be represented as a
     * 2D array (with W columns and H rows) of 3D 8bit unsigned integer valued vectors.
     * First if no such a argument
     * User will write a pathname in commandline
     *
     * @param args first index is got for picture path
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {


        BufferedImage bi;
        try {
             bi = ImageIO.read(new File(args[0]));
        }catch (Exception e){
            try {

                Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                System.out.println("\nNo such a command line argument \n " +
                                "Enter picture path(Example: example.png) : "
                                   );

                String pathnmame = myObj.nextLine();  // Read user input
                bi = ImageIO.read(new File(pathnmame));
            }catch (Exception e1){
                System.out.println("No such a picture");
                return;
            }
        }
        Pixel [][] PixelArray = new Pixel[bi.getWidth()][bi.getHeight()];

        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                int pixel = bi.getRGB(x, y);

                PixelArray[x][y] = new Pixel();
                PixelArray[x][y].setRed( (pixel >> 16) & 0xff);
                PixelArray[x][y].setGreen((pixel >> 8) & 0xff);
                PixelArray[x][y].setBlue((pixel) & 0xff);
            }
        }

            threadClass T1 = new threadClass( "Thread 1");

            T1.pixelArray = PixelArray;

            T1.start();

            threadClass T2 = new threadClass( "Thread2-PQLEX");
            T2.start();

            threadClass T3 = new threadClass( "Thread3-PQEUC");
            T3.start();

            threadClass T4 = new threadClass( "Thread4-PQBMX");
            T4.start();

    }
}
