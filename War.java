/**
 * War game class
 *
 * @author Mr. Jaffe
 * @version 2022-10-19
 */
public class War
{
    Deck CardsForGrab = new Deck();
    /**
     * Constructor for the game
     * Include your initialization here -- card decks, shuffling, etc
     * Run the event loop after you've done the initializations
     */
    public War()
    {
        // Initializations here...
        Deck[] ds;
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
        int round = 1;
        Card p1;
        Card p2;
        while (d1.getDeckSize() > 0 && d2.getDeckSize() > 0 && round < 300) {
            p1 = d1.dealCardFromDeck();
            p2 = d2.dealCardFromDeck();
            System.out.println("Round: " + round);
            System.out.println("Player 1's Top Card = " + p1.getFace() + " of " + p1.getSuit());
            System.out.println("Player 2's Top Card = " + p2.getFace() + " of " + p2.getSuit());
            System.out.println("Player 1's Deck Size = " + d1.getDeckSize());
            System.out.println("Player 2's Deck Size = " + d2.getDeckSize());
            if (p1.getRank() == p2.getRank()) {
                war(d1,d2,p1,p2);
            } else if (p1.getRank() > p2.getRank()) {
                d1.addCardToDeck(p1); // moves the first card to the back
                d1.addCardToDeck(p2);
            } else if (p2.getRank() > p1.getRank()) {
                d2.addCardToDeck(p2);
                d2.addCardToDeck(p1);
            }
            round++;
        }
        if (d1.getDeckSize() > d2.getDeckSize() || d2.getDeckSize() == 0) {
            System.out.println("Player 1 wins");
        } else if (d2.getDeckSize() > d1.getDeckSize() || d1.getDeckSize() == 0) {
            System.out.println("Player 2 wins");
        }
    }

    public void war(Deck d1, Deck d2, Card p1, Card p2) {
        System.out.println("War");
        
        if (d1.getDeckSize() >= 4 && d2.getDeckSize() >= 4) {
            CardsForGrab.addCardToDeck(p1);
            CardsForGrab.addCardToDeck(p2);
            for (int i =0; i<3; i++) {
                CardsForGrab.addCardToDeck(d1.dealCardFromDeck());
            }
            for(int i = 0; i<3; i++) {
                CardsForGrab.addCardToDeck(d2.dealCardFromDeck());
            }
            Card p1t = d1.dealCardFromDeck();
            Card p2t = d2.dealCardFromDeck();
            CardsForGrab.addCardToDeck(p1t);
            CardsForGrab.addCardToDeck(p2t);
            System.out.println("Player 1 top card = " + p1t.getFace() + " of " + p1t.getSuit());
            System.out.println("Player 2 top card = " + p2t.getFace() + " of " + p2t.getSuit());
            int size;
            if (p1t.getRank() == p2t.getRank()) {
                war2(d1,d2,p1t,p2t);
            }else if (p1t.getRank() > p2t.getRank()) {
                System.out.println("Player 1 Wins War");
                size = CardsForGrab.getDeckSize();
                for (int i =0; i< size; i++) {
                    d1.addCardToDeck(CardsForGrab.dealCardFromDeck());
                }
                System.out.println("Player 1's Deck Size = " + d1.getDeckSize());
                System.out.println("Player 2's Deck Size = " + d2.getDeckSize());
            } else if (p1t.getRank() < p2t.getRank()) {
                System.out.println("Player 2 Wins War");
                size = CardsForGrab.getDeckSize();
                for(int i = 0; i<size; i++) {
                    d2.addCardToDeck(CardsForGrab.dealCardFromDeck());
                }
                System.out.println("Player 1's Deck Size = " + d1.getDeckSize());
                System.out.println("Player 2's Deck Size = " + d2.getDeckSize());
            } 

        } else if (d2.getDeckSize() > 4) {
            //System.out.println("Player 1's Deck Size = " + d1.getDeckSize());
            //System.out.println("Player 2's Deck Size = " + d2.getDeckSize());
            //System.out.println("Not enough cards for War, Player 2 Wins");
            int test = d1.getDeckSize();
            for (int i =0; i<test; i++) {
                d2.addCardToDeck(d1.dealCardFromDeck());
            }
            //System.out.println("Player 1 Does Not Have Enough Cards For War");
        } else {
            //System.out.println("Player 1's Deck Size = " + d1.getDeckSize());
            //System.out.println("Player 2's Deck Size = " + d2.getDeckSize());
            //System.out.println("Not enough cards for War, Player 1 Wins");
            int test = d2.getDeckSize();
            for (int i =0; i<test; i++) {
                d1.addCardToDeck(d2.dealCardFromDeck());
            }
            //System.out.println("Player 2 Does Not Have Enough Cards For War");
        }
    }
    
