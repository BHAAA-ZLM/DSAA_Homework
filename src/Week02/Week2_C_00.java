package Week2;

import java.util.Scanner;

public class Week2_C_00 {

    private static final Scanner input = new Scanner(System.in);
    private static final boolean debug = false;

    public static void main(String[] args) {
        int studentNum = input.nextInt();
        int mooncakeNum = input.nextInt();
        double[] mooncakeSize = new double[mooncakeNum];
        for(int i = 0; i < mooncakeNum; i++){
            int r = input.nextInt();
            mooncakeSize[i] = r * r * Math.PI;
            if(debug) System.out.println("adding mooncake size " + mooncakeSize[i]);

        }
        System.out.println(maximumMooncakeSize(studentNum, mooncakeSize));
    }

    /**
     * find the largest piece of mooncake each student can get
     * @param num
     * @param mooncakeSize
     * @return
     */
    private static double maximumMooncakeSize(int num, double[] mooncakeSize){
        double maxMooncakeSize = findMaxMooncake(mooncakeSize);
        double smaller = maxMooncakeSize / 2;
        double larger = maxMooncakeSize;
        double iteration = 1d;
        while(iteration > 0.0000005){
            iteration = (larger - smaller)/2;
            int pieceCount = 0;
            for(double size : mooncakeSize){
                pieceCount += Math.floor(size/smaller);
            }
            if(pieceCount >= num){
                smaller += iteration;
            }else{
                larger = smaller;
                smaller -= iteration;
            }
        }
        return smaller;
    }

    /**
     * find the largest mooncake in all the mooncakes
     * @param mooncakeSize
     * @return
     */
    private static double findMaxMooncake(double[] mooncakeSize){
        double max = mooncakeSize[0];
        for(int i = 0; i < mooncakeSize.length; i++){
            if(mooncakeSize[i] > max){
                max = mooncakeSize[i];
            }
        }
        return max;
    }
}
