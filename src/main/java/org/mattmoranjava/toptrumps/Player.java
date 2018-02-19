package org.mattmoranjava.toptrumps;

import static org.mattmoranjava.toptrumps.Constants.MAXCARDS;

public class Player {
    private Deck hand;
    private String name;
    public Player(String name){
        hand = new Deck();
        this.name = name;
    }
    public void receiveCard(Card card){
        hand.add(card);
    }
    public Card giveUpCard(){
        return hand.removeTop();
    }

    public String getName(){
        return name;
    }
    public Card declare(){
        return hand.showTop();
    }
    public Boolean hasWon(){
        return hand.getCards().size() == MAXCARDS;
    }
    public Boolean isOut(){
        return hand.getCards().size() == 0;
    }
    public void rotate(){
        hand.rotate();
    }

    public String toString(){
        return name + " " + hand;
    }
}
