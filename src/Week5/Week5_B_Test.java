package Week5;

import java.util.Random;

public class Week5_B_Test {
    public static void main(String[] args) {
        Random R = new Random();
        int T = 10000;
        System.out.println(T);

        for(int i = 0; i < T; i++){
            int n = R.nextInt(10);
            System.out.print(n + " ");
            long x = R.nextLong(1000000000);
            long y = R.nextLong(1000000000);
            long m = x * y;
            System.out.print(m + " ");
            for(int j = 0; j < n; j++){
                System.out.print(R.nextInt(100000) + " ");
            }
            System.out.println();
        }

    }
}
