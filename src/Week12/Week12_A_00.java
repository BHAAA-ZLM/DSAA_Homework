package Week12;

import java.util.Scanner;

public class Week12_A_00 {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int a = input.nextInt();
        int b = input.nextInt();
        int k = input.nextInt();
        int[] A = new int[a];
        int[] B = new int[b];
        for(int i = 0; i < a; i++){
            A[i] = input.nextInt();
        }
        for(int i = 0; i < b; i++){
            B[i] = input.nextInt();
        }

        mergeSort(A,0,a - 1);

        Heap heap = new Heap(b);
        for(int i = 0; i < b; i++){
            C c = new C(0,i, (long) A[0] * B[i]);
            heap.insert(c);
        }

        for(int i = 0; i < k; i++){
            C c = heap.pop();
            System.out.print(c.mul + " ");
            if(c.a + 1 < a) {
                C newC = new C(c.a + 1, c.b, (long) A[c.a + 1] * B[c.b]);
                heap.insert(newC);
            }
        }
    }

    public static void mergeSort(int[] arr,int l, int r){
        if(l < r){
            int mid = l + (r - l)/2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
            merge(arr,l,mid,r);
        }
    }
    public static void merge(int[] arr, int l, int mid, int r){
        int n1 = mid - l + 1;
        int n2 = r - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for(int i = 0; i < n1; i++){
            L[i] = arr[l + i];
        }
        for(int i = 0; i < n2; i++) {
            R[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0;
        int k = l;
        while(i < n1 && j < n2){
            if(L[i] <= R[j]){
                arr[k] = L[i];
                i++;
            }else{
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }


    static class C{
        int a;
        int b;
        long mul;
        C(int a, int b, long mul){
            this.a = a;
            this.b = b;
            this.mul = mul;
        }
    }

    static class Heap{
        C[] heap;
        int size = 0;
        public Heap(int n){
            heap = new C[n+1];
        }

        void insert(C x){
            size++;
            heap[size] = x;

            int cur = size;

            while(cur > 1) {
                if (heap[cur].mul < heap[cur / 2].mul) {
                    C tmp = heap[cur];
                    heap[cur] = heap[cur / 2];
                    heap[cur / 2] = tmp;
                    cur = cur/2;
                }else break;
            }
        }

        C pop(){
            C x = heap[1];
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
                    if(heap[pos].mul < heap[cur].mul) break;
                    C tmp = heap[cur];
                    heap[cur] = heap[pos];
                    heap[pos] = tmp;
                }
                if(cur + 1 <= size){
                    if(heap[cur].mul <= heap[cur + 1].mul){
                        if(heap[pos].mul < heap[cur].mul) break;
                        C tmp = heap[cur];
                        heap[cur] = heap[pos];
                        heap[pos] = tmp;
                    }else{
                        if(heap[pos].mul < heap[cur + 1].mul) break;
                        C tmp = heap[cur + 1];
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

