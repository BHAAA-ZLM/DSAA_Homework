package Week12;

import java.util.Scanner;

public class Week12_B_00 {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int testcase = input.nextInt();
        for(int i = 0; i < testcase; i++){
            int n = input.nextInt();
            Heap heap = new Heap(n);
            for(int j = 0; j < n; j++){
                heap.insert(input.nextInt());
            }
            System.out.println(checkHeap(heap));
        }
    }

    public static int checkHeap(Heap heap){
        int sum = 0;
        while(heap.size != 0){
            if(heap.size > 1) {
                int tmpsum = heap.pop() + heap.pop();
                sum = sum + tmpsum;
                if(heap.size != 0) {
                    heap.insert(tmpsum);
                }
            }else{
                sum += heap.pop();
            }
        }
        return sum;
    }

    static class Heap{
        int[] heap;
        int size = 0;
        public Heap(int n){
            heap = new int[n+1];
        }

        void insert(int x){
            size++;
            heap[size] = x;

            int cur = size;

            while(cur > 1) {
                if (heap[cur] < heap[cur / 2]) {
                    int tmp = heap[cur];
                    heap[cur] = heap[cur / 2];
                    heap[cur / 2] = tmp;
                    cur = cur/2;
                }else break;
            }
        }

        int pop(){
            int x = heap[1];
            heap[1] = heap[size];
            heap[size] = 0;
            size--;
            int cur = 1;
            while(cur <= size){
                int pos = cur;
                cur = cur * 2;
                if(cur > size) break;
                if(cur == size){
                    // with only one child
                    if(heap[pos] < heap[cur]) break;
                    int tmp = heap[cur];
                    heap[cur] = heap[pos];
                    heap[pos] = tmp;
                }
                if(cur + 1 <= size){
                    if(heap[cur] <= heap[cur + 1]){
                        if(heap[pos] < heap[cur]) break;
                        int tmp = heap[cur];
                        heap[cur] = heap[pos];
                        heap[pos] = tmp;
                    }else{
                        if(heap[pos] < heap[cur + 1]) break;
                        int tmp = heap[cur + 1];
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

