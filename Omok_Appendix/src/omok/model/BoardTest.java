package omok.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

//import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import omok.model.Board.Place;

class BoardTest {
    Board board;
    Board board2;
	/** Before the test case a new Board will be created.*/
	@BeforeEach
	public void newBoard() {
		board=new Board();
		board2=new Board(20);
	}
	/**Test the method size() should return 15 or the board size that was called*/
	@Test
	public void testSize() {
		assertEquals(15,board.size());
		assertEquals(20,board2.size());
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
		board.placeStone(10, 10, board.player2);
		assertEquals('O',board.board[10][10]);
		board.placeStone(5, 5, board.player3);
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
		assertFalse(board.isOccupiedBy(0, 0, board.player2));
		board.board[10][10]='O';
		assertTrue(board.isOccupiedBy(10, 10, board.player2));
		assertFalse(board.isOccupiedBy(0, 0, board.player3));
		board.board[10][11]='O';
		assertTrue(board.isOccupiedBy(10, 11, board.player3));
	}
	/**Test method for playerAt() */
	@Test
	public void testPlayerAt() {
		assertNull(board.playerAt(0, 0));
		board.board[14][14]='X';
		assertEquals(board.player1,board.playerAt(14, 14));	
		board.board[10][10]='O';
		assertEquals(board.player2,board.playerAt(10, 10));
	}
	/**Test method for isWonBy()*/
	@Test
	public void testIsWonBy() {
		assertFalse(board.isWonBy(board.player1));
		assertFalse(board.isWonBy(board.player2));
		board.board=new char[][] {
				{'O','.','O','O','X','.','.'},
				{'.','O','.','X','.','.','.'},
				{'O','X','X','.','.','.','.'},
				{'O','X','X','X','.','.','.'},
				{'X','X','X','.','X','.','.'}};
				assertTrue(board.isWonBy(board.player1));
		board.board=new char[][] {
				{'O','X','O','O','X','.','.'},
				{'.','X','.','X','.','.','.'},
				{'O','X','O','.','.','.','.'},
				{'O','X','X','X','.','.','.'},
				{'X','X','X','.','X','.','.'}};
				assertTrue(board.isWonBy(board.player1));
		board.board=new char[][] {
				{'X','X','X','X','X','.','.'},
				{'.','X','.','X','.','.','.'},
				{'O','O','O','.','.','.','.'},
				{'O','X','X','X','.','.','.'},
				{'X','X','X','.','X','.','.'}};
				assertTrue(board.isWonBy(board.player1));
		board.board=new char[][] {
				{'X','X','O','O','X','.','.'},
				{'.','X','.','X','.','.','.'},	
				{'O','X','X','.','.','.','.'},
				{'O','O','X','X','.','.','.'},
				{'X','X','X','.','X','.','.'}};
				assertTrue(board.isWonBy(board.player1));
		board.board=new char[][] {
				{'O','X','O','O','X','.','.'},
				{'O','X','.','X','.','.','.'},
				{'O','X','O','.','.','.','.'},
				{'O','O','X','X','.','.','.'},
				{'O','X','X','.','X','.','.'}};
				assertTrue(board.isWonBy(board.player2));
		board.board=new char[][] {
				{'O','O','O','O','O','.','.'},
				{'O','X','.','.','.','.','.'},
				{'.','.','O','.','.','.','.'},
				{'.','.','X','X','.','.','.'},
				{'O','X','.','.','X','.','.'}};
				assertTrue(board.isWonBy(board.player2));
		board.board=new char[][] {
				{'O','X','O','O','O','.','.'},
				{'O','X','.','O','.','.','.'},
				{'.','X','O','.','.','.','.'},
				{'.','O','X','X','.','.','.'},
				{'O','X','X','.','X','.','.'}};
				assertTrue(board.isWonBy(board.player2));
		board.board=new char[][] {
				{'O','X','O','O','X','.','.'},
				{'.','O','.','X','.','.','.'},
				{'.','X','O','.','.','.','.'},
				{'.','O','X','O','.','.','.'},
				{'O','X','X','.','O','.','.'}};
				assertTrue(board.isWonBy(board.player2));

		
	}
	@Test
	public void testWinningRow() {
    	List<Place> winnerL=new ArrayList<Place>();	
    	board.board=new char[][] {
			{'O','O','O','O','O','.','.','.','.','.','.','.','.','.','.'},
			{'.','X','.','X','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','X','O','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','O','X','O','.','.','.','.','.','.','.','.','.','.','.'},
			{'O','X','X','.','O','.','.','.','.','.','.','.','.','.','.'},
			{'O','O','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','X','O','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'O','X','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'O','X','X','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'O','X','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','O','.','.','.','.','.','.','.','.','.','.'},
			{'.','.','.','.','O','.','.','.','.','.','.','.','.','.','.'},
			{'O','X','X','.','O','.','.','.','.','.','.','.','.','.','.'}};
			//Horizontal for Player 2
			Iterable<Place> winner2=board.winningRow();
			for(Place p:winner2) {
			winnerL.add(p);
			}
			Place place=winnerL.get(0);
			Place place1=winnerL.get(1);
			Place place2=winnerL.get(2);
			Place place3=winnerL.get(3);
			Place place4=winnerL.get(4);
			assertTrue(board.board[place.x][place.y]=='O');
			assertTrue(board.board[place1.x][place1.y]=='O');
			assertTrue(board.board[place2.x][place2.y]=='O');
			assertTrue(board.board[place3.x][place3.y]=='O');
			assertTrue(board.board[place4.x][place4.y]=='O');
			//Horizontal for Player 1
			board.clear();
	    	List<Place> winnerL1=new ArrayList<Place>();	
			board.clear();
			board.board[0][0]='X';
			board.board[0][1]='X';
			board.board[0][2]='X';
			board.board[0][3]='X';
			board.board[0][4]='X';
			Iterable<Place> winner3=board.winningRow();
			for(Place p:winner3) {
			winnerL1.add(p);
			}
			 place=winnerL1.get(0);
			 place1=winnerL1.get(1);
			 place2=winnerL1.get(2);
			 place3=winnerL1.get(3);
			 place4=winnerL1.get(4);
			assertTrue(board.board[place.x][place.y]=='X');
			assertTrue(board.board[place1.x][place1.y]=='X');
			assertTrue(board.board[place2.x][place2.y]=='X');
			assertTrue(board.board[place3.x][place3.y]=='X');
			assertTrue(board.board[place4.x][place4.y]=='X');
			//Vertical for Player 1
			board.clear();
	    	List<Place> winnerL2=new ArrayList<Place>();	
			board.clear();
			board.board[0][0]='X';
			board.board[1][0]='X';
			board.board[2][0]='X';
			board.board[3][0]='X';
			board.board[4][0]='X';
			Iterable<Place> winner4=board.winningRow();
			for(Place p:winner4) {
			winnerL2.add(p);
			}
			 place=winnerL2.get(0);
			 place1=winnerL2.get(1);
			 place2=winnerL2.get(2);
			 place3=winnerL2.get(3);
			 place4=winnerL2.get(4);
			assertTrue(board.board[place.x][place.y]=='X');
			assertTrue(board.board[place1.x][place1.y]=='X');
			assertTrue(board.board[place2.x][place2.y]=='X');
			assertTrue(board.board[place3.x][place3.y]=='X');
			assertTrue(board.board[place4.x][place4.y]=='X');
			//Vertical for Player 2 
			board.clear();
	    	List<Place> winnerL3=new ArrayList<Place>();	
			board.clear();
			board.board[0][0]='O';
			board.board[1][0]='O';
			board.board[2][0]='O';
			board.board[3][0]='O';
			board.board[4][0]='O';
			Iterable<Place> winner5=board.winningRow();
			for(Place p:winner5) {
			winnerL3.add(p);
			}
			 place=winnerL3.get(0);
			 place1=winnerL3.get(1);
			 place2=winnerL3.get(2);
			 place3=winnerL3.get(3);
			 place4=winnerL3.get(4);
			assertTrue(board.board[place.x][place.y]=='O');
			assertTrue(board.board[place1.x][place1.y]=='O');
			assertTrue(board.board[place2.x][place2.y]=='O');
			assertTrue(board.board[place3.x][place3.y]=='O');
			assertTrue(board.board[place4.x][place4.y]=='O');
			//Ascending Diagonal for Player 1
			board.clear();
	    	List<Place> winnerL4=new ArrayList<Place>();	
			board.clear();
			board.board[4][0]='X';
			board.board[3][1]='X';
			board.board[2][2]='X';
			board.board[1][3]='X';
			board.board[0][4]='X';
			Iterable<Place> winner6=board.winningRow();
			for(Place p:winner6) {
			winnerL4.add(p);
			}
			 place=winnerL4.get(0);
			 place1=winnerL4.get(1);
			 place2=winnerL4.get(2);
			 place3=winnerL4.get(3);
			 place4=winnerL4.get(4);
			assertTrue(board.board[place.x][place.y]=='X');
			assertTrue(board.board[place1.x][place1.y]=='X');
			assertTrue(board.board[place2.x][place2.y]=='X');
			assertTrue(board.board[place3.x][place3.y]=='X');
			assertTrue(board.board[place4.x][place4.y]=='X');
			//Ascending diagonal for Player 2
			board.clear();
	    	List<Place> winnerL5=new ArrayList<Place>();	
			board.clear();
			board.board[4][0]='O';
			board.board[3][1]='O';
			board.board[2][2]='O';
			board.board[1][3]='O';
			board.board[0][4]='O';
			Iterable<Place> winner7=board.winningRow();
			for(Place p:winner7) {
			winnerL5.add(p);
			}
			 place=winnerL5.get(0);
			 place1=winnerL5.get(1);
			 place2=winnerL5.get(2);
			 place3=winnerL5.get(3);
			 place4=winnerL5.get(4);
			assertTrue(board.board[place.x][place.y]=='O');
			assertTrue(board.board[place1.x][place1.y]=='O');
			assertTrue(board.board[place2.x][place2.y]=='O');
			assertTrue(board.board[place3.x][place3.y]=='O');
			assertTrue(board.board[place4.x][place4.y]=='O');
			//Descending diagonal for Player 1
			board.clear();
	    	List<Place> winnerL6=new ArrayList<Place>();	
			board.clear();
			board.board[0][0]='X';
			board.board[1][1]='X';
			board.board[2][2]='X';
			board.board[3][3]='X';
			board.board[4][4]='X';
			Iterable<Place> winner8=board.winningRow();
			for(Place p:winner8) {
			winnerL6.add(p);
			}
			 place=winnerL6.get(0);
			 place1=winnerL6.get(1);
			 place2=winnerL6.get(2);
			 place3=winnerL6.get(3);
			 place4=winnerL6.get(4);
			assertTrue(board.board[place.x][place.y]=='X');
			assertTrue(board.board[place1.x][place1.y]=='X');
			assertTrue(board.board[place2.x][place2.y]=='X');
			assertTrue(board.board[place3.x][place3.y]=='X');
			assertTrue(board.board[place4.x][place4.y]=='X');
			//Descending diagonal for Player 2
			board.clear();
	    	List<Place> winnerL7=new ArrayList<Place>();	
			board.clear();
			board.board[0][0]='O';
			board.board[1][1]='O';
			board.board[2][2]='O';
			board.board[3][3]='O';
			board.board[4][4]='O';
			Iterable<Place> winner9=board.winningRow();
			for(Place p:winner9) {
			winnerL7.add(p);
			}
			 place=winnerL7.get(0);
			 place1=winnerL7.get(1);
			 place2=winnerL7.get(2);
			 place3=winnerL7.get(3);
			 place4=winnerL7.get(4);
			assertTrue(board.board[place.x][place.y]=='O');
			assertTrue(board.board[place1.x][place1.y]=='O');
			assertTrue(board.board[place2.x][place2.y]=='O');
			assertTrue(board.board[place3.x][place3.y]=='O');
			assertTrue(board.board[place4.x][place4.y]=='O');
		}
	
}

	

