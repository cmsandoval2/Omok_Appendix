package omok.model;

import java.util.ArrayList;
import java.util.List;

//import omok.model.Player;

/**
 * Abstraction of an Omok board, which consists of n x n intersections
 * or places where players can place their stones. The board can be 
 * accessed using a pair of 0-based indices (x, y), where x and y 
 * denote the column and row number, respectively. The top-left 
 * intersection is represented by the indices (0, 0), and the
 * bottom-right intersection is represented by the indices (n-1, n-1).
 */
public class Board {
	private final int size;
	private Player player1=new Player("Player 1");
	private Player player2=new Player("Player 2"); 
	char[][] board=new char[0][0];
    /** Create a new board of the default size. */
    public Board() {
    	size=15;
       this.board=new char[size][size];
        for(int i=0;i<size;i++) {
        	for(int j=0;j<size;j++) {
        		board[i][j]='.';
        	}
        }
    }

    /** Create a new board of the specified size. */
    public Board(int size) {
    	this.size=size;  
        this.board=new char[size][size];
        for(int i=0;i<size;i++) {
        	for(int j=0;j<size;j++) {
        		board[i][j]='.';
        	}
        }
}
    

    /** Return the size of this board. */
    public int size() {
    	return size;
    }
    
    /** Removes all the stones placed on the board, effectively 
     * resetting the board to its original state. 
     */
    public void clear() {
    	for(int i=0;i<board.length;i++) {
    		for(int j=0;j<board[i].length;j++) {
    			board[i][j]='.';
    		}
    		}
    }

