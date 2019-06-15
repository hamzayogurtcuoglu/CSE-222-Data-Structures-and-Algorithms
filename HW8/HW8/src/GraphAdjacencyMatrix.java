import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphAdjacencyMatrix {
    int vertex;
    int matrix[][];
    boolean popularPathControl[];
    boolean visited[][];
    public GraphAdjacencyMatrix(int vertex) {
        this.vertex = vertex;
        matrix = new int[vertex][vertex];
        popularPathControl = new boolean[vertex];
        for (int i=0;vertex>i;i++)
            popularPathControl[i] = false;
        visited = new boolean[vertex][vertex];

        for (int i=0;vertex>i;i++)
            for (int j=0;vertex>j;j++)
            visited[i][j] = false;
    }
    public void addEdge(int source, int destination) {
        matrix[source][destination]=1;
    }
    public int numberOfPeople() {
        int popularPeople = 0;
        for (int i = 0; i < vertex; i++) {
            boolean allVisited = true;
            for (int j = 0; j <vertex ; j++) {
                if (matrix[j][i] == 1){
                    visited[j][i] = true;
                    popularPathControl[j]=true;
                    recursiveFunction(j);
                }
            }

            for (int l=0;vertex>l;l++) {
                if(!popularPathControl[l] && l!=i){
                    allVisited = false;
                }
            }
            if (allVisited) {
                popularPeople++;
            }
            refresh();
        }
        return popularPeople;
    }
    void refresh(){
        for (int k=0;vertex>k;k++)
            popularPathControl[k] = false;

        for (int o=0;vertex>o;o++)
            for (int t=0;vertex>t;t++)
                visited[o][t] = false;
    }
    public void  recursiveFunction(int index){
        for (int j = 0; j <vertex ; j++) {

            if (matrix[j][index] == 1){
                popularPathControl[j]=true;
                if (!visited[j][index]) {
                    visited[j][index] = true;
                    recursiveFunction(j);
                }
            }
        }
    }
    public static void main(String[] args) {

        GraphAdjacencyMatrix graph =null;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/input.txt"));
            String line = reader.readLine();
            boolean firstLine = true;
            while (line != null) {
                String[] parts = line.split(" ");
                int src = Integer.parseInt(parts[0]);
                int dest = Integer.parseInt(parts[1]);
                if (!firstLine){
                    graph.addEdge(src-1,dest-1);
                }
                if (firstLine){
                    graph = new GraphAdjacencyMatrix(src);
                    firstLine = !firstLine;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println(graph.numberOfPeople());
    }
}