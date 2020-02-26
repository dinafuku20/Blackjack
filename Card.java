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
        // returns A,J,Q,K based on rank of card, else return the number of the rank
        if (rank == 1)
        {
            return "A"+suit;
        }
        if (rank == 11)
        {
            return "J"+suit;
        }
        if (rank == 12)
        {
            return "Q"+suit;
        }
        if (rank == 13)
        {
            return "K"+suit;
        }
        else
        {
            return rank+suit;
        }
    }
}