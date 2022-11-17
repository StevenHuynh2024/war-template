
/**
 * War game class
 *
 * @author Mr. Jaffe
 * @version 2022-10-19
 */
public class War
{
    private Deck[] ds;
    private Deck CardsForGrab;
    /**
     * Constructor for the game
     * Include your initialization here -- card decks, shuffling, etc
     * Run the event loop after you've done the initializations
     */
    public War()
    {
        // Initializations here...
        Deck deck = new Deck();
        deck.initializeNewDeck();
        deck.shuffle();
        ds = deck.dealDeck();
        Deck d1 = ds[0];
        Deck d2 = ds[1];

        // ...then run the event loop
        this.runEventLoop(d1,d2);
    }

    /**
     * This is the game's event loop. The code in here should come
     * from the War flowchart you created for this game
     */
    public void runEventLoop(Deck d1, Deck d2) {
        while (d1.getDeckSize() > 0 && d2.getDeckSize() > 0) {
            Card p1 = d1.dealCardFromDeck();
            Card p2 = d2.dealCardFromDeck();
            System.out.println("Player 1's Top Card = " + p1.getFace() + " of " + p1.getSuit());
            System.out.println("Player 2's Top Card = " + p2.getFace() + " of " + p2.getSuit());
            System.out.println("Player 1's Deck Size = " + d1.getDeckSize());
            System.out.println("Player 2's Deck Size = " + d2.getDeckSize());
            if (p1.getRank() == p2.getRank()) {
                war(p1,p2,d1,d2);
            } else if (p1.getRank() > p2.getRank()) {
                d1.addCardToDeck(p1); // moves the first card to the back
                d1.addCardToDeck(p2);
            } else if (p2.getRank() > p1.getRank()) {
                d2.addCardToDeck(p2);
                d2.addCardToDeck(p1);
            }
        }
        if (d1.getDeckSize() > d2.getDeckSize()) {
            System.out.println("Player 1 wins");
        } else {
            System.out.println("Player 2 wins");
        }
    }

    public void war(Card p1, Card p2, Deck d1, Deck d2) {
        if (d1.getDeckSize() >= 4 && d2.getDeckSize() >= 4) {
            if (p1.getRank() == p2.getRank()) {
                System.out.println("War");
                for (int i =0; i<3; i++) {
                    CardsForGrab.addCardToDeck(d1.dealCardFromDeck());
                }
                for(int i = 0; i<3; i++) {
                    CardsForGrab.addCardToDeck(d2.dealCardFromDeck());
                }
                Card p1t = p1;
                Card p2t = p2;
                CardsForGrab.addCardToDeck(p1t);
                CardsForGrab.addCardToDeck(p2t);
                if (p1t.getRank() > p2t.getRank()) {
                    for (int i =0; i<CardsForGrab.getDeckSize(); i++) {
                        d1.addCardToDeck(CardsForGrab.dealCardFromDeck());
                    }
                } else if (p1t.getRank() < p2t.getRank()) {
                    for(int i = 0; i<CardsForGrab.getDeckSize(); i++) {
                        d2.addCardToDeck(CardsForGrab.dealCardFromDeck());
                    }
                } else if (p1t.getRank() == p2t.getRank()) {
                    war(p1t,p2t,d1,d2);
                }
            }
        } else if (d1.getDeckSize() < 4) {
            for (int i = 0; i<d1.getDeckSize(); i++) {
                d2.addCardToDeck(d1.dealCardFromDeck());
            }
        } else if (d2.getDeckSize() < 4) {
            for (int i = 0; i<d1.getDeckSize(); i++) {
                d1.addCardToDeck(d2.dealCardFromDeck());
            }
        }
    }

    /**
     * The main method is called when Java starts your program
     */
    public static void main(String[] args) {
        War war = new War();
    }

}
