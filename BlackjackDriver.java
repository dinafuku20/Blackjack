// Blackjack by Dylan Inafuku, 2020
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class BlackjackDriver
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
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
        boolean handOver = false;
        boolean handDone = false;
        boolean doubleDown = false;
        String choice;

        // array list objects
        ArrayList<Card> deck = new ArrayList<Card>();
        ArrayList<Card> playerHand = new ArrayList<Card>();
        ArrayList<Card> dealerHand = new ArrayList<Card>();

        // create deck
        deck = createDeck(deck);
        System.out.println("Welcome to a game of Blackjack!");
        System.out.println("Rules and objective:");
        System.out.println("The goal of the game is to get as close to 21 as you can without going over.");
        System.out.println("You win if your total is higher than the dealer's total.");
        System.out.println("The dealer must draw until 17 and stand on 17-21.");
        System.out.println("Good luck!");
        System.out.println();


        // while player still has money, continue game
        while (!broke)
        {
            // create a new deck if there are <=15 cards in the deck
            if (deck.size() <= 20)
            {
                deck = createDeck(deck);
            }
            // reset these variables before the start of a hand
            handOver = false;
            hits = 0;
            playerTotal = 0;
            dealerTotal = 0;
            dealerHits = 0;
            handDone = false;
            // prompt user for bet amount and if it's greater than their money or <1, prompt again
            System.out.println(ANSI_GREEN + "Balance: $" + money + ANSI_RESET);
            System.out.print(ANSI_GREEN + "Bet: $");
            bet = kb.nextInt();
            while (bet > money || bet < 1)
            {
                System.out.println(ANSI_RED + "Please make a bet between 0 and " + money + "." + ANSI_RESET);
                System.out.print(ANSI_GREEN + "Bet: $");
                bet = kb.nextInt();
            }
            // subtract bet amount from money
            money -= bet;
            // get dealer hand, user hand, remove those four cards from the deck
            playerHand.add(deck.remove(0));
            dealerHand.add(deck.remove(0));
            playerHand.add(deck.remove(0));
            dealerHand.add(deck.remove(0));
            System.out.println(ANSI_RESET + "Dealer Hand: " + dealerHand.get(0) + " ??");
            System.out.println("Your Hand: " + playerHand.get(0) + " " + playerHand.get(1));
            playerTotal = handValue(playerHand);
            dealerTotal = handValue(dealerHand);
            // if player hasn't won or lost yet
            while (!handOver)
            {
                // prompt user to hit, stand, or double, if you've already hit you can't double down
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
                    System.out.println(ANSI_RED + "Invalid answer..." + ANSI_RESET);
                    System.out.println("Hit, Stand, or Double?");
                    choice = kb.next();
                }
                while (choice.equalsIgnoreCase("Double") && hits > 0)
                {
                    System.out.println(ANSI_RED + "Invalid answer..." + ANSI_RESET);
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
                // if player choooses to stand
                else if (choice.equalsIgnoreCase("Stand") || choice.equalsIgnoreCase("Double"))
                {
                    // if user doubles, draw card, add to total, subtract from money
                    if (choice.equalsIgnoreCase("Double") && hits == 0)
                    {
                        playerHand.add(deck.remove(0));
                        System.out.println("Your Hand: " + playerHand.get(0) + " " + playerHand.get(1) + " " + playerHand.get(2));
                        playerTotal = handValue(playerHand);
                        doubleDown = true;
                        money -= bet;
                    }
                    // handDone is a variable that tracks if the user's hand is handDone (user won't draw any more cards)
                    handDone = true;
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
                }
                //     // smart dealer, dealer hits to beat the player and doesn't hit if he's already won
                //     else if (dealerTotal > 17 && dealerTotal < playerTotal && dealerTotal < max)
                //     {
                //         // dealer hits until he's beaten the player, gotten 21, or he's lost
                //         if (dealerHits == 0)
                //         {
                //             dealerHits++;
                //             dealerHand.add(deck.remove(0));
                //             dealerTotal = handValue(dealerHand);
                //             System.out.println("Dealer Hits!");
                //             System.out.println("Dealer Hand: " + dealerHand.get(0) + " " + dealerHand.get(1) + " " + dealerHand.get(2));
                //         }
                //         if (dealerHits == 1 && dealerTotal < playerTotal && dealerTotal < max)
                //         {
                //             dealerHits++;
                //             dealerHand.add(deck.remove(0));
                //             dealerTotal = handValue(dealerHand);
                //             System.out.println("Dealer Hits!");
                //             System.out.println("Dealer Hand: " + dealerHand.get(0) + " " + dealerHand.get(1) + " " + dealerHand.get(2) + " " + dealerHand.get(3));
                //         }
                //         if (dealerHits == 2 && dealerTotal < playerTotal && dealerTotal < max)
                //         {
                //             dealerHits++;
                //             dealerHand.add(deck.remove(0));
                //             dealerTotal = handValue(dealerHand);
                //             System.out.println("Dealer Hits!");
                //             System.out.println("Dealer Hand: " + dealerHand.get(0) + " " + dealerHand.get(1) + " " + dealerHand.get(2) + " " + dealerHand.get(3) + " " + dealerHand.get(4));
                //         }
                //         if (dealerHits == 3 && dealerTotal < playerTotal && dealerTotal < max)
                //         {
                //             dealerHits++;
                //             dealerHand.add(deck.remove(0));
                //             dealerTotal = handValue(dealerHand);
                //             System.out.println("Dealer Hits!");
                //             System.out.println("Dealer Hand: " + dealerHand.get(0) + " " + dealerHand.get(1) + " " + dealerHand.get(2) + " " + dealerHand.get(3) + " " + dealerHand.get(4) + " " + dealerHand.get(5));
                //         }
                //     }
                // }
                // if you get a blackjack
                if (((playerHand.get(0).value + playerHand.get(1).value) == max) && playerTotal > dealerTotal && handDone)
                {
                    money += bet + bet*1.5;
                    handOver = true;
                }
                // if player goes over 21
                else if (playerTotal > max)
                {
                    System.out.println(ANSI_RED + "Bust!" + ANSI_RESET);
                    System.out.println("The dealer wins!");
                    handOver = true;
                }
                // if dealer goes over 21
                else if (dealerTotal > max)
                {
                    System.out.println(ANSI_GREEN + "The dealer busts!");
                    System.out.println("You win!" + ANSI_RESET);
                    money += bet*2;
                    handOver = true;
                }
                // if dealer beats the player and the player doesn't bust
                else if (dealerTotal > playerTotal && dealerTotal <= max && handDone)
                {
                    System.out.println(ANSI_RED + "The dealer wins!" + ANSI_RESET);
                    handOver = true;
                }
                // if dealer and player tie, push
                else if (dealerTotal == playerTotal && handDone)
                {
                    System.out.println(ANSI_RED + "Push!" + ANSI_RESET);
                    money += bet;
                    handOver = true;
                }
                // if player wins and doubled down
                else if (playerTotal > dealerTotal && playerTotal <= max && handDone && doubleDown)
                {
                    System.out.println(ANSI_GREEN + "You Win!" + ANSI_RESET);
                    money += bet*4;
                    handOver = true;
                }
                // if player wins
                else if (playerTotal > dealerTotal && playerTotal <= max && handDone)
                {
                    System.out.println(ANSI_GREEN + "You Win!" + ANSI_RESET);
                    money += bet*2;
                    handOver = true;
                }
            }
            // if you don't have any money left
            if (money == 0)
            {
                broke = true;
                System.out.println(ANSI_RED + "You're broke." + ANSI_RESET);
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
        // remove all cards from the deck
        for (int i = 0; i < size; i++)
        {
            deck.remove(0);
        }
        // create a new deck
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
        // iterate through hand to see if there is an ace
        for (int i = 0; i < hand.size(); i++)
        {
            // if there is an ace, make the value 11 and count amout of aces in hand
            if (hand.get(i).value == 1 || hand.get(i).value == 11)
            {
                hand.get(i).value = 11;
                ace++;
            }
            // add up value of hand
            sum += hand.get(i).value;
        }
        // if the user or dealer goes over 21 and has aces in hand, make the value of the aces 1
        if ((sum > 21) && (ace > 0))
        {
            // iterate through hand, if there's an ace make the value 1, add to sum
            for (int i = 0; i < hand.size(); i++)
            {
                if (hand.get(i).value == 11)
                {
                    hand.get(i).value = 1;
                    sum += hand.get(i).value;
                }
            }
            // subtract 11 per ace since the ace has a value off 1 now
            sum -= 11*ace;
        }
        return sum;
    }
    // empty hands
    public static void emptyHand(ArrayList<Card> hand)
    {
        // create size variable since arraylist grows and shrinks when removing
        int size = hand.size();
        // iterate through hand and remove all cards from hand
        for (int i = 0; i < size; i++)
        {
            hand.remove(0);
        }
    }
}