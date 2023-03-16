package omok.model;

import static org.junit.jupiter.api.Assertions.*;

//import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
    Board board;
	/** Before the test case a new Board will be created.*/
	@BeforeEach
	public void newBoard() {
		board=new Board();
	}
	/**Test the method size() should return 15 or the board size that was called*/
	@Test
	public void testSize() {
		assertEquals(15,board.size());
	}
	/**Will test if the board only has '.' and not any user input */
	@Test
	public void testClear() {
		board.clear();
		for(int i=0;i<board.size();i++) {
        	for(int j=0;j<board.size();j++) {
        		assertEquals('.',board.board[i][j]);
        	}
        }
	}
	/**Testing if the isFull method works by using a full board*/
	@Test
	public void testIsFull() {
		assertFalse(board.isFull());
		board.board=new char[][] {
			{'X','O','X','O','X','O','X'},
			{'O','X','X','O','O','X','O'},
			{'x','O','O','X','O','X','O'},
			{'X','O','X','X','O','X','O'},
			{'O','X','X','O','X','O','X'}};
			assertTrue(board.isFull());
		}
	/**test method for placeStone() uses placeStone method and checks if the board is equal to what is placed on board */
	@Test
	public void testPlaceStone() {
		board.placeStone(14, 14, board.player1);
		assertEquals('X',board.board[14][14]);
	}
	/**Test method for isEmpty() use assertTrue and assertFalse to see is the results match the expected */
	@Test
	public void testIsEmpty() {
		assertTrue(board.isEmpty(0, 0));
		board.board[14][14]='X';
		assertFalse(board.isEmpty(14, 14));
	}
	/**Test method for isOccupied() use assertTrue and assertFalse to see is the results match the expected */
	@Test
	public void testIsOccupied() {
		assertFalse(board.isOccupied(0, 0));
		board.board[14][14]='X';
		assertTrue(board.isOccupied(14, 14));
	}
	/**Test method for isOccupiedBy() use assertTrue and assertFalse to see is the results match the expected */
	@Test
	public void testIsOccupiedBy() {
		assertFalse(board.isOccupiedBy(0, 0, board.player1));
		board.board[14][14]='X';
		assertTrue(board.isOccupiedBy(14, 14, board.player1));
	}
	/**Test method for playerAt() */
	@Test
	public void testPlayerAt() {
		assertNull(board.playerAt(0, 0));
		board.board[14][14]='X';
		assertEquals(board.player1,board.playerAt(14, 14));	
	}
	/**Test method for isWonBy()*/
	@Test
	public void testIsWonBy() {
		assertFalse(board.isWonBy(board.player1));
		board.board=new char[][] {
				{'O','.','O','O','X','.','.'},
				{'.','O','.','X','.','.','.'},
				{'O','X','X','.','.','.','.'},
				{'O','X','X','X','.','.','.'},
				{'X','X','X','.','X','.','.'}};
				assertTrue(board.isWonBy(board.player1));
		
	}
	}

