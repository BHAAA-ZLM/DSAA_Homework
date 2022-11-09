package Week7;
import java.util.Scanner;

public class Week7_D_01 {
    public static final Scanner input = new Scanner(System.in);
    public static final int prime = 514379;

    public static void main(String[] args) {
        int length = input.nextInt();
        String track1 = input.next();
        String track2 = input.next();

        Direction[] t1 = constructDirection(track1);
        Direction[] t2 = constructDirection(track2);
        if(check(t1,t2)) {
            System.out.println("YES");
        }else System.out.println("NO");
    }

    public static boolean check(Direction[] t1,Direction[] t2) {
        int t2counter = 0;
        for(Direction d : t1){
            if(t2[t2counter] == d){
                t2counter++;
            } else if(t2counter > 0 && t2[t2counter - 1] == Direction.oppositeDirection(d)){
                t2counter--;
            }
        }

        int t1Hash = 0;
        int t2Hash = 0;
        int power = 1;
//        for(int i = t2.length - 1; i >= t2counter; i--){
//            t1Hash = Direction.getNumber(t1[i]) * power + t1Hash;
//            t2Hash = (t2Hash * 4 + Direction.getNumber(Direction.oppositeDirection(t2[i]))) % prime;
//            power = 4 * (power % prime);
//
//            if(t1Hash == t2Hash){
//                return false;
//            }
//        }
        for(int i = 0; i < t1.length - t2counter; i++){
            t1Hash = (t1Hash * 4 + Direction.getNumber(t1[t1.length - 1 - i])) % prime;
            t2Hash = (Direction.getNumber(Direction.oppositeDirection(t2[t2.length - 1 - i])) * power + t2Hash) % prime ;
            power = power * 4 % prime;
        }
        return true;
    }

    public static Direction[] constructDirection(String track){
        Direction[] directions = new Direction[track.length()];
        for(int i = 0; i < track.length(); i++){
            directions[i] = Direction.setDirection(track.charAt(i));
        }
        return directions;
    }

    public enum Direction {
        NORTH, SOUTH, EAST, WEST, NULL;

        public static Direction setDirection(char direction){
             switch (direction) {
                case 'N' : return NORTH;
                case 'S' : return SOUTH;
                case 'E' : return EAST;
                case 'W' : return WEST;
                default : throw new IllegalArgumentException("what?");
            }
        }

        public static Direction oppositeDirection(Direction direction){
            switch (direction) {
                case NORTH : return SOUTH;
                case SOUTH : return NORTH;
                case EAST : return WEST;
                case WEST : return EAST;
                default : throw new IllegalArgumentException("what?");
            }
        }

        public static int getNumber(Direction direction) {
             switch (direction) {
                case NORTH : return 0;
                case SOUTH : return 1;
                case EAST : return 2;
                case WEST : return 3;
                default : throw new IllegalArgumentException("what?");
            }
        }
    }
}
