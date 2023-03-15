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
	

}
