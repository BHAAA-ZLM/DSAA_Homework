package Week5;

import java.io.*;
import java.util.StringTokenizer;

public class Week5_B_00 {
    public static final QReader in = new QReader();
    public static final QWriter out = new QWriter();

    public static void main(String[] args) {
        int n = in.nextInt();//number of shops
        long m = in.nextLong();//number of money
        long sum = 0;//used to calculate total money of shops

        if(n == 0){
            System.out.println("0");
            return;
        }
        //Create a new linked loop of all the different shops
        LinkedLoop shops = new LinkedLoop();
        for(int i = 0; i < n; i++){
            int a = in.nextInt();
            sum += a;
            LinkedLoop.insert(shops, a);
        }
        LinkedLoop.seal(shops);//seal the shops together, leaving the pivot outside the ring

        out.print(checkShops(shops,m,sum,n));
        out.close();
    }

    public static long checkShops(LinkedLoop loop, long m, long sum, int n){
        long food = 0;
        Node prev = loop.last;
        while(loop.pivot.next.next != loop.pivot.next) {
            if (m >= sum) {
                if(sum == 0){
                    return food;
                }
                long mod = m % sum;
                food += (m - mod) / sum * n;
                m = mod;
            }
            while(m >= loop.pivot.next.val){
                if(m == loop.pivot.next.val){
                    food++;
                    return food;
                }
                m -= loop.pivot.next.val;
                food++;
                prev = loop.pivot.next;
                loop.pivot.next = loop.pivot.next.next;
            }
            while(m < loop.pivot.next.val && n > 0){
                sum -= loop.pivot.next.val;
                prev.next = loop.pivot.next.next;
                n--;
                loop.pivot.next = loop.pivot.next.next;
            }
        }
        while(m >= loop.pivot.next.val){
            food += (m - m % loop.pivot.next.val)/loop.pivot.next.val;
            m = m % loop.pivot.next.val;
        }
        return food;
    }

    static class LinkedLoop{
        Node pivot = new Node(-1);
        Node last;

        public static LinkedLoop insert(LinkedLoop loop, int a){
            Node newNode = new Node(a);
            if(loop.last == null){
                loop.pivot.next = newNode;
                loop.last = newNode;
            }else{
                loop.last.next = newNode;
                loop.last = newNode;
            }
            return loop;
        }

        public static LinkedLoop seal(LinkedLoop loop){
            loop.last.next = loop.pivot.next;
            return loop;
        }
    }

    static class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
            next = null;
        }
    }
}

