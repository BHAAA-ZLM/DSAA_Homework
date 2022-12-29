package Extra;

import java.io.*;
import java.util.StringTokenizer;

public class Bonus_A_00 {

    public static final QReader in = new QReader();
    public static final QWriter out = new QWriter();

    public static void main(String[] args) {
        int T = in.nextInt();
        for(int t = 0; t < T; t++){
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = in.nextInt();
            }
            int[] sorted_arr = arr.clone();
            int cur = 0;
            int max = Integer.MIN_VALUE;
            boolean[] visited = new boolean[n];
            mergeSort(sorted_arr, 0, n - 1);
            for(int i = 0; i < n; i++){
                visited[binarySearch(sorted_arr, arr[i])] = true;
                while(visited[cur]){
                    cur++;
                    if(cur >= n){
                        break;
                    }
                }
                if(cur >= n){
                    break;
                }
                if(max < arr[i] - sorted_arr[cur]){
                    max = arr[i] - sorted_arr[cur];
                }
            }
            System.out.println(max);
        }
    }

    public static int binarySearch(int[] arr, int x){
        int left = 0;
        int right = arr.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(arr[mid] == x){
                return mid;
            }else if(arr[mid] > x){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return -1;
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
            if(L[i] < R[j]){
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
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}
class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}
