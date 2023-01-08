package Week2;

import java.util.Scanner;

public class Week2_A_00 {
    private static final Scanner input = new Scanner(System.in);
    private static final boolean debug = false;

    public static void main(String[] args) {
        int arrayLength = input.nextInt();
        int times = input.nextInt();
        int[] theArray = new int[arrayLength];
        for(int i = 0; i < arrayLength; i++){
            theArray[i] = input.nextInt();
        }
        for(int i = 0; i < times; i++){
            checkArray(input.nextInt(),input.nextInt(),theArray);
        }
    }

    private static void checkArray(int x, int y, int[] theArray){
        int indexX = binarySearchX(x, theArray);
        int indexY = binarySearchY(y, theArray);
        if(indexY - indexX - 1 > 0){
                System.out.println("YES " + (indexY - indexX - 1));
        }else{
            System.out.println("NO");
        }
    }

    private static int binarySearchX(int x, int[] theArray){
        if(x < theArray[0]){
           return  -1;
        }
        int left = 0;
        int right = theArray.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(x >= theArray[mid]){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
           if(debug) System.out.println("for x left: " + left + " for right: " + right);
        }
        if(left < theArray.length && left >= 0 && x == theArray[left]) {
            if(debug) System.out.println("index x: " + left);
            return left;
        }else{
            if(debug) System.out.println("index x: " + right);
            return right;
        }
    }

    private static int binarySearchY(int x, int[] theArray){
        if(x > theArray[theArray.length - 1]){
            return theArray.length;
        }
        int left = 0;
        int right = theArray.length - 1;
        while(right >= left){
            int mid = left + (right - left) / 2;
            if(x <= theArray[mid]){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
            if(debug) System.out.println("for y left: " + left + " for right: " + right);
        }
        if(right >= 0 && right < theArray.length && x == theArray[right]) {
            if(debug) System.out.println("index y: " + right);
            return right;
        }else{
            if(debug) System.out.println("index y: " + left);
            return left;
        }
    }
}
