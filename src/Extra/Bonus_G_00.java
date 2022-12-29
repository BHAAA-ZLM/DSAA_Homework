package Extra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bonus_G_00 {
    public final static QReader in = new QReader();
    public static void main(String[] args) {
        int T = in.nextInt();
        for(int t = 0; t < T; t++){
            int n = in.nextInt();
            boolean appear_zero = false;
            boolean correct = true;
            for(int i = 0; i < n; i++){
                int a = in.nextInt();
                int b = in.nextInt();
                if(appear_zero) {
                    if (a != 0 || b != 0) {
                        System.out.println("NO");
                        correct = false;
                        break;
                    }
                }
                if(a == 0 || b == 0){
                    appear_zero = true;
                }
            }
            if(correct) {
                System.out.println("YES");
            }
        }
    }
}

