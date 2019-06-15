public class cordinateOne {
    private int x = 0;
    private int y = 0;
    private char value;

    public cordinateOne(int column, int row,char value) {
        this.x = column;
        this.y = row ;
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "cordinateOne{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