    public void war2(Deck d1, Deck d2, Card p1, Card p2) {
        System.out.println("War");
        
        if (d1.getDeckSize() >= 4 && d2.getDeckSize() >= 4) {
            for (int i =0; i<3; i++) {
                CardsForGrab.addCardToDeck(d1.dealCardFromDeck());
            }
            for(int i = 0; i<3; i++) {
                CardsForGrab.addCardToDeck(d2.dealCardFromDeck());
            }
            Card p1t = d1.dealCardFromDeck();
            Card p2t = d2.dealCardFromDeck();
            CardsForGrab.addCardToDeck(p1t);
            CardsForGrab.addCardToDeck(p2t);
            System.out.println("Player 1 top card = " + p1t.getFace() + " of " + p1t.getSuit());
            System.out.println("Player 2 top card = " + p2t.getFace() + " of " + p2t.getSuit());
            int size;
            if (p1t.getRank() == p2t.getRank()) {
                war(d1,d2,p1,p2);
            }else if (p1t.getRank() > p2t.getRank()) {
                System.out.println("Player 1 Wins War");
                size = CardsForGrab.getDeckSize();
                for (int i =0; i< size; i++) {
                    d1.addCardToDeck(CardsForGrab.dealCardFromDeck());
                }
                System.out.println("Player 1's Deck Size = " + d1.getDeckSize());
                System.out.println("Player 2's Deck Size = " + d2.getDeckSize());
            } else if (p1t.getRank() < p2t.getRank()) {
                System.out.println("Player 2 Wins War");
                size = CardsForGrab.getDeckSize();
                for(int i = 0; i<size; i++) {
                    d2.addCardToDeck(CardsForGrab.dealCardFromDeck());
                }
                System.out.println("Player 1's Deck Size = " + d1.getDeckSize());
                System.out.println("Player 2's Deck Size = " + d2.getDeckSize());
            } 

        } else if (d2.getDeckSize() > 4) {
            //System.out.println("Player 1's Deck Size = " + d1.getDeckSize());
            //System.out.println("Player 2's Deck Size = " + d2.getDeckSize());
            //System.out.println("Not enough cards for War, Player 2 Wins");
            int test = d1.getDeckSize();
            for (int i =0; i<test; i++) {
                d2.addCardToDeck(d1.dealCardFromDeck());
            }
            //System.out.println("Player 1 Does Not Have Enough Cards For War");
        } else {
            //System.out.println("Player 1's Deck Size = " + d1.getDeckSize());
            //System.out.println("Player 2's Deck Size = " + d2.getDeckSize());
            //System.out.println("Not enough cards for War, Player 1 Wins");
            int test = d2.getDeckSize();
            for (int i =0; i<test; i++) {
                d1.addCardToDeck(d2.dealCardFromDeck());
            }
            //System.out.println("Player 2 Does Not Have Enough Cards For War");
        }
    }

    /**
     * The main method is called when Java starts your program
     */
    public static void main(String[] args) {
        War war = new War();
    }

}
