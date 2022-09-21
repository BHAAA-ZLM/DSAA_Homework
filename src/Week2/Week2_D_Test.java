package Week2;

import java.util.Random;

public class Week2_D_Test {

    private static final Random R = new Random();
    public static void main(String[] args) {
        int xr = R.nextInt(10);
        int yr = R.nextInt(10);
        int xc = R.nextInt(10);
        int yc = R.nextInt(10);
        int n = R.nextInt(100);
        String instructions = makeInstructions(n);
        System.out.println(xr + " " + yr + " " + xc + " " + yc);
        System.out.println(n);
        System.out.println(instructions);
    }

    private static String makeInstructions(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            int m = R.nextInt(4);
            if(m == 0){
                sb.append("U");
            }
            if(m == 1){
                sb.append("D");
            }
            if(m == 2){
                sb.append("L");
            }
            if(m == 3){
                sb.append("R");
            }
        }
        return sb.toString();
    }
}
