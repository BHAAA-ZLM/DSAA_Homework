package Week6;

import java.util.Scanner;

public class Week6_A_00 {
    public static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        String obj = input.next();
        String[] stack = new String[obj.length()];
        int top = -1;
        int score = 0;
        for(int i = 0; i < stack.length; i++) {
            char bracket = obj.charAt(i);
            if(bracket=='('){
                top++;
                stack[top] = Character.toString(bracket);
            }else{
                if(stack[top].equals("(")){
                    stack[top] = "1";
                }else{
                    int inScore = 0;
                    while(!stack[top].equals("(")){
                        inScore += Integer.parseInt(stack[top]);
                        top--;
                    }
                    int finalScore = inScore * 2;
                    if(finalScore >= 514329){
                        finalScore -= 514329;
                    }
                    stack[top] = Integer.toString(finalScore);
                }
            }
        }
        while(top != -1){
            score += Integer.parseInt(stack[top]);
            top--;
            if(score >= 514329){
                score -= 514329;
            }
        }
        System.out.println(score);
    }
}
