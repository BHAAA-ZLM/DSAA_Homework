package Week1;

import java.math.BigInteger;
import java.util.Scanner;

public class Week1_C_00 {
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int x0 = input.nextInt();
        int y0 = input.nextInt();
        int x1 = input.nextInt();
        int y1 = input.nextInt();
        int f = input.nextInt();

        int a = x1 - x0;
        int b = y1 - y0;
        int sum = a + b;

        int sum1 = sum;
        BigInteger up = BigInteger.valueOf(1);
        for(int i = 0; i < a; i++){
            up = up.multiply(BigInteger.valueOf(sum1));
            sum1--;
        }

        int a1 = a;
        BigInteger down = BigInteger.ONE;
        for(int i = 0; i < a; i++){
            down = down.multiply(BigInteger.valueOf(a1));
            a1--;
        }
        BigInteger num_pos = up.divide(down) ;
        System.out.println(num_pos.mod(BigInteger.valueOf(f)));
    }
}
