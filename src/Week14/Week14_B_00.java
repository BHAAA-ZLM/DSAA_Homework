package Week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Week14_B_00 {

    public static final QReader in = new QReader();

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        Node[] graph = new Node[n];
        Edge[] edges = new Edge[m];
        for(int i = 0; i < n; i++){
            graph[i] = new Node();
            graph[i].index = i + 1;
        }

        long positive_sum = 0L;

        //used to get the smallest edge
        Edge smallest = new Edge();
        smallest.weight = Integer.MAX_VALUE;

        for(int i = 0; i < m; i++){
            //initializing the vertices
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int weight = in.nextInt();

            //storing the positive sums
            if(weight > 0){
                positive_sum += weight;
            }

            graph[u].child.add(graph[v]);
            graph[v].child.add(graph[u]);
            graph[v].weight.add(weight);
            graph[u].weight.add(weight);

            //initializing the edges
            edges[i] = new Edge();
            edges[i].left = graph[u];
            edges[i].right = graph[v];
            edges[i].weight = weight;

            //getting the correct smallest edge
            if(weight < smallest.weight){
                smallest = edges[i];
            }
        }
        long tree_sum = checkPrim(smallest, m, n);
        System.out.println(positive_sum - tree_sum);
    }

    public static long checkPrim(Edge smallest, int m, int n){
        long sum = 0L;//sum only records the positive edges in the tree

        //initializing heap
        Heap heap = new Heap(m);
        heap.insert(smallest);
        int edge_count = 0;
        while(edge_count < n - 1){
            Edge cur = heap.pop();
            if(cur.left.checked && cur.right.checked){
                continue;
            }
            edge_count++;
            if(cur.weight > 0 && (!cur.left.checked || !cur.right.checked)){
                sum += cur.weight;
            }
            if(!cur.left.checked){
                cur.left.checked = true;
                for(int i = 0; i < cur.left.child.size(); i++){
                    Node next = cur.left.child.get(i);
                    int weight = cur.left.weight.get(i);
                    if(!next.checked && next != cur.right){
                        Edge edge = new Edge();
                        edge.left = cur.left;
                        edge.right = next;
                        edge.weight = weight;
                        heap.insert(edge);
                    }
                }
            }
            if(!cur.right.checked){
                cur.right.checked = true;
                for(int i = 0; i < cur.right.child.size(); i++){
                    Node next = cur.right.child.get(i);
                    int weight = cur.right.weight.get(i);
                    if(!next.checked && next != cur.left){
                        Edge edge = new Edge();
                        edge.left = cur.right;
                        edge.right = next;
                        edge.weight = weight;
                        heap.insert(edge);
                    }
                }
            }
        }
        return sum;
    }

    static class Heap{
        Edge[] heap;
        int size = 0;
        public Heap(int n){
            heap = new Edge[n + 1];
        }
        void insert(Edge x){
            size++;
            heap[size] = x;
            int cur = size;
            while(cur > 1) {
                if(heap[cur].weight < heap[cur/2].weight){
                    Edge tmp = heap[cur];
                    heap[cur] = heap[cur/2];
                    heap[cur/2] = tmp;
                    cur = cur/2;
                }else{
                    break;
                }
            }
        }

        Edge pop(){
            Edge ret = heap[1];
            heap[1] = heap[size];
            heap[size] = null;
            size--;
            int cur = 1;
            while(cur <= size){
                int pos = cur;
                cur = cur *2;
                if(cur > size) break;
                if(cur == size){
                    if(heap[pos].weight < heap[cur].weight) break;
                    Edge tmp = heap[cur];
                    heap[cur] = heap[pos];
                    heap[pos] = tmp;
                }
                if(cur + 1 <= size){
                    if(heap[cur].weight <= heap[cur + 1].weight){
                        if(heap[pos].weight < heap[cur].weight) break;
                        Edge tmp = heap[cur];
                        heap[cur] = heap[pos];
                        heap[pos] = tmp;
                    }else{
                        if(heap[pos].weight < heap[cur + 1].weight) break;
                        Edge tmp = heap[cur + 1];
                        heap[cur + 1] = heap[pos];
                        heap[pos] = tmp;
                        cur = cur + 1;
                    }
                }
            }
            return ret;
        }
    }

    static class Edge{
        Node left;
        Node right;
        int weight;
    }

    static class Node{
        int index;
        boolean checked = false;
        ArrayList<Node> child = new ArrayList<>();
        ArrayList<Integer> weight = new ArrayList<>();
    }
}

