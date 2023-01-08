package Week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Week14_A_01 {

    public static final QReader in = new QReader();

    //The Dijkstra's algorithm
    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();

        Node[] graph = new Node[n];
        for(int i = 0; i < n; i++){
            graph[i] = new Node();
            graph[i].index = i + 1;
        }
        for(int i = 0; i < m; i++){
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int weight = in.nextInt();
            graph[u].child.add(graph[v]);
            graph[u].weight.add(weight);
        }

        System.out.println(check(graph,3, 1));
    }

    public static long check(Node[] graph,int start, int end){
        Node head = graph[start - 1];
        head.length = 0L;
        Heap heap = new Heap(graph.length);
        heap.insert(head);
        while(heap.size != 0){
            Node cur = heap.pop();
            if(!cur.checked) {
                for (int i = 0; i < cur.child.size(); i++) {
                    Node next = cur.child.get(i);
                    int weight = cur.weight.get(i);
                    if (next.length > weight + cur.length) {
                        next.length = weight + cur.length;
                        heap.insert(next);
                    }
                }
            }
            cur.checked = true;
        }
        if(graph[end - 1].length == Long.MAX_VALUE){
            return -1;
        }else{
            return graph[end - 1].length;
        }
    }

    static class Node{
        ArrayList<Node> child = new ArrayList<>();
        ArrayList<Integer> weight = new ArrayList<>();
        long length = Long.MAX_VALUE;
        int index;
        boolean checked = false;
    }

    static class Heap{
        Node[] heap;
        int size = 0;
        public Heap(int n){
            heap = new Node[n+1];
        }

        void insert(Node x){
            size++;
            heap[size] = x;

            int cur = size;

            while(cur > 1) {
                if(heap[cur].length < heap[cur/2].length){
                    Node tmp = heap[cur];
                    heap[cur] = heap[cur/2];
                    heap[cur/2] = tmp;
                    cur = cur/2;
                }else{
                    break;
                }
            }
        }

        void floatUp(Node x){

        }

        Node pop(){
            Node ret = heap[1];
            heap[1] = heap[size];
            heap[size] = null;
            size--;
            int cur = 1;
            while(cur <= size){
                int pos = cur;
                cur = cur * 2;
                if(cur > size) break;
                if(cur == size){
                    // only one child
                    if(heap[cur].length < heap[pos].length) break;
                    Node tmp = heap[cur];
                    heap[cur] = heap[pos];
                    heap[pos] = tmp;
                }
                if(cur + 1 <= size){
                    if(heap[cur].length <= heap[cur + 1].length){
                        if(heap[pos].length < heap[cur].length) break;
                        Node tmp = heap[cur];
                        heap[cur] = heap[pos];
                        heap[pos] = tmp;
                    }else{
                        if(heap[pos].length < heap[cur+1].length) break;
                        Node tmp = heap[cur+1];
                        heap[cur+1] = heap[pos];
                        heap[pos] = tmp;
                        cur = cur + 1;
                    }
                }
            }
            return ret;
        }
    }
}

