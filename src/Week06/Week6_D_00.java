package Week6;

import java.util.Scanner;

public class Week6_D_00 {
    public static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int n = input.nextInt();
        int[] arr = new int[n + 1];
        int[] best = new int[n + 1];
        arr[0] = 0;
        int length = 0;
        for(int i = 1; i < n + 1; i++){
            arr[i] = arr[i - 1] + input.nextInt();
        }
        best[n] = arr[n];
        for(int i = n - 1; i >= 0; i--){
            if(best[i+1] > arr[i]) {
                best[i] = best[i + 1];
            }else{
                best[i] = arr[i];
            }
        }

        int start = 1;
        for(int end = 1; end < n + 1; end++){
            if(best[end] >= arr[start - 1]){
                if(length < end - start + 1){
                    length = end - start + 1;
                }
            }else start++;
        }
        System.out.println(length);
    }
}
