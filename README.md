# Blackjack
GitHub Repository Link: https://github.com/dinafuku20/Blackjack

Product:
The purpose of my Java mini-project is to create a game of blackjack between a dealer and a player. 
The player will be able to hit, stand, double, and bet.
The dealer will hit until 16 and stand on 17.

Functionality:
When the game starts an array list is initialized for the deck, the user's hand, and the dealer's hand. 
Next, the function createDeck is called on to create an array list of cards, each card having a suit, rank, and value to differentiate between face cards and number cards.
The user is greeted and the game checks to see if the player is broke or not. If the player is broke the game ends (i.e. money == 0)
Before the user inputs a bet amount, the game checks to see how many cards are left in the deck. To prevent the game from picking objects out of the index of the array list, I create a new deck every time the deck only has 20 cards left.
Once the player gives a bet amount, cards are removed from the deck and two cards are placed in the dealer and player's hands/array lists.
Each time the player or dealer recieve cards, the game stores a running total of the value of each hand. Checking to see if the player or dealer busts after each hit.
The game first checks if the game/hand is over, if it's not over the game proceeds to prompt user to stand, hit, or double
The user can't double if they have already hit and they can't hit if they have already doubled.
Each time the user or dealer hits a card is removed from the deck in placed and the respective array list.
Once the player stands or doubles, it's the dealers turn to hit.
The dealer must stand on 17 and hit up to 16.
The dealer follows these rules and hits, removing a card from the deck and adding it to the dealer hand array list.
After the dealer finishes, the game checks to see if the player and dealer won/lost, and how they won/lost.
The user is prompted that they've busted, won, the dealer busted, etc.
The game checks to see how much money the player has, and quits the game if they have no money left.
Once the hand is complete, the user and dealer's hands are emptied using the emptyHand() function. (basically just removing each object from the array lists)
Repeat.

Process:
One a-ha moment that I've had, was when I was trying to figure out a way to differentiate face cards from numbered cards.
Numbered cards are from 2-10 but their values are also 2-10. Whereas face cards (J,Q,K excluding the Ace for now) all have a value of 10.
Mr. Kiang helped me realize that I could use a third variable called rank apart from suit and value which would number J,Q, and K and 11,12, and 13.
This way I could differentiate between the face cards and numbered cards.

Another a-ha moment that I've had, was when I was trying to figure out how to make the Ace worth 1 and 11. 
At first I felt that it would be inefficient to prompt the user each time if they would want their Ace to be worth 1 or 11.
So, I worked around this by making the Ace worth 11 by default and changing the value of the Ace based on the value of the hand.
For instance, if the player's hand has a value of 22 and the ace is worth 11, the game would changed the value of the ace to be 1. Therefore, the value of the player's hand would be 12 instead.

A third a-ha moment that I've had, was when I was trying to print the letters A,J,Q,K instead of 1,11,12,13.
Mr. Kiang mentioned overriding the toString() method to print the letters whenever the game wanted to print the rank.
So, whenever the rank of the card being displayed was either, 1,11,12,or 13, the game would now print A,J,Q,K and the respective suits.

