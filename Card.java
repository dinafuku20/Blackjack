public class Card
{
    // Instance variables
    String suit; // D,S,C,H
    int rank; // 1-13
    int value; // 1-10
    // Constructors
    // Card with a suit, value, and rank
    public Card(String suit, int value, int rank)
    {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    // overrides toString method when printing an object
    public String toString()
    {
        return rank+""+suit+"";
    }
}