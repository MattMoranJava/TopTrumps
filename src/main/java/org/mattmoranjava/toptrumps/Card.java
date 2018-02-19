package org.mattmoranjava.toptrumps;

public class Card implements Comparable<Card>{
    int attributeValue;

    public Card(int attributeValue) {
        this.attributeValue = attributeValue;
    }

    public int compareTo(Card card) {
        return Integer.compare(card.attributeValue, attributeValue);
    }
}
