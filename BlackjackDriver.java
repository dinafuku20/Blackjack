// Blackjack by Dylan Inafuku, 2020
import java.util.Scanner;
import java.util.ArrayList;

public class BlackjackDriver
{
    public static void main(String[] args)
    {
        int value;
        ArrayList<Card> deck = new ArrayList<Card>(); // array list object
        // Loop from 1 to 13
        // Creates 52 card deck
        for (int i = 1; i < 14; i++)
        {
            if (i > 10)
            {
                value = 10;
            }
            else
            {
                value = i;
            }
            // Create a new Card with a suit, value, and rank
            // Add it to deck
            Card d = new Card("D", value, i);
            deck.add(d);
            Card s = new Card("S", value, i);
            deck.add(s);
            Card h = new Card("H", value, i);
            deck.add(h);
            Card c = new Card("C", value, i);
            deck.add(c);
        }
        // System.out.println(deck);
    }
}