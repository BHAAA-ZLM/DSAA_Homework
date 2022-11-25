package Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Week10_D_01 {
    public static final QReader in = new QReader();
    public static long esum = 0;

    public static void main(String[] args) {
        int n = in.nextInt();

        Node[] tree = new Node[n];
        for(int i = 0; i < n; i++){
            tree[i] = new Node();
        }

        for(int i = 0; i < n-1; i++){
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            tree[u].child.add(tree[v]);
            tree[v].child.add(tree[u]);
        }

        Node maxNode = tree[0];
        for(int i = 0; i < n; i++){
            tree[i].p = in.nextInt();
            if(tree[i].p > maxNode.p) maxNode = tree[i];
        }

        process(maxNode);
        System.out.println(esum);
    }

    public static void process(Node maxNode){
        maxNode.isParent = true;

        //getting the maximum pi in the sub-tree of every child
        Node max = maxNode.child.get(0);
        for(int i = 0; i < maxNode.child.size(); i++){
            if(maxNode.child.get(i).child.size() == 1){
                maxNode.child.get(i).max = maxNode.child.get(i).p;
                esum += maxNode.child.get(i).p;
            }else {
                checkE(maxNode.child.get(i));
            }
            if(maxNode.child.get(i).max > max.max) max = maxNode.child.get(i);
        }
        if(maxNode.child.size() == 1){
            esum = esum - max.max + 2L * maxNode.p;
        }else {
            Node second = new Node();
            for (int i = 0; i < maxNode.child.size(); i++) {
                if(second.max < maxNode.child.get(i).max && maxNode.child.get(i) != max){
                    second = maxNode.child.get(i);
                }
            }
            esum = esum - max.max - second.max + 2L * maxNode.p;
        }
    }

    public static void checkE(Node parent){
        parent.isParent = true;
        for(int i = 0; i < parent.child.size(); i++){
            Node children = parent.child.get(i);
            if(!children.isParent) {
                if (children.child.size() == 1 && children.child.get(0).isParent){
                    children.max = children.p;
                    esum += children.p;
                }else{
                    checkE(children);
                }
                if(children.max > parent.max){
                    parent.max = children.max;
                }
            }
        }
        if(parent.max < parent.p){
            esum -= parent.max;
            esum += parent.p;
            parent.max = parent.p;
        }
    }

    static class Node{
        ArrayList<Node> child = new ArrayList<>();
        boolean isParent = false;
        int p;
        int max = 0;
    }
}


