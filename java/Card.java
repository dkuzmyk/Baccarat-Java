import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;

public class Card {                             // class to create a card
    private String suite;                       // value of the card
    private int value;                          // suite of the card
    //private BufferedImage cardImage;            // image of the card
    public boolean used = false;               // is already used in the game?
    //private int number;                         // number of the card

    public Card(String theSuite, int theValue){
        value = theValue;
        suite = theSuite;
        used = false;
    }

    /*public Card(String suite, int value, BufferedImage card, int number){   // constructor
        this.suite = suite;
        this.value = value;
        this.cardImage = card;
        this.used = false;
        this.number = number;
    }*/

    // getter methods
    public int getValue() {                     // basic getter for value
        return value;
    }

    /*public BufferedImage getCardImage(){        // basic getter for the image
        return cardImage;
    }*/

    public String getSuite(){                   // basic getter for the suite
        return suite;
    }

    public void setUsedTrue(){used = true;}

    public void setUsedFalse(){used = false;}

}
