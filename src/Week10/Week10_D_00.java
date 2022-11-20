package Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Week10_D_00 {
    public static final QReader in = new QReader();
    public static int esum = 0;

    public static void main(String[] args) {
        int n = in.nextInt();

        //initializing the tree
        Node[] tree = new Node[n];
        for(int i = 0; i < n; i++){
            tree[i] = new Node();
        }

        //adding the relationships between the nodes
        for(int i = 0; i < n - 1; i++){
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            tree[u].child.add(tree[v]);
            tree[v].child.add(tree[u]);
        }

        //assigning the p-values to the nodes
        //and finding the node with largest p-value
        Node maxNode = tree[0];
        for(int i = 0; i < n; i++){
            tree[i].p = in.nextInt();
            if(tree[i].p > maxNode.p){
                maxNode = tree[i];
            }
        }

        //process all the child of the maxNode by setting the appropriate e-value
        maxNode.isParent = true;
        for(Node c : maxNode.child){
            setE(c);
        }

        if(maxNode.child.size() == 1){
            maxNode.e = maxNode.p;
            esum += maxNode.e;
            Node maxChild = maxNode.child.get(0);
            for(Node c : maxNode.child){
                if(c.maxChildE.e > maxChild.e){
                    maxChild = c.maxChildE;
                }
            }
            esum -= maxChild.e;
            maxChild.e = maxNode.p;
            esum += maxChild.e;
        }else{
            Node maxChild = maxNode.child.get(0);
            Node secondChild = new Node();
            for(Node c : maxNode.child){
                if(c.maxChildE.e > maxChild.e){
                    maxChild = c.maxChildE;
                }
            }
            esum -= maxChild.e;
//            maxChild.e = maxNode.p;
            esum += maxNode.p;
            for(Node c : maxNode.child) {
                if (c.maxChildE.e > secondChild.e && c.maxChildE != maxChild) {
                    secondChild = c.maxChildE;
                }
            }
            esum -= secondChild.e;
//            secondChild.e = maxNode.p;
            esum += maxNode.p;
        }
        System.out.println(esum);
    }

    public static void setE(Node parent){
        parent.isParent = true;

        if(parent.child.size() == 1 && parent.child.get(0).isParent){
            parent.e = parent.p;
            esum += parent.e;
            parent.maxChildE = parent;
        }else{
            for(Node n : parent.child) {
                setE(n);
                if(parent.maxChildE == null || parent.maxChildE.e < n.maxChildE.e){
                    parent.maxChildE = n.maxChildE;
                }
            }
            if(parent.maxChildE.e < parent.p){
                esum -= parent.maxChildE.e;
                parent.maxChildE.e = parent.p;
                esum += parent.maxChildE.e;
            }
            parent.e = 0;
        }
    }

    static class Node{
        ArrayList<Node> child = new ArrayList<>();
        int p;
        int e;
        boolean isParent = false;
        Node maxChildE;
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
