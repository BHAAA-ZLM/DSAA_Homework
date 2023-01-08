package Week5;

import java.util.Random;

import static Week5.Week5_E_01.insertBeforeNode;

public class Week5_E_Test {
    public static void main(String[] args) {
        Random R = new Random();
        int T = 100;
        System.out.println(T);

        for(int i = 0; i < T; i++){
            int n = R.nextInt(100);
            if(n==0){
                i--;
                break;
            }
            System.out.print(n + " ");
            int x = R.nextInt(50);
            System.out.print(x + " ");
            System.out.println();
            for(int j = 0; j < n; j++){
                System.out.print(R.nextInt(80000) + " ");
            }
            System.out.println();
            for(int j = 0; j < x; j++) {
                int q = R.nextInt(3);
                if(q == 0){
                    System.out.println("I" + " " + (R.nextInt(n) + 1) + " " + R.nextInt(80000));
                }
                if(q == 1){
                    System.out.println("M" + " "+ (R.nextInt(n) + 1) + " "+ R.nextInt(80000));
                }
                if(q == 2){
                    int l = R.nextInt(n) + 1;
                    int r = R.nextInt(n) + 1;
                    int left = Math.min(l,r);
                    int right = Math.max(l,r);
                    System.out.println("Q" + " "+ left + " " + right + " " + (R.nextInt(right - left + 1)+1));
                }
            }
            System.out.println();
        }
    }
}
