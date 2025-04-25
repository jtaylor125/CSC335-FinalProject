/* Authors: Andy Zhang, Nathan Crossman, Jacob, Talyor
 * Course: CSC 335
 * Description: This interface is used to implement different strategies a computer
 * will use against a Player. Implements the Strategy Design pattern.
 */
package model;

import java.util.List;

public interface Strategy {
	public List<Card> chooseDiscards(List<Card> hand);
	
	public Card choosePeggingPlayCard(List<Card> playableCards, int runningTotal, List<Card> playedCards);

}
