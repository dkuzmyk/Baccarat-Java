import java.util.ArrayList;

public class BaccaratGameLogic {
    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2){
        String player = "Player";                                           // strings
        String dealer = "Banker";
        String draw = "Draw";

        int hand1Val=0;                                                     // values to compare
        int hand2Val=0;

        if(hand1.size() == 2) {                                             // if the hand has 2 cards

            hand1Val = hand1.get(0).getValue() + hand1.get(1).getValue();   // add the values
            hand2Val = hand2.get(0).getValue() + hand2.get(1).getValue();

            hand1Val = hand1Val %10;
            hand2Val = hand2Val %10;

            if(hand1Val == 8 && hand2Val == 8)
                return draw;
            else if(hand1Val == 9 && hand2Val == 9)
                return draw;

            else if((hand1Val == 8 || hand1Val == 9) && (hand2Val == 8 || hand2Val == 9)){
                if(hand1Val > hand2Val){return player;}
                else return dealer;
            }

        }

        if(hand1.size() == 3 && hand2.size() == 3) {                                                  // if the hand has 3 cards
            hand1Val = hand1.get(0).getValue() + hand1.get(1).getValue() + hand1.get(2).getValue();
            hand2Val = hand2.get(0).getValue() + hand2.get(1).getValue() + hand2.get(2).getValue();
        }

        else if(hand1.size() == 3 && hand2.size() == 2) {                                             // if the hand has 3 cards
            hand1Val = hand1.get(0).getValue() + hand1.get(1).getValue() + hand1.get(2).getValue();
            hand2Val = hand2.get(0).getValue() + hand2.get(1).getValue();                             // and banker has 2 cards
        }

        else if(hand1.size() == 2 && hand2.size() == 3) {                                             // if the hand has 2 cards
            hand1Val = hand1.get(0).getValue() + hand1.get(1).getValue();
            hand2Val = hand2.get(0).getValue() + hand2.get(1).getValue() + hand2.get(1).getValue();   // banker has 3 cards
        }

        hand1Val = hand1Val% 10;                                            // get the reminder off 10
        hand2Val = hand2Val% 10;

        if(hand1Val > hand2Val){return player;}                             // if hand player > hand banker, player wins
        else if(hand1Val < hand2Val){return dealer;}                        // if hand banker > hand player, banker wins
        else {return draw;}                                                 // else its a draw

    }

    public int handTotal(ArrayList<Card> hand){
        int value = 0;                                                      // set value to return
        if(hand.size() == 2){value = hand.get(0).getValue() + hand.get(1).getValue();}
        if(hand.size() == 3){value = hand.get(0).getValue() + hand.get(1).getValue() + hand.get(2).getValue();}

        if(value == 0){System.out.println("Error handTotal: value = 0");}   // debug

        value = value% 10;                                            // get the reminder off 10

        return value;
    }

    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard){ // needs a fix
        if(hand.size() == 2) {
            if (handTotal(hand) >= 0 && handTotal(hand) < 3 && playerCard != null) {
                return true;
            } else if (handTotal(hand) == 3 && playerCard == null){return false;

            } else if (handTotal(hand) == 3 && playerCard.getValue()%10 >= 0 && playerCard.getValue()%10 < 8) {
                return true;
            } else if (handTotal(hand) == 3 && playerCard.getValue()%10 == 9) {
                return true;
            } else if (handTotal(hand) == 4 && playerCard == null){return false;

            } else if (handTotal(hand) == 4 && playerCard.getValue()%10 >= 2 && playerCard.getValue()%10 < 8) {
                return true;
            } else if (handTotal(hand) == 5 && playerCard == null){return false;

            } else if (handTotal(hand) == 5 && playerCard.getValue()%10 >= 4 && playerCard.getValue()%10 < 8) {
                return true;
            } else if (handTotal(hand) == 6 && playerCard == null){return false;

            } else if (handTotal(hand) == 6 && playerCard.getValue()%10 >= 6 && playerCard.getValue()%10 < 8) {
                return true;
            } else return false;
        }
        else
            return false;
    }

    public boolean evaluatePlayerDraw(ArrayList<Card> hand) {                // check if player needs a 3rd card
        if (hand.size() == 2) {
            if (handTotal(hand) <= 5) {
                return true;
            } else {
                return false;
            }
        }
        else return false;
    }

}
