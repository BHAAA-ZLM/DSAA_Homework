package Week6;

import java.util.Scanner;

public class Week6_C_01 {
    public final static Scanner input = new Scanner(System.in);
    public final static boolean debug = false;

    public static void main(String[] args) {
//        for(int z = 0; z < input.nextInt(); z++) {
            int n = input.nextInt();
            int k = input.nextInt();
            int q;
            if (!debug) {
                q = input.nextInt();
            }
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                int x = input.nextInt();
                arr[i] = x;
            }
            int[] max = slideMax(arr, k);
            if (debug) {
                for (int i : max) {
                    System.out.println(i);
                }
            }
            if (!debug) {
                for (int i = 0; i < q; i++) {
                    System.out.println(max[input.nextInt() - 1]);
                }
            }
//        }
    }

    public static int[] slideMax(int[] arr, int k){
        int[] max = new int[arr.length - k + 1];
        Queue queue = new Queue();
        for(int i = 0; i < arr.length; i++){
            Node newNode = new Node(arr[i]);
            Queue.insert(queue,newNode,k);
            if(i >= k - 1){
                max[i - k + 1] = queue.head.val;
            }
        }
        return max;
    }

    static class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
        }
    }

    static class Queue{
        Node head = new Node(-1);
        Node tmpNode = head;
        int count = 1;

        public static Queue insert(Queue queue, Node node,int k){
            queue.tmpNode.next = node;
            if(queue.count >= k){
                queue.head = queue.head.next;
                queue.count--;
                Node findMax = queue.head;
                int max = queue.head.val;
                while(findMax != null){
                    if(max < findMax.val){
                        max = findMax.val;
                    }
                    findMax = findMax.next;
                }
                while(queue.head.next != null) {
                    if(queue.head.val < max) {
                        queue.head = queue.head.next;
                        queue.count--;
                    }else{
                        break;
                    }
                }
            }
            while(node.val > queue.head.val){
                queue.head = queue.head.next;
                queue.count--;
            }
            queue.tmpNode = node;
            queue.count++;
            return queue;
        }
    }
}
