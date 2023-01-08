package Extra;

import java.util.Scanner;

public class Bonus_A_01 {
    public static final Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int T = in.nextInt();
        for(int t = 0; t < T; t++){
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = in.nextInt();
            }
            int min = arr[n - 1];
            int output = Integer.MIN_VALUE;
            for(int i = n - 2; i >= 0; i--){
                int cur = arr[i] - min;
                if(cur > output) output = cur;
                if(arr[i] < min) {
                    min = arr[i];
                }
            }
            System.out.println(output);
        }
    }
}