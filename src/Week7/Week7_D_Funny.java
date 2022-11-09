package Week7;

import java.util.Scanner;

public class Week7_D_Funny {
    public static final Scanner input = new Scanner(System.in);
    public static final int hash = 5;

    public static void main(String[] args) {
        int n = input.nextInt();
        String track1 = input.next();
        String track2 = input.next();

        if(checkTrack(track1, track2)){
            System.out.println("NO");
        }else {
            System.out.println("YES");
        }
    }

    public static boolean checkTrack(String track1, String track2){
        int track2count = getPosition(track1, track2);

        int track1hash = 0;
        int track2hash = 0;
        int n = 1;
        for(int i = track1.length() - 1; i >= track2count; i--){
            track1hash = track1hash * hash + getInt(track1.charAt(i));
            track2hash = track2hash + getInt(getOpposite(track2.charAt(i))) * n;
            n *= hash;
            if(track1hash == track2hash){
                return true;
            }
        }
        return false;
    }

    public static int getPosition(String track1, String track2){
        int track2count = 0;
        for (int i = 0; i < track1.length(); i++){
            if(track1.charAt(i) == track2.charAt(track2count)){
                track2count++;
            }else if(track2count > 0 && track1.charAt(i) == getOpposite(track2.charAt(track2count - 1))){
                track2count--;
            }
        }
        return track2count;
    }

    public static char getOpposite(char a) {
        if(a == 'N'){
            return 'S';
        }else if(a == 'S') {
            return 'N';
        }else if(a == 'E'){
            return 'W';
        }else{
            return 'E';
        }
    }

    public static int getInt(char a){
        if(a == 'N'){
            return 0;
        }else if(a == 'S') {
            return 1;
        }else if(a == 'E'){
            return 2;
        }else{
            return 3;
        }
    }
}
