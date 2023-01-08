package Week6;

import java.util.Random;

public class Week6_C_Test {
    public static void main(String[] args) {
        Random R = new Random();
        int T = 1000;
        System.out.println(T);

        for(int j = 0; j < T; j++){
            int n = R.nextInt(2,20);
            int k = R.nextInt(1,n);
            int q = R.nextInt(1, n);
            System.out.println(n + " " + k + " " + q);
            for(int i = 0; i < n; i++){
                System.out.print(R.nextInt(30) + " ");
            }
            System.out.println();
            for(int i = 0; i < q; i++){
                System.out.print(R.nextInt(1, n - k + 1) + " ");
            }
            System.out.println();
            System.out.println();
        }
    }


}
