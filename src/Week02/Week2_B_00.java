package Week2;

import java.util.Scanner;

public class Week2_B_00 {
    private static final Scanner input = new Scanner(System.in);
    private static final boolean debug = false;

    public static void main(String[] args) {
        int arrLength = input.nextInt();
        int sum = input.nextInt();
        int[] theArray = new int[arrLength];
        for(int i = 0; i < theArray.length; i++) {
            theArray[i] = input.nextInt();
        }
        int count = 0;
        for(int i = 0; i < theArray.length - 2; i++) {
            for(int j = i + 1; j < theArray.length - 1; j++) {
                if(debug) {
                    System.out.println("a is " + theArray[i]);
                    System.out.println("b is " + theArray[j]);
                }
                int c = sum - theArray[i] - theArray[j];
                if(debug) System.out.println("searching for " + c);
                if(debug) System.out.println("found " + checkRest(c,j,theArray));
                count += checkRest(c, j, theArray);
            }
        }
        System.out.println(count);
    }

    private static int checkRest(int c, int j, int[] theArray) {
        int left = findLeftIndex(c,j,theArray);
        if(left == -1){
            return 0;
        }
        int right = findRightIndex(c,j,theArray);
        if(right == -1){
            return 0;
        }
        return right - left - 1;
    }

    private static int findRightIndex(int c, int j, int[] theArray){
        if(c >= theArray[theArray.length - 1]){
            return theArray.length;
        }
        int left = j + 1;
        int right = theArray.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(c >= theArray[mid]){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        if(debug) System.out.println("found right index " + left);
        return left;
    }

    private static int findLeftIndex(int c, int j, int[] theArray){
        if(c <= theArray[j + 1]){
            return j;
        }
        int left = j + 1;
        int right = theArray.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(c <= theArray[mid]){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        if(debug) System.out.println("found left index " + right);
        return right;
    }
}
