package Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Week10_C_02 {

    public static final QReader in = new QReader();

    public static void main(String[] args) {
        int n = in.nextInt();
        Node[] tree = new Node[n];
        for(int i = 0; i < n; i++){
            tree[i] = new Node();
        }
        for(int i = 0; i < n - 1; i++){
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            tree[u].roads.add(tree[v]);
            tree[v].roads.add(tree[u]);
        }
        int m = in.nextInt();
        Constraint[] cons = new Constraint[m];
        int left = 0;
        for(int i = 0; i < m; i++){
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int na = in.nextInt();
            if(na > left){
                left = na;
            }
            cons[i] = new Constraint(tree[u],tree[v],na);
        }

        checkChild(tree[0]);

        for(Constraint c : cons){
            checkConstraint(c,n);
        }

        mergeDown(tree[0]);
        System.out.println(checkValue(tree, left));
    }

    public static int checkValue(Node[] tree, int n){
        int left = n, right = tree.length;
        int mid = 0;
        while(left <= right){
            mid = left + (right - left)/2;
            if(check(mid, tree)){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        if(left <= tree.length){
            return left;
        }else{
            return -1;
        }
    }

    public static boolean check(int k, Node[] tree){
        for(Node n : tree){
            n.upBound = n.childnum;
        }
        if(mergeUp(k, tree[0])) {
//            if ((k > tree[0].upBound || k < tree[0].down) && tree[0].upBound < tree[0].down) {
//                return false;
//            }
//            return true;
            if(k <= tree[0].upBound && k >= tree[0].down && tree[0].down <= tree[0].upBound){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public static boolean mergeUp(int k, Node city){
        if(k - city.up < city.childnum){
            city.upBound = k - city.up;
        }
        if(city.upBound < city.down){
            return false;
        }
        if(city.childnum != 0){
            int childUp = 0;
            for(Node n : city.child){
                if(!mergeUp(k, n)) return false;
                childUp += n.upBound;
            }
            if(city.upBound > childUp + 1){
                city.upBound = childUp + 1;
            }
        }
        if(city.upBound >= city.down){
            return true;
        }else{
            return false;
        }
    }

    public static void mergeDown(Node city){
        if(city.childnum != 0){
            int childDown = 0;
            for(Node n : city.child){
                mergeDown(n);
                childDown += n.down;
            }
            if(childDown > city.down){
                city.down = childDown;
            }
        }
    }

    public static void checkConstraint(Constraint c, int n){
        if(c.city.child.contains(c.road)){
            if(c.na > n - c.road.childnum){
                System.out.println(-1);
                System.exit(0);
            }
            if(c.road.up < c.na){
                c.road.up = c.na;
            }
        }else{
            if(c.na > c.city.childnum){
                System.out.println(-1);
                System.exit(0);
            }
            if(c.city.down < c.na){
                c.city.down = c.na;
            }
        }
    }

    public static void checkChild(Node root){
        root.isParent = true;
        if(root.roads.size() == 1 && root.roads.get(0).isParent){
            root.childnum = 1;
            root.upBound = 1;
        }else{
            int count = 0;
            for(Node n : root.roads){
                if(!n.isParent){
                    root.child.add(n);
                    checkChild(n);
                    count = count + n.childnum;
                }
            }
            root.childnum = count + 1;
            root.upBound = root.childnum;
        }
    }

    static class Node{
        boolean isParent = false;
        int childnum;
        ArrayList<Node> child = new ArrayList<>();
        ArrayList<Node> roads = new ArrayList<>();

        int down = 0;
        int up = 0;
        int upBound;
    }

    static class Constraint{
        Node city;
        Node road;
        int na;
        Constraint(Node u, Node v, int na){
            this.city = u;
            this.road = v;
            this.na = na;
        }
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
