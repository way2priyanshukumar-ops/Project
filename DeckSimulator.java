import java.util.*;

enum Suit {
    HEARTS, DIAMONDS, SPADES, CLUBS
}

enum Rank {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
    JACK, QUEEN, KING, ACE
}


class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() { return suit; }
    public Rank getRank() { return rank; }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}


class Deck {
    private List<Card> cards;

    public Deck() {
        initializeDeck();
    }

    public void initializeDeck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        System.out.println(" Deck initialized with 52 cards.");
    }

    public void printDeck() {
        if (cards.isEmpty()) {
            System.out.println(" Deck is empty!");
        } else {
            System.out.println(" Cards in the deck:");
            for (Card card : cards) {
                System.out.println("  - " + card);
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
        System.out.println(" Deck shuffled.");
    }

    public void sortDeck() {
        cards.sort(Comparator
            .comparing(Card::getSuit)
            .thenComparing(Card::getRank));
        System.out.println(" Deck sorted by suit and rank.");
    }

    public void drawCard() {
        if (cards.isEmpty()) {
            System.out.println(" No cards left to draw.");
            return;
        }
        Random rand = new Random();
        int index = rand.nextInt(cards.size());
        Card drawnCard = cards.remove(index);
        System.out.println(" You drew: " + drawnCard);
    }

    public void drawMultipleCards(int count) {
        if (count > cards.size()) {
            System.out.println(" Not enough cards. Only " + cards.size() + " left.");
            return;
        }

        System.out.println(" Drawing " + count + " card(s):");
        for (int i = 0; i < count; i++) {
            drawCard();
        }
    }

    public void resetDeck() {
        initializeDeck();
    }

    public int remainingCards() {
        return cards.size();
    }
}

public class DeckSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();

        System.out.println("\n Welcome to Deck of Cards Simulator!");
        String choice;

        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Print Deck");
            System.out.println("2. Shuffle Deck");
            System.out.println("3. Sort Deck");
            System.out.println("4. Draw a Random Card");
            System.out.println("5. Draw Multiple Cards");
            System.out.println("6. Reset Deck");
            System.out.println("7. Show Remaining Cards");
            System.out.println("0. Exit");

            System.out.print(" Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    deck.printDeck();
                    break;
                case "2":
                    deck.shuffleDeck();
                    break;
                case "3":
                    deck.sortDeck();
                    break;
                case "4":
                    deck.drawCard();
                    break;
                case "5":
                    System.out.print("How many cards to draw? ");
                    int count = Integer.parseInt(scanner.nextLine());
                    deck.drawMultipleCards(count);
                    break;
                case "6":
                    deck.resetDeck();
                    break;
                case "7":
                    System.out.println(" Remaining cards: " + deck.remainingCards());
                    break;
                case "0":
                    System.out.println(" Exiting Deck Simulator. Goodbye!");
                    break;
                default:
                    System.out.println(" Invalid choice. Try again.");
            }

        } while (!choice.equals("0"));

        scanner.close();
    }
}
