package Week12;

import java.util.Scanner;

public class Test {
    public static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        while(true) {
            int n = input.nextInt();
            int m = input.nextInt();
            System.out.println((n ^ m) + 1);
        }
    }
}
