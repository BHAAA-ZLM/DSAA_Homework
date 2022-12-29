package Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Week12_D_Brute {
    public static final QReader in = new QReader();
    public static int C;
    public static void main(String[] args) {
        int T = in.nextInt();
        for(int z = 0 ; z < T; z++) {
            ArrayList<Integer> commands = new ArrayList<>();
            C = in.nextInt();
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                commands.add(in.nextInt());
            }
            int m = in.nextInt();
            for (int i = 0; i < m; i++) {
                checkCommand(in.next(), commands);
            }
        }
    }

    public static void checkCommand(String cmd, ArrayList<Integer> commands){
        if(cmd.equals("rem")){
            int n = in.nextInt();
            commands.remove(n - 1);
        }else if(cmd.equals("ins")){
            int n = in.nextInt() - 1;
            int key = in.nextInt();
            commands.add(n,key);
        }else if(cmd.equals("ask")){
            int n = in.nextInt();
            if(commands.size() == 0){
                System.out.println(n);
            }else {
                for (Integer i : commands) {
                    n = n + i;
                    if (n > C) {
                        n = C;
                    }
                    if (n < 0) {
                        n = 0;
                    }
                }
                System.out.println(n);
            }
        }
    }
}



