package Week13;

import Week10.Week10_A_00;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Week13_B_00 {
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
            graph[v].child.add(graph[u]);
        }

        Node[] q = new Node[n];
        for(int i = graph.length - 1; i >= 0; i--){
            if(!graph[i].checked){
                int front = 0;
                int rear = front;
                q[rear++] = graph[i];
                graph[i].checked = true;
                graph[i].max = i;

                while(front != rear){
                    Node head = q[front++];
                    for(int j = 0; j < head.child.size(); j++){
                        if(!head.child.get(j).checked){
                            q[rear++] = head.child.get(j);
                            head.child.get(j).checked = true;
                            head.child.get(j).max = i;
                        }
                    }
                }
            }
        }

        for(Node a : graph){
            System.out.print((a.max + 1) + " ");
        }
    }

    static class Node{
        ArrayList<Node> child = new ArrayList<>();
        int max;
        boolean checked = false;
    }
}

