// Blackjack by Dylan Inafuku, 2020
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class BlackjackDriver
{
    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        // variables
        int value;
        int playerTotal;
        int dealerTotal;
        int max = 21;
        int money = 5000;
        int bet;
        int hits = 0;
        int dealerHits = 0;
        boolean broke = false;
        boolean win;
        boolean lose;
        boolean done = false;
        boolean doubleDown = false;
        String choice;
        // user hand and dealer hand
        Card cardOne;
        Card cardTwo;
        Card cardThree = null;
        Card cardFour = null;
        Card cardFive = null;
        Card cardSix = null;
        Card dealOne;
        Card dealTwo;
        Card dealThree = null;
        Card dealFour = null;
        Card dealFive = null;
        Card dealSix = null;
        // array list objects
        ArrayList<Card> deck = new ArrayList<Card>();
        ArrayList<Card> playerHand = new ArrayList<Card>();
        ArrayList<Card> dealerHand = new ArrayList<Card>();
        deck = createDeck(deck);

        System.out.println("Welcome to a game of Blackjack!");
        // Shuffles deck
        // Collections.shuffle(deck);
        // while player still has money, continue game
        while (!broke)
        {
            // create a new deck if there are <=15 cards in the deck
            if (deck.size() <= 20)
            {
                deck = createDeck(deck);
            }
            // reset these variables before the start of a hand
            win = false;
            lose = false;
            hits = 0;
            playerTotal = 0;
            dealerTotal = 0;
            dealerHits = 0;
            done = false;
            // prompt user for bet amount and if it's greater than their money or <1, prompt again
            System.out.println("Balance: $" + money);
            System.out.print("Bet: $");
            bet = kb.nextInt();
            while (bet > money || bet < 1)
            {
                System.out.println("Please make a bet between 0 and " + money + ".");
                System.out.print("Bet: $");
                bet = kb.nextInt();
            }
            // subtract bet amonunt from money
            money -= bet;
            // get dealer hand, user hand, remove those four cards from the deck
            playerHand.add(deck.remove(0));
            dealerHand.add(deck.remove(0));
            playerHand.add(deck.remove(0));
            dealerHand.add(deck.remove(0));
            System.out.println("Dealer Hand: " + dealerHand.get(0) + " ??");
            System.out.println("Your Hand: " + playerHand.get(0) + " " + playerHand.get(1));
            playerTotal = handValue(playerHand);
            dealerTotal = handValue(dealerHand);
            // if player hasn't won or lost yet
            while (!win && !lose)
            {
                if (hits == 0)
                {
                    System.out.println("Hit, Stand, or Double?");
                }
                else
                {
                    System.out.println("Hit or Stand?");
                }
                choice = kb.next();
                while (!choice.equalsIgnoreCase("Hit") && !choice.equalsIgnoreCase("Stand") && !choice.equalsIgnoreCase("Double"))
                {
                    System.out.println("Invalid answer...");
                    System.out.println("Hit, Stand, or Double?");
                    choice = kb.next();
                }
                while (choice.equalsIgnoreCase("Double") && hits > 0)
                {
                    System.out.println("Invalid answer...");
                    System.out.println("Hit or Stand?");
                    choice = kb.next();
                }
                if (choice.equalsIgnoreCase("Hit"))
                {
                    // count amount of times player hits
                    hits++;
                    // if player hit once, get card, remove card from deck, add to running total
                    if (hits == 1)
                    {
                        playerHand.add(deck.remove(0));
                        System.out.println("Your Hand: " + playerHand.get(0) + " " + playerHand.get(1) + " " + playerHand.get(2));
                        playerTotal = handValue(playerHand);
                    }
                    // if player hit twice, get card, remove card from deck, add to running total
                    else if (hits == 2)
                    {
                        playerHand.add(deck.remove(0));
                        System.out.println("Your Hand: " + playerHand.get(0) + " " + playerHand.get(1) + " " + playerHand.get(2) + " " + playerHand.get(3));
                        playerTotal = handValue(playerHand);
                    }
                    // if player hit three times, get card, remove card from deck, add to running total
                    else if (hits == 3)
                    {
                        playerHand.add(deck.remove(0));
                        System.out.println("Your Hand: " + playerHand.get(0) + " " + playerHand.get(1) + " " + playerHand.get(2) + " " + playerHand.get(3) + " " + playerHand.get(4));
                        playerTotal = handValue(playerHand);
                    }
                    // if player hit four times, get card, remove card from deck, add to running total
                    else if (hits == 4)
                    {
                        playerHand.add(deck.remove(0));
                        System.out.println("Your Hand: " + playerHand.get(0) + " " + playerHand.get(1) + " " + playerHand.get(2) + " " + playerHand.get(3) + " " + playerHand.get(4) + " " + playerHand.get(5));
                        playerTotal = handValue(playerHand);
                    }

                }
                else if (choice.equalsIgnoreCase("Stand") || choice.equalsIgnoreCase("Double"))
                {
                    if (choice.equalsIgnoreCase("Double") && hits == 0)
                    {
                        playerHand.add(deck.remove(0));
                        System.out.println("Your Hand: " + playerHand.get(0) + " " + playerHand.get(1) + " " + playerHand.get(2));
                        playerTotal = handValue(playerHand);
                        doubleDown = true;
                        money -= bet;
                    }
                    done = true;
                    System.out.println("Dealer Hand: " + dealerHand.get(0) + " " + dealerHand.get(1));
                    // dealer stands on 17 and draws to 16
                    if (dealerTotal <= 16)
                    {
                        // dealer hits until total is greater than 16
                        dealerHits++;
                        dealerHand.add(deck.remove(0));
                        dealerTotal = handValue(dealerHand);
                        System.out.println("Dealer Hits!");
                        System.out.println("Dealer Hand: " + dealerHand.get(0) + " " + dealerHand.get(1) + " " + dealerHand.get(2));
                        if (dealerTotal <= 16)
                        {
                            dealerHits++;
                            dealerHand.add(deck.remove(0));
                            dealerTotal = handValue(dealerHand);
                            System.out.println("Dealer Hits!");
                            System.out.println("Dealer Hand: " + dealerHand.get(0) + " " + dealerHand.get(1) + " " + dealerHand.get(2) + " " + dealerHand.get(3));
                            if (dealerTotal <= 16)
                            {
                                dealerHits++;
                                dealerHand.add(deck.remove(0));
                                dealerTotal = handValue(dealerHand);
                                System.out.println("Dealer Hits!");
                                System.out.println("Dealer Hand: " + dealerHand.get(0) + " " + dealerHand.get(1) + " " + dealerHand.get(2) + " " + dealerHand.get(3) + " " + dealerHand.get(4));
                                if (dealerTotal <= 16)
                                {
                                    dealerHits++;
                                    dealerHand.add(deck.remove(0));
                                    dealerTotal = handValue(dealerHand);
                                    System.out.println("Dealer Hits!");
                                    System.out.println("Dealer Hand: " + dealerHand.get(0) + " " + dealerHand.get(1) + " " + dealerHand.get(2) + " " + dealerHand.get(3) + " " + dealerHand.get(4) + " " + dealerHand.get(5));
                                }
                            }
                        }
                    }
                    // smart dealer, dealer hits to beat the player and doesn't hit if he's already won
                    else if (dealerTotal > 17 && dealerTotal < playerTotal && dealerTotal < max)
                    {
                        // dealer hits until he's beaten the player, gotten 21, or he's lost
                        if (dealerHits == 0)
                        {
                            dealerHits++;
                            dealerHand.add(deck.remove(0));
                            dealerTotal = handValue(dealerHand);
                            System.out.println("Dealer Hits!");
                            System.out.println("Dealer Hand: " + dealerHand.get(0) + " " + dealerHand.get(1) + " " + dealerHand.get(2));
                        }
                        if (dealerHits == 1 && dealerTotal < playerTotal && dealerTotal < max)
                        {
                            dealerHits++;
                            dealerHand.add(deck.remove(0));
                            dealerTotal = handValue(dealerHand);
                            System.out.println("Dealer Hits!");
                            System.out.println("Dealer Hand: " + dealerHand.get(0) + " " + dealerHand.get(1) + " " + dealerHand.get(2) + " " + dealerHand.get(3));
                        }
                        if (dealerHits == 2 && dealerTotal < playerTotal && dealerTotal < max)
                        {
                            dealerHits++;
                            dealerHand.add(deck.remove(0));
                            dealerTotal = handValue(dealerHand);
                            System.out.println("Dealer Hits!");
                            System.out.println("Dealer Hand: " + dealerHand.get(0) + " " + dealerHand.get(1) + " " + dealerHand.get(2) + " " + dealerHand.get(3) + " " + dealerHand.get(4));
                        }
                        if (dealerHits == 3 && dealerTotal < playerTotal && dealerTotal < max)
                        {
                            dealerHits++;
                            dealerHand.add(deck.remove(0));
                            dealerTotal = handValue(dealerHand);
                            System.out.println("Dealer Hits!");
                            System.out.println("Dealer Hand: " + dealerHand.get(0) + " " + dealerHand.get(1) + " " + dealerHand.get(2) + " " + dealerHand.get(3) + " " + dealerHand.get(4) + " " + dealerHand.get(5));
                        }
                    }
                }
                System.out.println(playerTotal);
                System.out.println(dealerTotal);
                // if you get a blackjack
                if (((playerHand.get(0).value + playerHand.get(1).value) == max) && playerTotal > dealerTotal && done)
                {
                    money += bet*2;
                    win = true;
                }
                // if player goes over 21
                else if (playerTotal > max)
                {
                    System.out.println("Bust!");
                    System.out.println("The dealer wins!");
                    lose = true;
                }
                else if (dealerTotal > max)
                {
                    System.out.println("The dealer busts!");
                    System.out.println("You win!");
                    money += bet*2;
                    win = true;
                }
                // if dealer beats the player and the player doesn't bust
                else if (dealerTotal > playerTotal && dealerTotal <= max && done)
                {
                    System.out.println("The dealer wins!");
                    lose = true;
                }
                else if (dealerTotal == playerTotal && done)
                {
                    System.out.println("Push!");
                    money += bet;
                    lose = true;
                }
                else if (playerTotal > dealerTotal && playerTotal <= max && done && doubleDown)
                {
                    System.out.println("You Win!");
                    money += bet*4;
                    win = true;
                }
                else if (playerTotal > dealerTotal && playerTotal <= max && done)
                {
                    System.out.println("You Win!");
                    money += bet*2;
                    win = true;
                }

                // if you don't have any money left
                if (money == 0)
                {
                    broke = true;
                    System.out.println("You're broke.");
                }
            }
            // remove player and dealer hand after hand is complete
            emptyHand(playerHand);
            emptyHand(dealerHand);
        }
    }
    // creates a new deck, shuffles cards, returns the array list of cards
    public static ArrayList<Card> createDeck(ArrayList<Card> deck)
    {
        int value;
        int size = deck.size();
        ArrayList<Card> deckTwo = new ArrayList<Card>();
        for (int i = 0; i < size; i++)
        {
            deck.remove(0);
        }
        for (int i = 1; i < 14; i++)
        {
            // Loop from 1 to 13
            // Creates 52 card deck
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
            deckTwo.add(d);
            Card s = new Card("S", value, i);
            deckTwo.add(s);
            Card h = new Card("H", value, i);
            deckTwo.add(h);
            Card c = new Card("C", value, i);
            deckTwo.add(c);
        }
        // shuffle deck
        Collections.shuffle(deckTwo);
        return deckTwo;
    }
    // returns player hand value from dealer or player
    public static int handValue(ArrayList<Card> hand)
    {
        int sum = 0;
        int ace = 0;
        for (int i = 0; i < hand.size(); i++)
        {
            if (hand.get(i).value == 1 || hand.get(i).value == 11)
            {
                hand.get(i).value = 11;
                ace++;
            }
            sum += hand.get(i).value;
        }
        if ((sum > 21) && (ace > 0))
        {
            for (int i = 0; i < hand.size(); i++)
            {
                if (hand.get(i).value == 11)
                {
                    hand.get(i).value = 1;
                    sum += hand.get(i).value;
                }
            }
            sum -= 11*ace;
        }
        return sum;
    }
    // empty hands
    public static void emptyHand(ArrayList<Card> hand)
    {
        int size = hand.size();
        for (int i = 0; i < size; i++)
        {
            hand.remove(0);
        }
    }
}
