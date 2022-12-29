package Extra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bonus_D_00 {
    public final static QReader in = new QReader();

    public static void main(String[] args) {
        int T = in.nextInt();
        for(int t = 0; t < T; t++){
            int n = in.nextInt();
            if(n % 2 == 1){
                String s = in.next();
                System.out.println("NO");
                continue;
            }else{
                Heap heap = new Heap(n);
                String s = in.next();
                for(int i = 0; i < n; i++){
                    char c = s.charAt(i);
                    check(c, heap);
                }
                if(heap.size() == 0){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }
    }

    public static void check(char c, Heap heap){
        if(c == '(' || c == '[' || c == '{') {
            heap.insert(c);
        }
        if(c == ')'){
            if(heap.size() == 0 || heap.top() != '(') {
                heap.insert(c);
            }else{
                heap.pop();
            }
        }
        if(c == ']'){
            if(heap.size() == 0 || heap.top() != '[') {
                heap.insert(c);
            }else{
                heap.pop();
            }
        }
        if(c == '}'){
            if(heap.size() == 0 || heap.top() != '{') {
                heap.insert(c);
            }else{
                heap.pop();
            }
        }
    }

    static class Heap{
        char[] heap;
        Heap(int n){
            heap = new char[n];
        }
        int cur = 0;

        void insert(char c){
            heap[cur] = c;
            cur++;
        }

        void pop(){
            cur--;
        }

        int size(){
            return cur;
        }

        char top(){
            return heap[cur - 1];
        }
    }
}

