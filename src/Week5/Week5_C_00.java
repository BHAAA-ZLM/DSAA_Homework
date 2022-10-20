package Week5;

import java.io.*;
import java.util.StringTokenizer;

public class Week5_C_00 {

    public static final QReader in = new QReader();
    public static final QWriter out = new QWriter();

    public static void main(String[] args) {
        int n = in.nextInt();
        Node[] A = new Node[n];
        LinkedList list = new LinkedList();
        for(int i = 0; i < n; i++){
            Node a = new Node(in.nextInt());
            A[i] = a;
            LinkedList.insertPosition(list, a);
        }//O(n)
        mergeSort(A, 0, n - 1);//O(nlogn)
        for(Node a : A){
            LinkedList.insertLarger(list, a);
        }//O(n)
        Node curr = list.head.next;
        while(curr.next != null) {
            out.print(checkNode(curr) + " ");
            curr = curr.next;
        }
        out.close();
    }

    public static int checkNode(Node n){
        if(n.larger == null){
            int min = n.value - n.smaller.value;
            n.smaller.larger = n.larger;
            return min;
        }else if(n.smaller == null || n.smaller.value == -1){
            int large = n.larger.value - n.value;
            n.larger.smaller = n.smaller;
            return large;
        }
        int large = n.larger.value - n.value;
        int min = n.value - n.smaller.value;

        n.smaller.larger = n.larger;
        n.larger.smaller = n.smaller;

        return Math.min(large,min);
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
            if(L[i].value <= R[j].value){
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
        int value;
        Node next;
        Node larger;
        Node smaller;

        Node(int value){
            this.value = value;
        }
    }

    static class LinkedList{
        Node head = new Node(-1);
        Node tmpPos = head;
        Node tmpLar = head;

        public static LinkedList insertPosition(LinkedList list, Node a){
            list.tmpPos.next = a;
            list.tmpPos = a;
            return list;
        }

        public static LinkedList insertLarger(LinkedList list, Node a){
            a.smaller = list.tmpLar;
            list.tmpLar.larger = a;
            list.tmpLar = a;
            return list;
        }
    }
}