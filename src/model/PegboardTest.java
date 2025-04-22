package model;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
public class PegboardTest {
	@Test
	void testPegboard() {
		Pegboard p = new Pegboard();
	}
	
	@Test
	void addPlayer() {
		Pegboard peg = new Pegboard();
		
		Player p = new Player();
		
		peg.addPlayer(p);
		
		assertEquals(0,peg.getScore(p));
	}
	
	@Test
	void addScores() {
		Pegboard peg = new Pegboard();
		
		Player p1 = new Player();
		Player p2 = new Player();
		
		peg.addPlayer(p1);
		peg.addPlayer(p2);
		
		peg.addPoints(p1, 12);
		
		assertEquals(12,peg.getScore(p1));
		assertEquals(0,peg.getScore(p2));
	}
	
	@Test
	void testHasWon() {
		Pegboard peg = new Pegboard();
		
		Player p1 = new Player();
		Player p2 = new Player();
		Player p3 = new Player();
		
		peg.addPlayer(p1);
		peg.addPlayer(p2);
		peg.addPlayer(p3);
		
		peg.addPoints(p1, 121);
		peg.addPoints(p2, 122);
		
		assertTrue(peg.hasWon(p1));
		assertTrue(peg.hasWon(p2));
		assertFalse(peg.hasWon(p3));
	}
	
	@Test
	void testAllScores() {
		Pegboard peg = new Pegboard();
		
		Player p1 = new Player();
		Player p2 = new Player();
		Player p3 = new Player();
		
		peg.addPlayer(p1);
		peg.addPlayer(p2);
		peg.addPlayer(p3);
		
		peg.addPoints(p1, 89);
		peg.addPoints(p2, 12);
		
		HashMap<Player, Integer> scores = peg.getAllScores();
		
		assertEquals(89,scores.get(p1));
		assertEquals(12,scores.get(p2));
		assertEquals(0,scores.get(p3));
	}
}
