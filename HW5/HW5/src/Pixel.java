

public class Pixel {

    private int red  = 0;
    private int green = 0;
    private int blue = 0;



    public int getRed() {
        return red;
    }

    @Override
    public String toString() {
        return "[" +red +
                "," + green +
                "," + blue +
                ']';
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }


}










