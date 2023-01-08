package Week2;

import java.util.Scanner;

public class Week2_D_00 {
    private static final Scanner input = new Scanner(System.in);
    private static final boolean debug = true;
    private static int uCount = 0, dCount = 0, lCount = 0, rCount = 0;
    private static int[] displacement;


    public static void main(String[] args) {
        int xr = input.nextInt();
        int yr = input.nextInt();
        int xc = input.nextInt();
        int yc = input.nextInt();
        int x = xr - xc;
        int y = yr - yc;
        int instructionSize = input.nextInt();
        String instructionStr = input.next();
        char[] instructions = new char[instructionSize];
        displacement = new int[instructionSize];
        for(int i = 0; i < instructionSize; i++){
            instructions[i] = instructionStr.charAt(i);
            countDirections(instructions[i]);
            if(i == 0){
                displacement[i] = checkDisplacement(instructions[i]);
            }else {
                displacement[i] = displacement[i - 1] + checkDisplacement(instructions[i]);
            }
        }
        System.out.println(chaseRobotTime(x, y, instructions));
    }

    private static int chaseRobotTime(int x, int y, char[] instructions){
        int maxTime = findMaxTime(x,y,instructions);
        if(maxTime == -1){
            return -1;
        }else if(maxTime == -2){
            return calculateChaseTime(x,y,instructions);
        }else{
            return calculateChaseTimeWithMax(x,y,instructions,maxTime);
        }
    }

    private static int findMaxTime(int x, int y, char[] instructions){
        int deltaX = rCount - lCount;
        int deltaY = uCount - dCount;
        if(instructions.length == Math.abs(deltaX) + Math.abs(deltaY)){
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
                if(debug) System.out.println("found max time " + ((m + 1) * instructions.length));
                return (m + 1) * instructions.length;
            }
        }else{
            if(debug) System.out.println("can't find max time");
            return -2;
        }
    }

    private static int calculateChaseTimeWithMax(int x, int y, char[] instructions, int maxTime){
        if(catchAtTime(x,y,instructions,maxTime)){
            int lowerLimit = 0;
            int upperLimit = maxTime;
            while(lowerLimit <= upperLimit){
                int mid = lowerLimit + (upperLimit - lowerLimit) / 2;
                if(catchAtTime(x,y,instructions, mid)){
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

    private static int calculateChaseTime(int x, int y, char[] instructions){
        int lowerLimit = 0;
        int upperLimit = 10000;
        while(!catchAtTime(x,y,instructions, upperLimit)){
            upperLimit *= 2;
        }
        while(lowerLimit <= upperLimit){
            int mid = lowerLimit + (upperLimit - lowerLimit) / 2;
            if(catchAtTime(x,y,instructions, mid)){
                upperLimit = mid - 1;
            }else{
                lowerLimit = mid + 1;
            }
        }
        return lowerLimit;
    }

    private static boolean catchAtTime(int x, int y, char[] instructions, int time){
        int deltaX = Math.abs(rCount - lCount);
        int deltaY = Math.abs(uCount - dCount);
        int robotMove = (time / instructions.length) * (deltaX + deltaY) + displacement[time % instructions.length];
        int humanMove = time - Math.abs(x) - Math.abs(y);
        if(robotMove <= humanMove){
            return true;
        }else{
            return false;
        }
    }

    private static void countDirections(char c){
        if(c == 'U'){
            uCount++;
        }
        if(c == 'D'){
            dCount++;
        }
        if(c == 'L'){
            lCount++;
        }
        if(c == 'R'){
            rCount++;
        }
    }

    private static int checkDisplacement(char c){
        if(c == 'U'){
            return 1;
        }
        if(c == 'D'){
            return -1;
        }
        if(c == 'L'){
            return -1;
        }
        if(c == 'R'){
            return 1;
        }
        return 0;
    }


}
