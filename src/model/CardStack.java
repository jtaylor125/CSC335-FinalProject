package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardStack implements Iterable<Card> {

	private final List<Card> cards = new ArrayList<Card>();
	
	public void push(Card c){
		assert c != null && !cards.contains(c);
		cards.add(c);
	}
	
	public Card pop() {
		assert !isEmpty();
		return cards.remove(cards.size() - 1);
	}
	
	public Card peek() {
		assert !isEmpty();
		return cards.get(cards.size() - 1);
	}
	
	public void clear () {
		cards.clear();
	}
	
	public boolean isEmpty() {
		return cards.size() == 0;
	}
	
	public Iterator<Card> iterator() {
		return cards.iterator();
	}
	
}