package Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Week10_C_00 {
    public static final QReader in = new QReader();

    public static void main(String[] args) {

        //inputting all the nodes
        int n = in.nextInt();
        Node[] cities = constructCities(n);
        for(int i = 0; i < n - 1; i++){
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            cities[u].child.add(cities[v]);
            cities[v].child.add(cities[u]);
        }

        //inputting all the constraints
        int m = in.nextInt();
        Constraint[] constraints = new Constraint[m];
        for(int i = 0; i < m; i++){
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int num = in.nextInt();
            constraints[i] = new Constraint(cities[u],cities[v],num);
        }

        System.out.println(check(cities, constraints));
    }

    public static int check(Node[] cities, Constraint[] constraints){

        //initiate the cities' fulfillment of each constraint
        for(Node city : cities){
            city.setFulfill(constraints.length);
        }

        //go through each constraint and change the cities' property
        for(int i = 0; i < constraints.length; i++){
            constraints[i].city.fulfill[i] = true;
            constraints[i].city.count++;

            Node[] queue = new Node[cities.length];
            int front = 0;
            int rear = 0;
            int fulfill = 0;
            for(Node c : constraints[i].city.child){
                if(c != constraints[i].road){
                    queue[rear++] = c;
                    c.fulfill[i] = true;
                    c.count++;
                    fulfill++;
                }
            }
            while(front != rear){
                Node head = queue[front++];
                for(int z = 0; z < head.child.size(); z++){
                    if(!head.child.get(z).fulfill[i]){
                        queue[rear++] = head.child.get(z);
                        head.child.get(z).fulfill[i] = true;
                        head.child.get(z).count++;
                        fulfill++;
                    }
                }
            }
            if(fulfill < constraints[i].num){
                return -1;
            }
        }

        //sort the cities according to the number of constraints they fulfill
        mergeSort(cities, 0, cities.length - 1);
        int[] cityCount = new int[constraints.length];
        int count = 0;
        int fulfilledConstraints = 0;

        //choose cities that fulfill most of the constraints first.
        for(int j = 0; j < cities.length; j++){
            Node c = cities[j];
            count++;
            for(int i = 0; i < c.fulfill.length; i++){
                if(!constraints[i].fulfilled && c.fulfill[i]){
                    cityCount[i]++;
                    if(cityCount[i] == constraints[i].num){
                        constraints[i].fulfilled = true;
                        fulfilledConstraints++;
                        for(int z = j; z < cities.length; z++){
                            if(cities[z].fulfill[i]){
                                cities[z].count--;
                            }
                        }
                        mergeSort(cities, j+1, cities.length - 1);
                    }
                }
            }
            if(fulfilledConstraints == constraints.length){
                break;
            }
        }
        return count;
    }

    public static void mergeSort(Node[] A, int l, int r){
        if(l < r){
            int mid = l + (r - l)/2;
            mergeSort(A,l,mid);
            mergeSort(A,mid+1,r);
            merge(A,l,mid,r);
        }
    }

    public static void merge(Node[] A, int l, int mid, int r){
        int n1 = mid - l + 1;
        int n2 = r - mid;

        Node[] L = new Node[n1];
        Node[] R = new Node[n2];

        for(int i = 0; i < n1; i++){
            L[i] = A[l + i];
        }
        for(int i = 0; i < n2; i++){
            R[i] = A[mid + 1 + i];
        }

        int i = 0, j = 0;
        int k = l;
        while(i < n1 && j < n2){
            if(L[i].count >= R[j].count){
                A[k] = L[i];
                i++;
            }else{
                A[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            A[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            A[k] = R[j];
            j++;
            k++;
        }
    }


    static class Node{
        ArrayList<Node> child = new ArrayList<>();
        int count = 0;
        boolean[] fulfill;

        void setFulfill(int m){
            fulfill = new boolean[m];
        }
    }

    public static Node[] constructCities(int n){
        Node[] tree = new Node[n];
        for(int i = 0; i < n; i++){
            tree[i] = new Node();
        }
        return tree;
    }

    static class Constraint{
        Node city;
        Node road;
        int num;
        boolean fulfilled = false;

        Constraint(Node city, Node road, int num){
            this.city = city;
            this.road = road;
            this.num = num;
        }
    }
}

