package Week12;

import java.util.Scanner;

public class Week12_C_00 {
    public static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int n = in.nextInt();
        Node[] pile = new Node[n];
        Heap heap = new Heap(n);

        for(int i = 0; i < n; i++){
            Node node = new Node(in.nextInt(), i);
            pile[i] = node;
            heap.insert(node);
        }
        for(int i = 0; i < n; i++){
            if(i > 0){
                pile[i].pre = pile[i - 1];
            }
            if(i < n - 1){
                pile[i].next = pile[i + 1];
            }
        }

        int smallestPile = 0;
        while(heap.size != 0){
            while(!heap.heap[1].isReal){
                heap.pop();
            }
            if(heap.size == 0){
                break;
            }
            Node smallest = heap.pop();
            smallestPile = smallest.val;
            Node pre = smallest.pre;
            Node next = smallest.next;
            if(pre == null && next == null){
                break;
            }
            if(next != null && pre != null) {
                int left = (smallest.val ^ pre.val) + 1;
                int right = (smallest.val ^ next.val) + 1;
                if(left < right){
                    next.isReal = false;
                    Node newNode = new Node(right, smallest.index);
                    newNode.pre = pre;
                    pre.next = newNode;
                    newNode.next = next.next;
                    if(next.next != null) next.next.pre = newNode;
                    heap.insert(newNode);
                }else{
                    pre.isReal = false;
                    Node newNode = new Node(left, pre.index);
                    newNode.pre = pre.pre;
                    if(pre.pre != null) pre.pre.next = newNode;
                    newNode.next = next;
                    next.pre = newNode;
                    heap.insert(newNode);
                }
            } else if(next == null){
                int left = (smallest.val ^ pre.val) + 1;
                pre.isReal = false;
                Node newNode = new Node(left, pre.index);
                newNode.pre = pre.pre;
                if(pre.pre != null) pre.pre.next = newNode;
                heap.insert(newNode);
            }else{
                int right = (smallest.val ^ next.val) + 1;
                next.isReal = false;
                Node newNode = new Node(right, smallest.index);
                newNode.next = next.next;
                if(next.next != null) next.next.pre = newNode;
                heap.insert(newNode);
            }

        }

        System.out.println(smallestPile);

    }

    static class Node{
        int val;
        int index;
        int pos;
        Node pre;
        Node next;
        boolean isReal = true;

        Node(int x, int i) {
            val = x;
            index = i;
        }
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
                if (heap[cur].val < heap[cur / 2].val) {
                    Node tmp = heap[cur];
                    heap[cur] = heap[cur / 2];
                    heap[cur / 2] = tmp;
                    cur = cur/2;
                }else {
                    checkIndex(cur);
                    break;
                }
            }
        }

        void checkIndex(int cur){
            while(cur != 1 && heap[cur].val == heap[cur / 2].val){
                if(heap[cur].index < heap[cur / 2].index){
                    Node tmp = heap[cur];
                    heap[cur] = heap[cur / 2];
                    heap[cur / 2] = tmp;
                    cur = cur/2;
                }else{
                    break;
                }
            }
            while(cur * 2 < size && heap[cur].val == heap[cur * 2].val){
                if(heap[cur].index > heap[cur * 2].index){
                    Node tmp = heap[cur];
                    heap[cur] = heap[cur * 2];
                    heap[cur * 2] = tmp;
                    cur = cur*2;
                }else{
                    break;
                }
            }
            while(cur * 2 + 1 < size && heap[cur].val == heap[cur * 2 + 1].val){
                if(heap[cur].index > heap[cur * 2 + 1].index){
                    Node tmp = heap[cur];
                    heap[cur] = heap[cur * 2 + 1];
                    heap[cur * 2 + 1] = tmp;
                    cur = cur*2 + 1;
                }else{
                    break;
                }
            }
        }

        Node pop(){
            Node x = heap[1];
            heap[1] = heap[size];
            heap[size] = null;
            size--;
            int cur = 1;
            while(cur <= size){
                int pos = cur;
                cur = cur * 2;
                if(cur > size) break;
                if(cur == size){
                    // with only one child
                    if(heap[pos].val < heap[cur].val) break;
                    Node tmp = heap[cur];
                    heap[cur] = heap[pos];
                    heap[pos] = tmp;
                }
                if(cur + 1 <= size){
                    if(heap[cur].val <= heap[cur + 1].val){
                        if(heap[pos].val < heap[cur].val) break;
                        Node tmp = heap[cur];
                        heap[cur] = heap[pos];
                        heap[pos] = tmp;
                    }else{
                        if(heap[pos].val < heap[cur + 1].val) break;
                        Node tmp = heap[cur + 1];
                        heap[cur + 1] = heap[pos];
                        heap[pos] = tmp;
                        cur = cur + 1;
                    }
                }
            }
            return x;
        }

       /*
       swap final leaf with root
       pointer point to root
       while(have child){
            if(have two child){
                if(left child bigger){
                }else(right child bigger){
                }
            }else{
            }
       }
        */
    }

}
