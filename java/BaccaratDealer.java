import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;

public class BaccaratDealer {
    ArrayList<Card> deck;

    BaccaratDealer(){
        deck = new ArrayList<>(52);
    }

    public void generateDeck() {

        String[] theSuits = {"C", "S", "H", "D"};               // declare the suits of the cards
        int[] theRanks = {1,2,3,4,5,6,7,8,9,10,20,30,40};       // declare the ranks of the cards

        for(int i = 0; i < 4; i++){                                     // for all suits
            for(int j = 0; j < 13; j++){                                // for all ranks
                Card newCard = new Card(theSuits[i], theRanks[j]);      // add a new card with such suit and rank
                deck.add(newCard);                                      // add the card into the deck
            }
        }


        // clubs suit   (throws IOException)
        /*
        Card twoC = new Card("Clubs", 2, ImageIO.read(new File("src/main/resources/2C.png")), 1);
        Card threeC = new Card("Clubs", 3, ImageIO.read(new File("src/main/resources/3C.png")), 2);
        Card fourC = new Card("Clubs", 4, ImageIO.read(new File("src/main/resources/4C.png")), 3);
        Card fiveC = new Card("Clubs", 5, ImageIO.read(new File("src/main/resources/5C.png")), 4);
        Card sixC = new Card("Clubs", 6, ImageIO.read(new File("src/main/resources/6C.png")), 5);
        Card sevenC = new Card("Clubs", 7, ImageIO.read(new File("src/main/resources/7C.png")), 6);
        Card eightC = new Card("Clubs", 8, ImageIO.read(new File("src/main/resources/8C.png")), 7);
        Card nineC = new Card("Clubs", 9, ImageIO.read(new File("src/main/resources/9C.png")), 8);
        Card tenC = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/10C.png")), 9);
        Card jackC = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/JC.png")), 10);
        Card queenC = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/QC.png")), 11);
        Card kingC = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/KC.png")), 12);
        Card aceC = new Card("Clubs", 1, ImageIO.read(new File("src/main/resources/AC.png")), 13);

        // spades suit
        Card twoS = new Card("Clubs", 2, ImageIO.read(new File("src/main/resources/2S.png")), 14);
        Card threeS = new Card("Clubs", 3, ImageIO.read(new File("src/main/resources/3S.png")), 15);
        Card fourS = new Card("Clubs", 4, ImageIO.read(new File("src/main/resources/4S.png")), 16);
        Card fiveS = new Card("Clubs", 5, ImageIO.read(new File("src/main/resources/5S.png")), 17);
        Card sixS = new Card("Clubs", 6, ImageIO.read(new File("src/main/resources/6S.png")), 18);
        Card sevenS = new Card("Clubs", 7, ImageIO.read(new File("src/main/resources/7S.png")), 19);
        Card eightS = new Card("Clubs", 8, ImageIO.read(new File("src/main/resources/8S.png")), 20);
        Card nineS = new Card("Clubs", 9, ImageIO.read(new File("src/main/resources/9S.png")), 21);
        Card tenS = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/10S.png")), 22);
        Card jackS = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/JS.png")), 23);
        Card queenS = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/QS.png")), 24);
        Card kingS = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/KS.png")), 25);
        Card aceS = new Card("Clubs", 1, ImageIO.read(new File("src/main/resources/AS.png")), 26);

        // Hearts suit
        Card twoH = new Card("Clubs", 2, ImageIO.read(new File("src/main/resources/2H.png")), 27);
        Card threeH = new Card("Clubs", 3, ImageIO.read(new File("src/main/resources/3H.png")), 28);
        Card fourH = new Card("Clubs", 4, ImageIO.read(new File("src/main/resources/4H.png")), 29);
        Card fiveH = new Card("Clubs", 5, ImageIO.read(new File("src/main/resources/5H.png")), 30);
        Card sixH = new Card("Clubs", 6, ImageIO.read(new File("src/main/resources/6H.png")), 31);
        Card sevenH = new Card("Clubs", 7, ImageIO.read(new File("src/main/resources/7H.png")), 32);
        Card eightH = new Card("Clubs", 8, ImageIO.read(new File("src/main/resources/8H.png")), 33);
        Card nineH = new Card("Clubs", 9, ImageIO.read(new File("src/main/resources/9H.png")), 34);
        Card tenH = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/10H.png")), 35);
        Card jackH = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/JH.png")), 36);
        Card queenH = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/QH.png")), 37);
        Card kingH = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/KH.png")), 38);
        Card aceH = new Card("Clubs", 1, ImageIO.read(new File("src/main/resources/AH.png")), 39);

        // spades suit
        Card twoD = new Card("Clubs", 2, ImageIO.read(new File("src/main/resources/2D.png")), 40);
        Card threeD = new Card("Clubs", 3, ImageIO.read(new File("src/main/resources/3D.png")), 41);
        Card fourD = new Card("Clubs", 4, ImageIO.read(new File("src/main/resources/4D.png")), 42);
        Card fiveD = new Card("Clubs", 5, ImageIO.read(new File("src/main/resources/5D.png")), 43);
        Card sixD = new Card("Clubs", 6, ImageIO.read(new File("src/main/resources/6D.png")), 44);
        Card sevenD = new Card("Clubs", 7, ImageIO.read(new File("src/main/resources/7D.png")), 45);
        Card eightD = new Card("Clubs", 8, ImageIO.read(new File("src/main/resources/8D.png")), 46);
        Card nineD = new Card("Clubs", 9, ImageIO.read(new File("src/main/resources/9D.png")), 47);
        Card tenD = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/10D.png")),48);
        Card jackD = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/JD.png")), 49);
        Card queenD = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/QD.png")), 50);
        Card kingD = new Card("Clubs", 0, ImageIO.read(new File("src/main/resources/KD.png")), 51);
        Card aceD = new Card("Clubs", 1, ImageIO.read(new File("src/main/resources/AD.png")), 52);

        // store in order
        deck.add(twoC);deck.add(threeC);deck.add(fourC);deck.add(fiveC);deck.add(sixC);deck.add(sevenC);deck.add(eightC);
        deck.add(nineC);deck.add(tenC);deck.add(jackC);deck.add(queenC);deck.add(kingC);deck.add(aceC);

        deck.add(twoS);deck.add(threeS);deck.add(fourS);deck.add(fiveS);deck.add(sixS);deck.add(sevenS);deck.add(eightS);
        deck.add(nineS);deck.add(tenS);deck.add(jackS);deck.add(queenS);deck.add(kingS);deck.add(aceS);

        deck.add(twoH);deck.add(threeH);deck.add(fourH);deck.add(fiveH);deck.add(sixH);deck.add(sevenH);deck.add(eightH);
        deck.add(nineH);deck.add(tenH);deck.add(jackH);deck.add(queenH);deck.add(kingH);deck.add(aceH);

        deck.add(twoD);deck.add(threeD);deck.add(fourD);deck.add(fiveD);deck.add(sixD);deck.add(sevenD);deck.add(eightD);
        deck.add(nineD);deck.add(tenD);deck.add(jackD);deck.add(queenD);deck.add(kingD);deck.add(aceD);

         */
    }



