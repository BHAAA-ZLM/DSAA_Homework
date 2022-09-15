package Week1;

import java.util.ArrayList;
import java.util.Scanner;

public class Week1_B_00 {
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        String deal_card = input.next();
        char deal_rank = deal_card.charAt(0);
        char deal_suit = deal_card.charAt(1);

        String[] cards = new String[5];
        for (int i = 0; i < 5; i++) {
            cards[i] = input.next();
        }

        ArrayList<Character> rank= new ArrayList<>();
        ArrayList<Character> suit= new ArrayList<>();

        for(int i = 0; i < 5; i++){
            rank.add(cards[i].charAt(0));
            suit.add(cards[i].charAt(1));
        }

        if(rank.contains(deal_rank)||suit.contains(deal_suit)){
            System.out.println("All in");
        }else{
            System.out.println("Fold");
        }
    }
}