    /** Return a boolean value indicating whether all the places
     * on the board are occupied or not.
     */
    public boolean isFull() {
    	for(int i=0;i<board.length;i++) {
    		for(int j=0;j<board[i].length;j++) {
    			if(board[i][j]=='.') {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    /**
     * Place a stone for the specified player at a specified 
     * intersection (x, y) on the board.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     * @param player Player whose stone is to be placed
     */
    public void placeStone(int x, int y, Player player) {
    	char symbol=' ';
    	String user=player.name();
    	if(user.equals("Player 1")) {
    		symbol='X';
    	}else if(user.equals("CPU")||user.equals("Player 2")){
    		symbol='O';
    	}
    	board[x][y]=symbol;	
    }
    
    /**
     * Return a boolean value indicating whether the specified 
     * intersection (x, y) on the board is empty or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isEmpty(int x, int y) {
    	if(board[x][y]=='.') {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Is the specified place on the board occupied?
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupied(int x, int y) {
    	if(board[x][y]=='.') {
    		return false;
    	}
    	return true;
    }

    /**
     * Rreturn a boolean value indicating whether the specified 
     * intersection (x, y) on the board is occupied by the given 
     * player or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupiedBy(int x, int y, Player player) {
    	String user=player.name();
    	if(user.equals("Player 1") && board[x][y]=='X') {
    		return true;
    	}
    	else if(user.equals("CPU") && board[x][y]=='O') {
    		return true;
    	}
    	else if(user.equals("Player 2") && board[x][y]=='O') {
    		return true;
    	}
    	return false;
    }

    /**
     * Return the player who occupies the specified intersection (x, y) 
     * on the board. If the place is empty, this method returns null.
     * 
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public Player playerAt(int x, int y) {
    	Player player1=new Player("Player 1");
    	Player player2=new Player("Player 2");
    	if(isOccupiedBy(x,y,player1)) {
    		return player1;
    	}else if(isOccupiedBy(x,y,player2)) {
    		return player2;
    	}
    	return null;
    }

    /** 
     * Return a boolean value indicating whether the given player 
     * has a winning row on the board. A winning row is a consecutive 
     * sequence of five or more stones placed by the same player in 
     * a horizontal, vertical, or diagonal direction.
     */
    public boolean isWonBy(Player player) {
    	if(player.name()=="player") {
    		//Horizontal
    	for(int i=0;i<board.length;i++) {
    		for(int j=0;j<board[i].length-3;j++) {
    			if(board[i][j]=='X'&&board[i][j+1]=='X'&&board[i][j+2]=='X'&&board[i][j+3]=='X'&&board[i][j+4]=='X') {
    				return true;
    			}
    		}
    	}
    			//vertical
    		for(int i=0;i<board.length-3;i++) {
    			for(int j=0;j<board[i].length;j++) {
    		 if(board[i][j]=='X'&&board[i+1][j]=='X'&&board[i+2][j]=='X'&&board[i+3][j]=='X'&&board[i+4][j]=='X') {
    				return true;
    			}
    			}
    		}
    		//Ascending diagonal
    		for (int i = 4; i < board.length; i++) {
    		    for (int j = 0; j < board[i].length-4; j++) {
    		        if (board[i][j] == 'X' && board[i-1][j+1] == 'X' && board[i-2][j+2] == 'X' && board[i-3][j+3] == 'X' && board[i-4][j+4] == 'X') {
    		            return true;
    		        }
    		    }
    		}
    		//Descending diagonal
    		for(int i=4;i<board.length;i++) {
    			for(int j=4;j<board[i].length;j++) {
    				if(board[i][j]=='X'&&board[i-1][j-1]=='X'&&board[i-2][j-2]=='X'&&board[i-3][j-3] =='X'&&board[i-4][j-4]=='X') {
    					return true;
    		}
    			}
    		}
    	}
    	if(player.name()=="cpu"||player.name()=="player2") {
    		//Horizontal
    		for(int i=0;i<board.length;i++) {
    			for(int j=0;j<board[i].length-3;j++) {
    				if(board[i][j]=='O'&&board[i][j+1]=='O'&&board[i][j+2]=='O'&&board[i][j+3]=='O'&&board[i][j+4]=='O') {
    					return true;
    				}
    			}
    		}
    				//vertical
    			for(int i=0;i<board.length-3;i++) {
    				for(int j=0;j<board[i].length;j++) {
    			 if(board[i][j]=='O'&&board[i+1][j]=='O'&&board[i+2][j]=='O'&&board[i+3][j]=='O'&&board[i+4][j]=='O') {
    					return true;
    				}
    				}
    			}
    			//Ascending diagonal
    			for (int i = 4; i < board.length; i++) {
    			    for (int j = 0; j < board[i].length-4; j++) {
    			        if (board[i][j] == 'O' && board[i-1][j+1] == 'O' && board[i-2][j+2] == 'O' && board[i-3][j+3] == 'O' && board[i-4][j+4] == 'O') {
    			            return true;
    			        }
    			    }
    			}
    			//Descending diagonal
    			for(int i=4;i<board.length;i++) {
    				for(int j=4;j<board[i].length;j++) {
    					if(board[i][j]=='O'&&board[i-1][j-1]=='O'&&board[i-2][j-2]=='O'&&board[i-3][j-3] =='O'&&board[i-4][j-4]=='O') {
    						return true;
    			}
    				}
    			}
    	}
    	return false;
    }

    /** Return the winning row. For those who are not familiar with
     * the Iterable interface, you may return an object of
     * List<Place>. */
    public Iterable<Place> winningRow() {
    	List<Place> winner=new ArrayList<Place>();
    	for(int i=0;i<size;i++) {
        	for(int j=0;j<size;j++) {
        		if(isWonBy(player1)||isWonBy(player2)) {
        			Place place=new Place(i,j);
        			winner.add(place);
        		}
        	}
        }
       		return winner;
        
    }

    /**
     * An intersection on an Omok board identified by its 0-based column
     * index (x) and row index (y). The indices determine the position 
     * of a place on the board, with (0, 0) denoting the top-left 
     * corner and (n-1, n-1) denoting the bottom-right corner, 
     * where n is the size of the board.
     */
    public static class Place {
        /** 0-based column index of this place. */
        public final int x;

        /** 0-based row index of this place. */
        public final int y;

        /** Create a new place of the given indices. 
         * 
         * @param x 0-based column (vertical) index
         * @param y 0-based row (horizontal) index
         */
        public Place(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // other methods if needed ...
    }
}

