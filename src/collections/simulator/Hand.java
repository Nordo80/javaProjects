package collections.simulator;

import java.util.*;

public class Hand implements Iterable<Card>, Comparable<Hand> {

    private List<Card> cards = new ArrayList<>();
    public String previousCard = "";
    public int handtypeIndex = 0;
    public int priorityNumber = 0;

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public HandType getHandType() {
        Collections.sort(cards);
        for(Card card : cards){
            containsFourOfAKIND(card.getValue().toString());
            containsFullHouse(card.getValue().toString());
            containsFlush(card.getSuit().toString());
            containsStraight(card.getValue().ordinal());
            containsTrips(card.getValue().toString());
            containsTwoPairs(card.getValue().toString());
            containsOnePair(card.getValue().toString());
            containsStraightFlush();

        }
        return HandType.values()[handtypeIndex];
    }
    public void containsOnePair(String card){
        if(handtypeIndex < 1 ) {
            if (previousCard == card) {
                handtypeIndex = 1;
            }
            previousCard = card;
        }
    }
    public void containsTwoPairs(String card){
        if (handtypeIndex == 1  && priorityNumber <= 2){
            if(previousCard == card){
                handtypeIndex = 2;
                priorityNumber = 3;
            }
            previousCard = card;

        }
    }
    public int tripsCounter = 0;
    public void containsTrips(String card){
        if (handtypeIndex == 1 && priorityNumber <= 3){
            if(card == previousCard && tripsCounter == 0){
                handtypeIndex = 3;
                priorityNumber = 4;
            }else{
                tripsCounter = 1;
            }
        }
        else if(handtypeIndex == 2){
            if(card == previousCard){
                handtypeIndex = 3;
                priorityNumber = 4;
            }
        }
    }

    public boolean straightContains = false;
    public int previousIndex;
    public int straightCounter = 0;
    public void containsStraight(int index){
        if(index == 12 && previousIndex == 3){
            straightCounter += 1;
            previousIndex = index;
        }
        if(straightCounter == 0 || previousIndex + 1 == index){
            straightCounter += 1;
            previousIndex = index;
        }
        if (straightCounter == 5){
            straightContains = true;
            if(priorityNumber <= 4){
                handtypeIndex = 4;
                priorityNumber = 5;
            }
        }
    }


    public int flushCounter = 0;
    public String previousSuit;
    public boolean flashContains = false;
    public void containsFlush(String card){
        if (flushCounter == 0 || previousSuit == card){
            flushCounter += 1;
            previousSuit = card;
        }

        if(flushCounter == 5 && priorityNumber <= 5){
            handtypeIndex = 5;
            priorityNumber = 6;
            flashContains = true;
        }
    }

    public String fullHousePreviousCard = "";
    public void containsFullHouse(String card){
        if(priorityNumber <= 6){
            if (handtypeIndex == 3 && fullHousePreviousCard == card){
                handtypeIndex = 6;
                priorityNumber = 7;
            }else{
                fullHousePreviousCard = card;
            }
            if (handtypeIndex == 2 && card == previousCard){
                handtypeIndex = 6;
                priorityNumber = 7;
            }
        }
    }
    public void containsFourOfAKIND(String card){
        if (handtypeIndex == 3 && priorityNumber <= 7){
            if(card == previousCard){
                handtypeIndex = 7;
            }
        }
    }
    public void containsStraightFlush(){
        if(flashContains && straightContains){
            handtypeIndex = 8;
        }
    }
    
    public boolean contains(Card card) {
        return cards.contains(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public int compareTo(Hand other) {
        return 0;
    }
}
