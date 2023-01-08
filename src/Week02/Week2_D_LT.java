package Week2;

import java.util.Scanner;

public class Week2_D_LT {

    private static Point[] displacements;
    private static Point circleDis;
    private static Point robot;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public static class Point {
        public final long x;
        public final long y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public Point move(Direction direction) {
            switch (direction) {
                case UP:
                    return new Point(x, y + 1);
                case DOWN:
                    return new Point(x, y - 1);
                case LEFT:
                    return new Point(x - 1, y);
                case RIGHT:
                    return new Point(x + 1, y);
                default:
                    throw new IllegalArgumentException();
            }
        }

        public Point move(long x, long y) {
            return new Point(this.x + x, this.y + y);
        }

        public Point add(Point point) {
            return new Point(x + point.x, y + point.y);
        }

        public long distance() {
            return Math.abs(x) + Math.abs(y);
        }
    }

    public static long search() {
        long maxTime = 1;
        //check if cannot end
        if (circleDis.distance() >= displacements.length) {
            long xCir = circleDis.x == 0 ? -1 : - robot.x / circleDis.x;
            long yCir = circleDis.y == 0 ? -1 : - robot.y / circleDis.y;

            long circles = Math.max(xCir, yCir);
            if (circles < 0) {
                return -1;
            }
            maxTime = (circles + 1) * displacements.length;
            if (!canCatchUp(maxTime))
                return -1;
        }
        //must end
        else {
            while (!canCatchUp(maxTime))
                maxTime *= 2;
        }

        Debug.println("Begin binary search, max time = " + maxTime);

        //binary search
        long l = 1, r = maxTime;
        while (l <= r) {
            long mid = l + (r - l) / 2 + (r - l) % 2 ;
            if (!canCatchUp(mid)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r + 1;
    }

    public static long search2(Direction[] instructions) {
        long time = 0;
        Point p = new Point(robot.x, robot.y);
        while (p.distance() > time) {
            p = p.move(instructions[(int) (time % displacements.length)]);
            ++time;
        }
        return time;
    }

    public static void initialize(Point rb, Direction[] instructions) {
        robot = rb;

        //go through a circle
        circleDis = new Point(0,0);
        displacements = new Point[instructions.length];
        displacements[0] = new Point(0,0);
        for (int i = 0; i < instructions.length - 1; i++) {
            circleDis = circleDis.move(instructions[i]);
            displacements[i + 1] = circleDis;
        }
        circleDis = circleDis.move(instructions[instructions.length - 1]);
        Debug.println("Circle length: " + displacements.length);
        Debug.println("Circle displacement: (" + circleDis.x + ", " + circleDis.y + ")");
    }

    private static boolean canCatchUp(long time) {
        long circle = time / displacements.length;
        Point end = robot.move(circleDis.x * circle, circleDis.y * circle)
                .add(displacements[(int) (time % displacements.length)]);
        Debug.println("time: " + time + " end: (" + end.x + ", " + end.y + ")");
        boolean ifCatchUp = end.distance() <= time;
        Debug.println("can catch up: " + ifCatchUp);
        return ifCatchUp;
    }

    public static void main(String[] args) {
        //Debug.isOn = true;

        Scanner input = new Scanner(System.in);
        int xr = input.nextInt();
        int yr = input.nextInt();
        int x0 = input.nextInt();
        int y0 = input.nextInt();

        int n = input.nextInt();
        Direction[] instructions = new Direction[n];
        String instruction = input.next();
        for (int i = 0; i < n; i++) {
            instructions[i] = getDirection(instruction.charAt(i));
        }
        initialize(new Point(xr - x0, yr - y0), instructions);
        System.out.println(search());
    }

    private static Direction getDirection(char c) {
        switch (c) {
            case 'U':
                return Direction.UP;
            case 'D':
                return Direction.DOWN;
            case 'L':
                return Direction.LEFT;
            case 'R':
                return Direction.RIGHT;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static class Debug {
        public static boolean isOn;
        public static void println(String s) {
            if (isOn) {
                System.out.println(s);
            }
        }
    }
}
