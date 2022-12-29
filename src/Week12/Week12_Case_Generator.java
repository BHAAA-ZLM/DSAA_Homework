package Week12;

import java.util.Random;

public class Week12_Case_Generator {
    public static final Random R = new Random();

    public static void main(String[] args) {
        int T = 100;
        System.out.println(T);

        for(int i = 0; i < T; i++){
            System.out.println();
            int C = R.nextInt(50);
            int N = R.nextInt(10);
            System.out.print(C + " " + N + " \n");
            for(int j = 0; j < N; j++){
                System.out.print(R.nextInt(-C, C + 1) + " ");
            }
            System.out.println();
            int M = R.nextInt(10);
            System.out.println(M + 1);
            for(int j = 0; j < M; j++){
                int tmp = R.nextInt(3);
                if(tmp == 1){
                    int ask = R.nextInt(0,C + 1);
                    System.out.println("ask " + ask);
                }else if(tmp == 2){
                    int insPos;
                    if(N == 0){
                        insPos = 1;
                    }else {
                        insPos = R.nextInt(1, N + 1);
                    }
                    N++;
                    int insVal = R.nextInt(-C, C + 1);
                    System.out.println("ins " + insPos + " " + insVal);
                }else{
                    if(N == 0){
                        j--;
                        continue;
                    }
                    int delPos = R.nextInt(1, N + 1);
                    N--;
                    System.out.println("rem " + delPos);
                }
            }
            int ask = R.nextInt(0,C + 1);
            System.out.println("ask " + ask);
        }
    }
}
