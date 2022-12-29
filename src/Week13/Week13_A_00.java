package Week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week13_A_00 {
    public static final QReader input = new QReader();

    public static void main(String[] args) {
        int n = input.nextInt();
        int m = input.nextInt();
        int q = input.nextInt();

        boolean[][] graph = new boolean[n][n];
        for(int i = 0; i < m; i++){
            int u = input.nextInt() - 1;
            int v = input.nextInt() - 1;
            graph[u][v] = true;
            graph[v][u] = true;
        }
        for(int i = 0; i < q; i++){
            int u = input.nextInt() - 1;
            int v = input.nextInt() - 1;
            if(graph[u][v]) System.out.println("Yes");
            else System.out.println("No");
        }
    }
}
