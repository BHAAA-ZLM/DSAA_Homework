package Week5;

import java.io.*;
import java.util.StringTokenizer;

public class Week5_E_00 {
    public static final QReader in = new QReader();
    public static final QWriter out = new QWriter();
    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        Node[] A = new Node[n];
        LinkedList list = new LinkedList();
        for(int i = 0; i < n; i++){
            Node tmp = new Node(in.nextInt());
            A[i] = tmp;
            LinkedList.insert(list,tmp);
        }
        for(int i = 0; i < m; i++){
            String operation = in.next();
            if(operation.equals("I")){
                int a = in.nextInt();
                Node b = new Node(in.nextInt());
                insertList(A, a - 1, b);
                A = insertArray(A, a - 1, b);
            }
            if(operation.equals("M")){
                int a = in.nextInt();
                Node b = new Node(in.nextInt());
                exchange(A, a-1, b);
            }
            if(operation.equals("Q")){
                int l = in.nextInt();
                int r = in.nextInt();
                int k = in.nextInt();
                Node[] B = new Node[r - l + 1];
                for(int j = 0; j < B.length; j++){
                    B[j] = A[l - 1];
                    l++;
                }
                out.println(small(B,k));
            }
        }
        out.close();
    }

    public static int small(Node[] B, int k){
        mergeSort(B,0,B.length-1);
        return B[k-1].val;
    }

    public static void exchange(Node[] A, int a, Node b){
        b.last = A[a].last;
        b.next = A[a].next;
        A[a].last.next = b;
        A[a].next.last = b;
        A[a] = b;
    }

    public static void insertList(Node[] A, int a, Node newNode){
        newNode.last = A[a].last;
        newNode.next = A[a];
        A[a].last = newNode;
    }

    public static Node[] insertArray(Node[] A, int a, Node newNode){
        Node[] B = new Node[A.length + 1];
        int j = 0;
        for(int i = 0; i < B.length; i++){
            if(i != a){
                B[i] = A[j];
                j++;
            }else{
                B[i] = newNode;
            }
        }
        return B;
    }

    static class Node{
        int val;
        Node next;
        Node last;

        Node(int val){
            this.val = val;
        }
    }

    static class LinkedList{
        Node head = new Node(-1);
        Node tail = head;

        public static LinkedList insert(LinkedList list, Node x){
            list.tail.next = x;
            x.last = list.tail;
            list.tail = x;
            return list;
        }
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
            if(L[i].val <= R[j].val){
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
}

