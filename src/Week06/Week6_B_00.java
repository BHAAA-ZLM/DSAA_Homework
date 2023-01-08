package Week6;

import java.util.Scanner;

public class Week6_B_00 {
    public static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int n = input.nextInt();
        int[] arr = new int[n];
        int top = 0;
        int bot = 0;
        for(int i = 0; i < n; i++){
            String operation = input.next();
            if(operation.equals("E")){
                arr[top] = input.nextInt();
                top++;
            }else if(operation.equals("D")){
                bot += input.nextInt();
                System.out.println(arr[bot]);
            }
        }
    }
}
