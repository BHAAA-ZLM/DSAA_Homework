package Week2;

import java.util.Scanner;


public class Week2_D_01 {
    private static final Scanner input = new Scanner(System.in);
    private static final boolean debug = false;

    public static void main(String[] args) {
        int xr = input.nextInt();
        int yr = input.nextInt();
        int xc = input.nextInt();
        int yc = input.nextInt();
        int x = xr - xc;
        int y = yr - yc;
        int times = input.nextInt();
        String str = input.next();
        int[][] instructions = new int[times][2];
        int[][] displacement = new int[times][2];
        for(int i = 0; i < times; i++){
            if(str.charAt(i) == 'U') {
                instructions[i] = new int[]{0,1};
            }
            if(str.charAt(i) == 'D') {
                instructions[i] = new int[]{0,-1};
            }
            if(str.charAt(i) == 'R') {
                instructions[i] = new int[]{1,0};
            }
            if(str.charAt(i) == 'L') {
                instructions[i] = new int[]{-1,0};
            }
            if(i == 0){
                displacement[i] = instructions[i];
            }else {
                displacement[i][0] = instructions[i][0] + displacement[i-1][0];
                displacement[i][1] = instructions[i][1] + displacement[i-1][1];
            }
        }
        System.out.println(chaseRobotTime(x,y,displacement));
    }

    private static long chaseRobotTime(int x, int y, int[][] displacement){
        long maxTime = findMaxTime(x,y,displacement);
        if(maxTime == -1){
            return -1;
        }else if(maxTime == -2){
            return calculateChaseTime(x,y,displacement);
        }else{
            return calculateChaseTimeWithMax(x,y,displacement,maxTime);
        }
    }

    private static long calculateChaseTime(int x, int y, int[][] displacement){
        long lowerLimit = 0;
        long upperLimit = 10000;
        while(!catchAtTime(x,y,displacement, upperLimit)){
            upperLimit *= 2;
        }
        while(lowerLimit <= upperLimit){
            long mid = lowerLimit + (upperLimit - lowerLimit) / 2;
            if(catchAtTime(x,y,displacement, mid)){
                upperLimit = mid - 1;
            }else{
                lowerLimit = mid + 1;
            }
        }
        return lowerLimit;
    }

    private static long calculateChaseTimeWithMax(int x, int y, int[][] displacement, long maxTime){
        if(catchAtTime(x,y,displacement,maxTime)){
            long lowerLimit = 0;
            long upperLimit = maxTime;
            while(lowerLimit <= upperLimit){
                long mid = lowerLimit + (upperLimit - lowerLimit) / 2;
                if(catchAtTime(x,y,displacement, mid)){
                    upperLimit = mid - 1;
                }else{
                    lowerLimit = mid + 1;
                }
            }
            return lowerLimit;
        }else{
            return -1;
        }
    }

    private static boolean catchAtTime(int x, int y, int[][] displacement, long time){
        long robotMoveX = displacement[displacement.length-1][0] * (time/ displacement.length);
        long robotMoveY = displacement[displacement.length-1][1] * (time/ displacement.length);
        if(time % displacement.length != 0){
            robotMoveX += displacement[(int)(time % displacement.length) - 1][0];
            robotMoveY += displacement[(int)(time % displacement.length) - 1][1];
        }
        long varyX = x + robotMoveX;
        long varyY = y + robotMoveY;
        long humanMove = time - Math.abs(varyX) - Math.abs(varyY);
        if(humanMove >= 0){
            return true;
        }else{
            return false;
        }
    }


    private static long findMaxTime(int x, int y, int[][] displacement){
        int deltaX = displacement[displacement.length-1][0];
        int deltaY = displacement[displacement.length-1][1];
        if(displacement.length == Math.abs(deltaX) + Math.abs(deltaY)){
            int m;
            if(deltaX != 0 && deltaY != 0){
                m = Math.max(-x/deltaX, -y/deltaY);
            }else if(deltaX == 0){
                m = -y/deltaY;
            }else{
                m = -x/deltaX;
            }
            if(m <= 0){
                if(debug) System.out.println("found cannot catch at all");
                return -1;
            }else{
                if(debug) System.out.println("found max time " + ((m + 1) * displacement.length));
                return (long)(m + 1) * (long)displacement.length;
            }
        }else{
            if(debug) System.out.println("can't find max time");
            return -2;
        }
    }
}


