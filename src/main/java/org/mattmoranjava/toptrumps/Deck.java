package org.mattmoranjava.toptrumps;

import java.util.LinkedList;
import java.util.Random;

public class Deck {

    private LinkedList<Card> cards;
    public Deck(){
        cards = new LinkedList<>();
    }
    public Deck(int numberOfCards){
        cards = new LinkedList<>();
        Random rnd = new Random();
        int i = 0;
        do{
            cards.add(new Card(rnd.nextInt(100)));
            i++;
        } while (i < numberOfCards);
    }
    public void add(Card card){
        cards.add(card);
    }
    public Card removeTop(){
        return cards.removeFirst();
    }

    public Card showTop(){
        return cards.getFirst();
    }

    public Boolean hasCards(){
        return cards.size() > 0;
    }
    public LinkedList<Card> getCards() {
        return cards;
    }
    public void rotate(){
        cards.add(cards.removeFirst());
    }

    public String toString(){
        StringBuilder output = new StringBuilder();
        for (Card card: cards){
            output.append(card.attributeValue + " ");
        }
        return output.toString();
    }
}
