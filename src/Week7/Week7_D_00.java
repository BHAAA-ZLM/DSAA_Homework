package Week7;

import java.util.Scanner;

public class Week7_D_00 {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int n = input.nextInt();
        String track1 = input.next();
        String track2 = input.next();

        int left = 0, right = n - 2;

//        while(left <= right){
//            int mid = left + (right - left) / 2;
//            if(check(track1,track2,mid)){
//                System.out.println("NO");
//                return;
//            }else{
//                left = mid + 1;
//            }
//        }
        for(int i = 0; i < right; i ++){
            if(check(track1,track2,i)){
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    public static boolean check(String track1, String track2, int mid){
        for(int i = 0; i < track1.length()-mid; i++){
            if(!checkAlphabet(track1.charAt(mid + i),track2.charAt(track2.length()-i-1))){
                return false;
            }
        }
        return true;
    }

    public static boolean checkAlphabet(char a, char b){
        if(a == 'N' && b == 'S'){
            return true;
        }
        if(a == 'S' && b == 'N'){
            return true;
        }
        if(a == 'E' && b == 'W'){
            return true;
        }
        return a == 'W' && b == 'E';
    }
}
