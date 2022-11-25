package Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Week10_C_01 {
    public static final QReader in = new QReader();

    public static void main(String[] args) {

        //initialization of the tree
        int n = in.nextInt();
        Node[] tree = new Node[n];
        boolean[] root = new boolean[n];
        int rootNum = 0;
        for(int i = 0; i < n; i++){
            tree[i] = new Node();
            root[i] = true;
        }
        for(int i = 0; i < n - 1; i++){
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            tree[u].child.add(tree[v]);
            tree[u].isChild.add(false);
            tree[v].child.add(tree[u]);
            tree[v].isChild.add(false);
        }

        int m = in.nextInt();
        Constraint[] cons = new Constraint[m];
        for(int i = 0; i < m; i++){
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int na = in.nextInt();
            root[u] = false;
            root[v] = false;
            cons[i] = new Constraint(u,v,na);
        }

        for(int i = 0; i < n; i++){
            if(root[i]){
                rootNum = i;
                break;
            }
        }

        //check the descendent number of each node
        //i.e. the number of nodes in each subtree
        checkChild(tree[rootNum]);

        //go through every constraint
        //set the upper and lower limit for each node
        for(Constraint c : cons){
            checkConstraint(c, tree);
        }

        mergeConstraints(tree, rootNum);

        int left = 0;
        int right = tree.length;
        int mid = 0;
        while(left <= right){
            mid = left + (right - left) / 2;
            if(checkValue(mid, tree)){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        if(left <= tree.length){
            System.out.println(mid + 1);
        }else{
            System.out.println("-1");
        }
    }

    public static boolean checkValue(int val, Node[] tree){
        for(Node n : tree){
            if(n.down > val - n.up) return false;
        }
        return true;
    }

    public static void mergeConstraints(Node[] tree, int rootNum){
        mergeDown(tree[rootNum]);
        mergeUp(tree[rootNum]);
    }

    public static void mergeUp(Node city) {
        if (city.childnum != 0) {
            int childUp = 0;
            for(Node n : city.child){
                if(city.isChild.get(city.child.indexOf(n))) {
                    mergeUp(n);
                    childUp += n.up;
                }
            }
            if(childUp > city.up){
                city.up = childUp;
            }
        }
    }

    public static void mergeDown(Node city){
        if(city.childnum != 0) {
            int childDown = 0;
            for (Node n : city.child) {
                if(city.isChild.get(city.child.indexOf(n))) {
                    mergeDown(n);
                    childDown += n.down;
                }
            }
            if (childDown > city.down) {
                city.down = childDown;
            }
        }
    }

    public static void checkConstraint(Constraint c, Node[] tree){
        Node city = tree[c.city];
        Node road = tree[c.road];
        //if the road leads to a subtree
        if(city.isChild.get(city.child.indexOf(road))){
            if(c.na - 1 > city.childnum){
                System.out.println("-1");
                System.exit(0);
            }else{
                if (c.na - 1 > city.down){
                    city.down = c.na - 1;
                }
            }
        }else{
        //if the road does not lead to a subtree
            if(c.na > tree.length - city.childnum - 1) {
                System.out.println("-1");
                System.exit(0);
            }else {
                if (c.na > city.up) {
                    city.up = c.na;
                }
            }
        }
    }

    public static void checkChild(Node root){
        root.isParent = true;
        if(root.child.size() == 1 && root.child.get(0).isParent){
            root.childnum = 0;
        }else {
            int count = 0;
            for (int i = 0; i < root.child.size(); i++) {
                Node n = root.child.get(i);
                if (!n.isParent) {
                    root.isChild.set(i, true);
                    checkChild(n);
                    count = count + n.childnum + 1;
                }
            }
            root.childnum = count;
        }
    }

    static class Node{
        ArrayList<Node> child = new ArrayList<>();
        ArrayList<Boolean> isChild = new ArrayList<>();
        int childnum;
        boolean isParent = false;
        int down = 0;
        int up = 0;
    }

    static class Constraint{
        int city;
        int road;
        int na;
        Constraint(int city, int road, int na){
            this.city = city;
            this.road = road;
            this.na = na;
        }
    }
}

