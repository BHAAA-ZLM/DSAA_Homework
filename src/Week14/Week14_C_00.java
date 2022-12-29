package Week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Week14_C_00 {
    public static final QReader in = new QReader();
    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int c = in.nextInt();
        Node[] graph = new Node[n];
        ArrayList<Node>[] colours = new ArrayList[k];
        for(int i = 0; i < k; i++){
            colours[i] = new ArrayList<Node>();
        }

        for(int i = 0; i < n; i++){
            graph[i] = new Node(k);
            int x = in.nextInt();
            graph[i].index = i + 1;
            graph[i].colour = x;
            colours[x - 1].add(graph[i]);
        }

        for(int i = 0; i < m; i++){
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            graph[u].child.add(graph[v]);
            graph[v].child.add(graph[u]);
        }

        checkColours(colours, n);
        printColours(graph, c);
    }

    public static void printColours(Node[] graph, int c){
        for(int i = 0; i < graph.length; i++){
            Node cur = graph[i];
            int count = 0;
            Arrays.sort(cur.dist_colour);
            for(int j = 0; j < c; j++){
                count += cur.dist_colour[j];
            }
            System.out.print(count + " ");
        }
    }

    public static void checkColours(ArrayList<Node>[] colours, int n){
        for(int i = 0; i < colours.length; i++){
            Node[] queue = new Node[n];
            int front = 0;
            int rear = front;
            ArrayList<Node> colour = colours[i];
            for(Node node : colour){
                queue[rear++] = node;
                node.checked[i] = true;
                node.dist_colour[i] = 0;
            }
            while(front != rear){
                Node head = queue[front++];
                for(Node child : head.child){
                    if(!child.checked[i]){
                        child.checked[i] = true;
                        child.dist_colour[i] = head.dist_colour[i] + 1;
                        queue[rear++] = child;
                    }
                }
            }
        }
    }

    static class Node{
        Node(int k){
            this.dist_colour = new int[k];
            this.checked = new boolean[k];
        }
        boolean[] checked;
        int[] dist_colour;
        int colour;
        int index;
        ArrayList<Node> child = new ArrayList<>();
    }
}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}
