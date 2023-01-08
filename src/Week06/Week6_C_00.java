package Week6;

import java.util.Scanner;

public class Week6_C_00 {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int T = input.nextInt();
        for (int z = 0; z < T; z++) {
            int n = input.nextInt();
            int k = input.nextInt();
            int q = input.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = input.nextInt();
            }
            for (int i = 0; i < q; i++) {
                int l = input.nextInt() - 1;
                int r = l + k - 1;
                int max = arr[l];
                for (int j = l; j <= r; j++) {
                    if (max < arr[j]) {
                        max = arr[j];
                    }
                }
                System.out.println(max);
            }
        }
    }
}
