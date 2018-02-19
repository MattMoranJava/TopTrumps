package org.mattmoranjava.toptrumps;

import java.util.*;

import static org.mattmoranjava.toptrumps.Constants.MAXCARDS;

public class Game {
    public static void main(String[] args) {
        Game myGame = new Game();
        myGame.run();
    }

    private void run() {
        /*
            Setup phase - create 3 Players
            Set up the Deck of 40 cards with random attribute values between 0 and 100
            Deal cards from the Deck to each Player in turn until all are gone
         */
        List<Player> players = new ArrayList<>();
        players.add(new Player("Jim"));
        players.add(new Player("Bob"));
        players.add(new Player("Aloysius"));

        Deck deck = new Deck(MAXCARDS);
        while (deck.hasCards()) {
            players.get(0).receiveCard(deck.removeTop());
            Collections.rotate(players, 1);
        }

        /*
            Play phase. Start with Player 0. Each player declares their card's value.
            The cards that are lower in value go to the player with the top value.
         */
        boolean gameWon = false;
        while (!gameWon) {
            HashMap<Card, Player> cardsThisHand = new HashMap<>();
            Player winner;
            System.out.println();
            System.out.println("New hand. Player " + players.get(0).getName() + " to start...");
            for (Player player : players) {
                cardsThisHand.put(player.declare(), player);
                System.out.println("Player "+player.getName()+ " has a card of value "+player.declare().attributeValue);
            }

/*
        Find out who has the biggest card value
*/
            List<Card> myList = new ArrayList<>(cardsThisHand.keySet());
            Collections.sort(myList);
            winner = cardsThisHand.get(myList.get(0));
            System.out.println("Player "+winner.getName()+" wins this round.");
            int winnerindex = players.indexOf(winner);
            // Rotate the winner to the head of the players collection
            if (winnerindex > 0) Collections.rotate(players, -1 * winnerindex );

            // Give the losers' losing cards to the winner
            for (int i = 1; i < players.size(); i++) {
                players.get(0).receiveCard(players.get(i).giveUpCard());

            }
            // Put the winning card on the bottom of the winning player's hand.
            players.get(0).rotate();

            //Now check for player out and player won conditions
            //We'll use an Iterator to avoid ConcurrentModificationExceptions
            Iterator<Player> playerIter = players.iterator();
            while(playerIter.hasNext()) {
                Player player = playerIter.next();
                System.out.println(player);

                if (player.isOut()){
                    System.out.println("OH NOES - Player "+player.getName()+ " IS OUT! Bye-bye loser...");
                    System.out.println("...");
                    playerIter.remove();
                }
                if (player.hasWon()) {
                    gameWon = true;
                    System.out.println("*******************************************************************");
                    System.out.println("Player " + player.getName() + " has won. Game over, man, GAME OVER!");
                    System.out.println("*******************************************************************");
                    break;
                }
            }
        }
    }
}
