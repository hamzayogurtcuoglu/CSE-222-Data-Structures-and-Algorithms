public class variableClass {
    private String name=null;
    private float value= (float) 0.0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "variableClass{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
