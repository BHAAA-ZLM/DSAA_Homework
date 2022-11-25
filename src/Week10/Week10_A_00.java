package Week10;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Week10_A_00 {

    public static final QReader in = new QReader();

    public static void main(String[] args) {
        int n = in.nextInt();
        int num = in.nextInt();
        Node[] tree = new Node[n];
        for(int i = 0; i < n; i++){
            tree[i] = new Node();
        }
        for(int i = 0; i < n-1; i++){
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int w = in.nextInt();

            tree[u].children.add(tree[v]);
            tree[u].length.add(w);
            tree[v].children.add(tree[u]);
            tree[v].length.add(w);
        }

        int count = 0;

        //use queue to pop the parent and add the children
        //this is how we make level traversal possible in tree
        Node[] queue = new Node[n];
        int front = 0;
        int rear = front;
        queue[rear++] = tree[0];
        tree[0].isVisited = true;
        tree[0].path = 0;

        while(front != rear){
            Node head = queue[front++];
            if(head.path == num && head.children.size()==1) count++;
            for(int i = 0; i < head.children.size(); i++){
                if(!head.children.get(i).isVisited) {
                    queue[rear++] = head.children.get(i);
                    head.children.get(i).path = head.path + head.length.get(i);
                    head.children.get(i).isVisited = true;
                }
            }
        }

        System.out.println(count);
    }

    static void dfs(Node a){
        a.isVisited = true;
//        if(head.path == num && head.children.size()==1) count++;
        for(int i = 0; i < a.children.size(); i++){
            if(!a.children.get(i).isVisited){
                a.children.get(i).path = a.path + a.length.get(i);
                dfs(a.children.get(i));
            }
        }
    }

    static class Node{
        int path = 0;
        ArrayList<Node> children = new ArrayList<>();
        ArrayList<Integer> length = new ArrayList<>();
        boolean isVisited = false;
    }
}



//dfs(node a){
//    if(a == null) return;
//    dfs(a.left);
//    dfs(a.right);
//}