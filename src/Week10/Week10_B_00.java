package Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Week10_B_00 {

    public static final QReader in = new QReader();

    public static void main(String[] args) {
        int n = in.nextInt();
        Node[] cities = constructCities(n);

        for(int i = 0; i < n - 1; i++){
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            cities[u].child.add(cities[v]);
            cities[v].child.add(cities[u]);
        }
        int m = in.nextInt();
        for(int i = 0; i < m; i++){
            int giant = in.nextInt() - 1;
            cities[giant].hasGiant = true;
        }
        System.out.println(checkGiant(cities));
    }

    public static int checkGiant(Node[] cities){
        int max = 0;
        cities[0].isFather = true;
        for(Node n : cities[0].child){

            Node[] queue = new Node[cities.length];
            ArrayList<Integer> paths = new ArrayList<>();
            int front = 0;
            int rear = 0;

            n.isFather = true;
            n.path = 1;
            queue[rear++] = n;

            while(front!=rear){
                Node head = queue[front++];
                for(int i = 0; i < head.child.size(); i++){
                    if(!head.child.get(i).isFather){
                        Node city = head.child.get(i);
                        queue[rear++] = city;
                        city.path = head.path + 1;
                        city.isFather = true;
                        if(city.hasGiant){
                            paths.add(city.path);
                        }
                    }
                }

            }
            for(int i = 1; i < paths.size() ; i++){
                if(paths.get(i) <= paths.get(i - 1)){
                    paths.set(i,paths.get(i - 1) + 1);
                }
            }
            if(paths.size() > 0 && max < paths.get(paths.size() - 1)){
                max = paths.get(paths.size() - 1);
            }
        }
        return max;
    }

    public static Node[] constructCities(int n){
        Node[] tree = new Node[n];
        for(int i = 0; i < n; i++){
            tree[i] = new Node();
        }
        return tree;
    }

    static class Node{
        ArrayList<Node> child = new ArrayList<>();
        boolean hasGiant = false;
        boolean isFather = false;
        int path;
    }
}

