package Week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Week13_C_00 {
    public static final QReader input = new QReader();

    public static void main(String[] args) {
        int n = input.nextInt();
        int m = input.nextInt();

        Node[] graph = new Node[n];
        for(int i = 0; i < n; i++){
            graph[i] = new Node();
        }

        for(int i = 0; i < m; i++){
            int u = input.nextInt() - 1;
            int v = input.nextInt() - 1;
            graph[u].child.add(graph[v]);
            graph[v].child.add(graph[u]);
        }

        Node[] q = new Node[n];
        for(int i = 0; i < n; i++){
            if(!graph[i].checked){
                int point = 0;
                int side = 0;

                int front = 0;
                int rear = front;
                q[rear++] = graph[i];
                graph[i].checked = true;

                while(front != rear){
                    Node head = q[front++];
                    for(int j = 0; j < head.child.size(); j++){
                        if(!head.child.get(j).checked){
                            q[rear++] = head.child.get(j);
                            head.child.get(j).checked = true;
                        }
                    }
                    side += head.child.size();
                    point++;
                }
                if(point <= side/2){
                    System.out.println("Bad");
                    System.exit(0);
                }
            }
        }
        System.out.println("Good");
    }

    static class Node{
        ArrayList<Node> child = new ArrayList<>();
        boolean checked = false;
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