    public ArrayList<Card> dealHand(){
        //Collections.shuffle(deck);
        ArrayList<Card> returnCard = new ArrayList<Card>();                     // create a return hand arraylist
        boolean done = false;                                                // checkpoint for the while loop
        //returnCard.clear();                                                  // clear the arraylist to return, useful when multiple hands are drawn
        if(deckSize() >= 6) {                                                // we can deal 2 hands if there's enough cards
            int counter = 0;                                                 // counter to count 2 cards
            System.out.println("Dealing cards!");
            while (!done) {                                                  // while we're not done

                Random rand = new Random();
                int randomNum = rand.nextInt(52);                     // create a random number                                     // create a random number
                //System.out.println(randomNum);                             // debug
                int backOfTheDeck2 = deckSize()-1;
                if (!deck.get(randomNum).used) {                             // if card is unused
                    deck.get(randomNum).setUsedTrue();                       // set the card is now used
                    //System.out.println("Random number: "+randomNum);       // debug
                    returnCard.add(deck.get(randomNum));                     // add the card to the hand
                    counter++;                                               // add the counter
                }
                if (counter == 2) {                                          // if we have 2 cards, we're done
                    done = true;
                }
            }
        }/*
        if(deckSize() >= 6) {
            returnCard.clear();
            returnCard.add(drawOne());
            returnCard.add(drawOne());
        }*/

        return returnCard;                                                   // return hand
    }

    public Card drawOne() {                                     // draw one card
        boolean done = false;
        System.out.println("Adding one card!");
        //int backOfTheDeck = deckSize()-1;                      // get cards from the back
        while(!done) {
            Random rand = new Random();                             // create a random number
            int randomNum  = rand.nextInt(52);
            if (!deck.get(randomNum).used) {                          // check if the chosen card has been used
                deck.get(randomNum).used = true;                    // set that it is now used
                done = true;
                return deck.get(randomNum);                         // return the new random unused card
            }
        }

            System.out.println("No card available");            // if there's no unused cards
            return null;

        /*/
        Card returnCard;
        int backOfTheDeck = deckSize()-1;                      // get cards from the back
        returnCard = deck.get(backOfTheDeck);
        deck.remove(backOfTheDeck);
        return returnCard;                                     // return the new random unused card

         */
    }

    public void shuffleDeck() {
        //deck.clear();                                           // remove all elements
        //generateDeck();                                         // re-make the deck
        Collections.shuffle(deck);                              // shuffle it
    }

    public int deckSize(){
        return deck.size();                                     // return size of deck
    }
}
