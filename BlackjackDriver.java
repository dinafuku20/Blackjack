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

        System.out.println("Welcome to a game of Blackjack!");
        // Shuffles deck
        Collections.shuffle(deck);
        // while player still has money, continue game
        while (!broke)
        {
            win = false;
            lose = false;
            hits = 0;
            playerTotal = 0;
            dealerTotal = 0;
            dealerHits = 0;
            done = false;
            System.out.println("Balance: $" + money);
            System.out.print("Bet: $");
            bet = kb.nextInt();
            while (bet > money || bet < 1)
            {
                System.out.println("Please make a bet between 0 and " + money + ".");
                System.out.print("Bet: $");
                bet = kb.nextInt();
            }
            money -= bet;
            // get dealer hand, user hand, remove those four cards from the deck
            cardOne = deck.get(0);
            dealOne = deck.get(1);
            cardTwo = deck.get(2);
            dealTwo = deck.get(3);
            deck.remove(0);
            deck.remove(0);
            deck.remove(0);
            deck.remove(0);
            System.out.println("Dealer Hand: " + dealOne + " ??");
            System.out.println("Your Hand: " + cardOne + " " + cardTwo);
            // add first two cards to running player total
            playerTotal = cardOne.value + cardTwo.value;
            // add first two cards the dealer recieves to running dealer total
            dealerTotal = dealOne.value + dealTwo.value;
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
                        cardThree = deck.get(0);
                        deck.remove(0);
                        System.out.println("Your Hand: " + cardOne + " " + cardTwo + " " + cardThree);
                        playerTotal += cardThree.value;
                    }
                    // if player hit twice, get card, remove card from deck, add to running total
                    else if (hits == 2)
                    {
                        cardFour = deck.get(0);
                        deck.remove(0);
                        System.out.println("Your Hand: " + cardOne + " " + cardTwo + " " + cardThree + " " + cardFour);
                        playerTotal += cardFour.value;
                    }
                    // if player hit three times, get card, remove card from deck, add to running total
                    else if (hits == 3)
                    {
                        cardFive = deck.get(0);
                        deck.remove(0);
                        System.out.println("Your Hand: " + cardOne + " " + cardTwo + " " + cardThree + " " + cardFour + " " + cardFive);
                        playerTotal += cardFive.value;
                    }
                    // if player hit four times, get card, remove card from deck, add to running total
                    else if (hits == 4)
                    {
                        cardSix = deck.get(0);
                        deck.remove(0);
                        System.out.println("Your Hand: " + cardOne + " " + cardTwo + " " + cardThree + " " + cardFour + " " + cardFive + " " + cardSix);
                        playerTotal += cardSix.value;
                    }

                }
                else if (choice.equalsIgnoreCase("Stand") || choice.equalsIgnoreCase("Double"))
                {
                    if (choice.equalsIgnoreCase("Double") && hits == 0)
                    {
                        cardThree = deck.get(0);
                        deck.remove(0);
                        System.out.println("Your Hand: " + cardOne + " " + cardTwo + " " + cardThree);
                        playerTotal += cardThree.value;
                        doubleDown = true;
                        money -= bet;
                    }
                    done = true;
                    System.out.println("Dealer Hand: " + dealOne + " " + dealTwo);
                    // dealer stands on 17 and draws to 16
                    if (dealerTotal <= 16)
                    {
                        // dealer hits until total is greater than 16
                        dealerHits++;
                        dealThree = deck.get(0);
                        deck.remove(0);
                        System.out.println("Dealer Hits!");
                        System.out.println("Dealer Hand: " + dealOne + " " + dealTwo + " " + dealThree);
                        dealerTotal += dealThree.value;
                        if (dealerTotal <= 16)
                        {
                            dealerHits++;
                            dealFour = deck.get(0);
                            deck.remove(0);
                            dealerTotal += dealFour.value;
                            System.out.println("Dealer Hits!");
                            System.out.println("Dealer Hand: " + dealOne + " " + dealTwo + " " + dealThree + " " + dealFour);
                            if (dealerTotal <= 16)
                            {
                                dealerHits++;
                                dealFive = deck.get(0);
                                deck.remove(0);
                                dealerTotal += dealFive.value;
                                System.out.println("Dealer Hits!");
                                System.out.println("Dealer Hand: " + dealOne + " " + dealTwo + " " + dealThree + " " + dealFour + " " + dealFive);
                                if (dealerTotal <= 16)
                                {
                                    dealerHits++;
                                    dealSix = deck.get(0);
                                    deck.remove(0);
                                    dealerTotal += dealSix.value;
                                    System.out.println("Dealer Hits!");
                                    System.out.println("Dealer Hand: " + dealOne + " " + dealTwo + " " + dealThree + " " + dealFour + " " + dealFive + " " + dealSix);
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
                            dealThree = deck.get(0);
                            deck.remove(0);
                            dealerTotal += dealThree.value;
                            System.out.println("Dealer Hits!");
                            System.out.println("Dealer Hand: " + dealOne + " " + dealTwo + " " + dealThree);
                        }
                        if (dealerHits == 1 && dealerTotal < playerTotal && dealerTotal < max)
                        {
                            dealerHits++;
                            dealFour = deck.get(0);
                            deck.remove(0);
                            dealerTotal += dealFour.value;
                            System.out.println("Dealer Hits!");
                            System.out.println("Dealer Hand: " + dealOne + " " + dealTwo + " " + dealThree + " " + dealFour);
                        }
                        if (dealerHits == 2 && dealerTotal < playerTotal && dealerTotal < max)
                        {
                            dealerHits++;
                            dealFive = deck.get(0);
                            deck.remove(0);
                            dealerTotal += dealFive.value;
                            System.out.println("Dealer Hits!");
                            System.out.println("Dealer Hand: " + dealOne + " " + dealTwo + " " + dealThree + " " + dealFour + " " + dealFive);
                        }
                        if (dealerHits == 3 && dealerTotal < playerTotal && dealerTotal < max)
                        {
                            dealerHits++;
                            dealSix = deck.get(0);
                            deck.remove(0);
                            dealerTotal += dealSix.value;
                            System.out.println("Dealer Hits!");
                            System.out.println("Dealer Hand: " + dealOne + " " + dealTwo + " " + dealThree + " " + dealFour + " " + dealFive + " " + dealSix);
                        }
                    }
                }
                // if you get a blackjack
                if (((cardOne.value + cardTwo.value) == max) && playerTotal > dealerTotal && done)
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

                // reset cards if you've won or lost
                if (win || lose)
                {
                    cardOne = null;
                    cardTwo = null;
                    cardThree = null;
                    cardFour = null;
                    cardFive = null;
                    dealOne = null;
                    dealTwo = null;
                    dealOne = null;
                    dealTwo = null;
                    dealThree = null;
                    dealFour = null;
                    dealFive = null;
                    dealSix = null;
                }
                // if you don't have any money left
                if (money == 0)
                {
                    broke = true;
                    System.out.println("You're broke.");
                }
            }
        }
    }
}