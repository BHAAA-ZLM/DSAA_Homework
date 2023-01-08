package Week7;

import java.util.Scanner;

public class Week7_D_KMP {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        input.nextInt();
        String track1 = input.next();
        String track2 = input.next();
        char[] track = new char[track1.length() * 2];
        int index = 0;
        for(int i = track2.length() - 1; i >= 0; i--){
            if(track2.charAt(i) == 'N'){
                track[index++] = 'S';
            }
            if(track2.charAt(i) == 'S'){
                track[index++] = 'N';
            }
            if(track2.charAt(i) == 'W'){
                track[index++] = 'E';
            }
            if(track2.charAt(i) == 'E'){
                track[index++] = 'W';
            }
        }
        for(int i = 0; i < track1.length(); i++){
            track[index++] = track1.charAt(i);
        }

        if(check(track)){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }

    public static boolean check(char[] track){
        int[] lps = new int[track.length];
        int k = 0;
        lps[0] = 0;
        for(int i = 1; i < lps.length; i++){
            while(k > 0 && track[k] != track[i]){
                k = lps[k - 1];
            }
            if(track[k] == track[i]){
                k++;
            }
            lps[i] = k;
        }
        if(lps[track.length - 1] == 0){
            return true;
        }else return false;
    }
}
